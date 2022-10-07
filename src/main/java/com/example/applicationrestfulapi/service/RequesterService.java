package com.example.applicationrestfulapi.service;


import com.example.applicationrestfulapi.DTO.RequesterErrorDTO;
import com.example.applicationrestfulapi.DTO.RequesterOkDTO;
import com.example.applicationrestfulapi.modelExternalApp.ExternalAppTable;
import com.example.applicationrestfulapi.modelExternalApp.ExternalAppRepository;

import com.example.applicationrestfulapi.modelRequester.RequesterRepository;
import com.example.applicationrestfulapi.modelRequester.RequesterTable;
import com.example.applicationrestfulapi.modelRequesterForm.RequesterFormRepository;
import com.example.applicationrestfulapi.modelRequesterForm.RequesterFormTable;
import com.example.applicationrestfulapi.modelUsersTable.UsersTableRepository;
import com.example.applicationrestfulapi.modelUsersTable.UsersTable;
//import kz.gatewaysoap.requester.Requester;
import kz.gatewaysoap.requester.RequesterERROR;
import kz.gatewaysoap.requester.RequesterOK;
import kz.gatewaysoap.requester.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class RequesterService {

    @Autowired
    private RequesterRepository requesterRepository;
    @Autowired
    private RequesterFormRepository requesterFormRepository;
    @Autowired
    private ExternalAppRepository externalAppRepository;
    @Autowired
    private UsersTableRepository usersTableRepository;

    private static final Map<String, RequesterOK> requesterOK = new HashMap<>();

    private static final Map<String, RequesterERROR> requesterERROR = new HashMap<>();

    @PostConstruct
    public void initData() {

    }

    public RequesterERROR findRequesterErrorUsername(String username) {
        return requesterERROR.get(username);
    }

    public RequesterOK findRequesterOkIin(String Iin) {
        return requesterOK.get(Iin);
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

    public Long addRequesterFormDB(String content, Long requesterId) {
        RequesterFormTable requesterFormTable = new RequesterFormTable();
        Long newGatewayId = requesterFormRepository.getMaxId() + 1L;
        requesterFormTable.setGatewayId(newGatewayId);
        requesterFormTable.setRequesterId(requesterId);
        requesterFormTable.setContent(content);
        requesterFormRepository.save(requesterFormTable);
        return newGatewayId;
    }

    public void putListRequester(String iin, Long gatewayId) {
        RequesterTable requesterTable = requesterRepository.findByIin(iin);
        RequesterFormTable requesterFormTable = requesterFormRepository.findRequesterFormTableByGatewayId(gatewayId);
        RequesterOkDTO requesterOkDTO = new RequesterOkDTO();
        requesterOkDTO.setRequestId(requesterFormTable.getRequesterId());
        requesterOkDTO.setStatus(Status.OK);
        requesterOK.put(requesterTable.getIin(), requesterOkDTO);
    }

}
