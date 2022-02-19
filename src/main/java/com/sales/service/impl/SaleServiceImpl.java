package com.sales.service.impl;

import com.sales.api.PaginatedResponse;
import com.sales.api.PaginationRequest;
import com.sales.mapper.SaleMapper;
import com.sales.model.Client;
import com.sales.model.Sale;
import com.sales.model.Transaction;
import com.sales.model.repo.SaleRepo;
import com.sales.pojo.SaleModel;
import com.sales.pojo.TransactionModel;
import com.sales.pojo.response.ProductResponse;
import com.sales.service.ClientService;
import com.sales.service.ProductService;
import com.sales.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepo saleRepo;
    private final ClientService clientService;
    private final ProductService productService;
    private final SaleMapper mapper = Mappers.getMapper(SaleMapper.class);

    @Override
    public SaleModel createSale(SaleModel request) {
        Sale sale = new Sale();

        return populateSale(sale, request);
    }

    @Override
    public SaleModel updateSale(SaleModel request, UUID saleId) {
        Sale sale = saleRepo.findById(saleId)
                .orElseThrow(() -> new RuntimeException("Sale does not exist"));

        return populateSale(sale, request);
    }

    @Override
    public PaginatedResponse<SaleModel> retrieveSales(PaginationRequest request) {
        Page<Sale> page = saleRepo.findAll(request.pageRequest());
        List<SaleModel> sales = page.stream()
                .map(mapper::convert)
                .collect(toList());

        return new PaginatedResponse<>(sales, page);
    }

    private SaleModel populateSale(Sale sale, SaleModel request) {
        Client client = clientService.retrieveClient(request.getClientId()).getClient();
        sale.setClient(client);
        sale.setSeller(request.getSeller());

        Map<UUID, ProductResponse> products = productService.retrieveProducts(request.getTransactions().stream()
                .map(TransactionModel::getProductId)
                .collect(toList()));

        populateTransactions(sale, request, products);

        BigDecimal total = sale.getTransactions().stream()
                .map(Transaction::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        sale.setTotal(total);

        saleRepo.save(sale);

        return mapper.convert(sale);
    }

    private void populateTransactions(Sale sale, SaleModel request, Map<UUID, ProductResponse> products) {
        List<UUID> requestIds = new ArrayList<>();
        List<Transaction> newTransactions = new ArrayList<>();
        for (TransactionModel transactionModel : request.getTransactions()) {
            Transaction transaction = sale.getTransactions().stream()
                    .filter(trans -> trans.getId().equals(transactionModel.getTransactionId()))
                    .findFirst()
                    .orElse(new Transaction());

            ProductResponse productResponse = products.get(transactionModel.getProductId());
            if (productResponse == null)
                throw new RuntimeException("Transaction product does not exist");

            transaction.setProduct(productResponse.getProduct());
            transaction.setPrice(transactionModel.getPrice());
            transaction.setQuantity(transactionModel.getQuantity());

            if (transaction.getId() == null)
                newTransactions.add(transaction);
            else
                requestIds.add(transaction.getId());
        }
        newTransactions.forEach(sale::addTransaction);

        sale.getTransactions().stream()
                .filter(trans -> trans.getId() != null && !requestIds.contains(trans.getId()))
                .forEach(trans -> trans.setDeleted(true));
    }
}
