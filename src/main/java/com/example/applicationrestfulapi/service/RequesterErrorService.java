package com.example.applicationrestfulapi.service;


import com.example.applicationrestfulapi.DTO.RequesterErrorDTO;
import com.example.applicationrestfulapi.entity.ExternalAppTable;
import com.example.applicationrestfulapi.repository.ExternalAppRepository;
import com.example.applicationrestfulapi.repository.RequesterRepository;
import com.example.applicationrestfulapi.entity.RequesterTable;
import com.example.applicationrestfulapi.repository.RequesterFormRepository;
import com.example.applicationrestfulapi.repository.UsersTableRepository;
import kz.gatewaysoap.requester.RequesterERROR;
import kz.gatewaysoap.requester.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class RequesterErrorService {

    @Autowired
    private RequesterRepository requesterRepository;
    @Autowired
    private RequesterFormRepository requesterFormRepository;
    @Autowired
    private ExternalAppRepository externalAppRepository;
    @Autowired
    private UsersTableRepository usersTableRepository;

    private static final Map<String, RequesterERROR> requesterERROR = new HashMap<>();

    @PostConstruct
    public void initData() {

    }

    public RequesterERROR findRequesterErrorUsername(String username) {
        return requesterERROR.get(username);
    }


    public void putListErrorUsername(String username) {
        RequesterERROR requesterErDTO = new RequesterErrorDTO();
        requesterErDTO.setStatus(Status.ERROR);
        requesterErDTO.setMessageError(username + " неверный отправитель");
        requesterERROR.put(username, requesterErDTO);
    }

    public boolean checkExternalStatus(String externalAppName) {
        ExternalAppTable externalAppTable = externalAppRepository.findExternalAppTableByName(externalAppName);
        return externalAppTable.getIsActive();
    }

    public void putListErrorExternalApp(String username) {
        RequesterERROR requesterErDTO = new RequesterErrorDTO();
        requesterErDTO.setStatus(Status.ERROR);
        requesterErDTO.setMessageError("Неверное тело запроса");
        requesterERROR.put(username, requesterErDTO);
    }

    public void addIinInDB(String iin,
                           String firstName,
                           String lastName,
                           String patronymic) {
        RequesterTable requesterTable = new RequesterTable();
        requesterTable.setIin(iin);
        requesterTable.setFirstName(firstName);
        requesterTable.setLastName(lastName);
        requesterTable.setPatronymic(patronymic);
        requesterRepository.save(requesterTable);

    }

    public boolean checkLenIin(String iin) {
        return iin.length() == 12;
    }

    public void putListErrorLenIin(String username) {
        RequesterERROR requesterErDTO = new RequesterErrorDTO();
        requesterErDTO.setStatus(Status.ERROR);
        requesterErDTO.setMessageError("Неверная длинна ИИНа");
        requesterERROR.put(username, requesterErDTO);
    }


}
