package com.example.applicationrestfulapi.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
//@Component
public class SOAPDataDTO {
    private String sender;
    private Long id;
    private String Iin;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String content;
    private String nameExternalApp;
    private Boolean isActive;


}
