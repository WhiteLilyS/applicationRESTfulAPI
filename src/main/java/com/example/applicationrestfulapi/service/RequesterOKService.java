package com.example.applicationrestfulapi.service;


import com.example.applicationrestfulapi.DTO.RequesterOkDTO;
import com.example.applicationrestfulapi.modelExternalApp.ExternalAppRepository;

import com.example.applicationrestfulapi.modelRequester.RequesterRepository;
import com.example.applicationrestfulapi.modelRequester.RequesterTable;
import com.example.applicationrestfulapi.modelRequesterForm.RequesterFormRepository;
import com.example.applicationrestfulapi.modelRequesterForm.RequesterFormTable;
import com.example.applicationrestfulapi.modelUsersTable.UsersTableRepository;
//import kz.gatewaysoap.requester.Requester;
import kz.gatewaysoap.requester.RequesterOK;
import kz.gatewaysoap.requester.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class RequesterOKService {

    @Autowired
    private RequesterRepository requesterRepository;
    @Autowired
    private RequesterFormRepository requesterFormRepository;
    @Autowired
    private ExternalAppRepository externalAppRepository;
    @Autowired
    private UsersTableRepository usersTableRepository;

    private static final Map<String, RequesterOK> requesterOK = new HashMap<>();


    @PostConstruct
    public void initData() {

    }


    public RequesterOK findRequesterOkIin(String Iin) {
        return requesterOK.get(Iin);
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
