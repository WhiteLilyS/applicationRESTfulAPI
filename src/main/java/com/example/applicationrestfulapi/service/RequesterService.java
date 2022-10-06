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

    public RequesterERROR findRequesterErrorIin(String Iin) {
        return requesterERROR.get(Iin);
    }

    public RequesterERROR findRequesterErrorUsername(String username) {
        return requesterERROR.get(username);
    }
    public RequesterOK findRequesterOkIin(String Iin) {
        return requesterOK.get(Iin);
    }

    public RequesterOK findRequesterOkUsername(String username) {
        return requesterOK.get(username);
    }
    public void putListErrorUsername(String username){
        RequesterERROR requesterErDTO = new RequesterErrorDTO();
        requesterErDTO.setStatus(Status.ERROR);
        requesterErDTO.setMessageError(username + " неверный отправитель");
        requesterERROR.put(username, requesterErDTO);
    }
    public boolean checkExternalStatus(String externalAppName){
        ExternalAppTable externalAppTable = externalAppRepository.findExternalAppTableByName(externalAppName);
        if (externalAppTable.getIsActive()) {
            return true;
        } else
            return false;
    }
    public void putListErrorExternalApp(String username){
        RequesterERROR requesterErDTO = new RequesterErrorDTO();
        requesterErDTO.setStatus(Status.ERROR);
        requesterErDTO.setMessageError("Неверное тело запроса");
        requesterERROR.put(username, requesterErDTO);
    }

    public void addIinInDB(String Iin,
                             String firstName,
                             String lastName,
                             String patronymic) {
        RequesterTable requesterTable = new RequesterTable();
        requesterTable.setIin(Iin);
        requesterTable.setFirstName(firstName);
        requesterTable.setLastName(lastName);
        requesterTable.setPatronymic(patronymic);
        requesterRepository.save(requesterTable);

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

    public void putListRequester(String Iin, Long gatewayId) {
            RequesterTable requesterTable = requesterRepository.findByIin(Iin);
            RequesterFormTable requesterFormTable = requesterFormRepository.findRequesterFormTableByGatewayId(gatewayId);
            RequesterOkDTO requesterOkDTO = new RequesterOkDTO();
            requesterOkDTO.setRequestId(requesterFormTable.getRequesterId());
            requesterOkDTO.setStatus(Status.OK);
            requesterOK.put(requesterTable.getIin(), requesterOkDTO);
    }



//    public void putListErrorRequester(String username,String externalAppName) {
//        RequesterERROR requesterErDTO = new RequesterErrorDTO();
//        RequesterOK requesterOKDTO = new RequesterOkDTO();
//        if(putListUsernameStatus(username)== Status.OK){
//            requesterOKDTO.setStatus(Status.OK);
//            requesterOK.put(username,requesterOKDTO );
//        }else {
//            requesterErDTO.setStatus(Status.ERROR);
//            requesterErDTO.setMessageError( username + " неверный отправитель");
//            requesterERROR.put(username, requesterErDTO);
//        }
//        if(!checkExternalStatus(externalAppName)){
//            requesterErDTO.setStatus(Status.ERROR);
//            requesterErDTO.setMessageError(requesterErDTO.getMessageError() +"\n" + " неверное тело запроса");
//        }
//    }
//
//
//
//
//    public Status putListExternalStatus(String externalAppName) {
//        ExternalAppTable externalAppTable = externalAppRepository.findExternalAppTableByName(externalAppName);
//        if (externalAppTable.getIsActive()) {
//            return Status.OK;
//        } else
//            return Status.ERROR;
//    }

//    public Status putListUsernameStatus(String username) {
//        if (!usersTableRepository.existsByUsername(username)) {
//            return Status.ERROR;
//        }
//        UsersTable usersTable = usersTableRepository.findByUsername(username);
//        if (usersTable.getIs_active()) {
//            return Status.OK;
//        } else
//            return Status.ERROR;
//    }


}
