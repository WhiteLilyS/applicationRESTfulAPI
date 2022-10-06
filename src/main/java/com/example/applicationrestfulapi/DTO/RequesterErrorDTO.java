package com.example.applicationrestfulapi.DTO;

import kz.gatewaysoap.requester.RequesterERROR;

public class RequesterErrorDTO extends RequesterERROR {
    private Long id;
    private String Iin;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String content;
    private Boolean isActive;
}
