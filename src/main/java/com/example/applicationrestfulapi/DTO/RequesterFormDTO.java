package com.example.applicationrestfulapi.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequesterFormDTO {

    private Long id;
    private Long gatewayId;
    private Long requesterId;
    private String firstNameFromRequester;
    private String content;
    private String answer;
}
