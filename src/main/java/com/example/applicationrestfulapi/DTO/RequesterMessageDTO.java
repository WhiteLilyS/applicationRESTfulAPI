package com.example.applicationrestfulapi.DTO;

import kz.gatewaysoap.requester.Requester;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.implementation.bind.annotation.Super;

public class RequesterMessageDTO extends Requester {
    private Long id;
    private String Iin;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String content;
    private Boolean isActive;

}
