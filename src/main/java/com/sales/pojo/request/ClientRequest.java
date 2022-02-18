package com.sales.pojo.request;


import javax.validation.constraints.*;

public class ClientRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String lastName;

    @NotBlank
    private String mobile;
}
