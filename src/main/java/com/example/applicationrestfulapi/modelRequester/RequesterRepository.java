package com.example.applicationrestfulapi.modelRequester;


import com.example.applicationrestfulapi.DTO.RequesterMessageDTO;
import com.example.applicationrestfulapi.modelExternalApp.ExternalAppTable;
import com.example.applicationrestfulapi.modelExternalApp.IExternalAppRepository;
import com.example.applicationrestfulapi.modelRequesterForm.IRequesterFormRepository;
import com.example.applicationrestfulapi.modelRequesterForm.RequesterFormTable;
import com.example.applicationrestfulapi.modelUsersTable.IUsersTableRepository;
import com.example.applicationrestfulapi.modelUsersTable.UsersTable;
import kz.gatewaysoap.requester.Requester;
import kz.gatewaysoap.requester.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class RequesterRepository {

    @Autowired
    private IRequesterRepository iRequesterRepository;
    @Autowired
    private IRequesterFormRepository iRequesterFormRepository;
    @Autowired
    private IExternalAppRepository iExternalAppRepository;
    @Autowired
    private IUsersTableRepository iUsersTableRepository;
    private static final Map<String, Requester> requester = new HashMap<>();

    @PostConstruct
    public void initData() {

    }

    public Requester findRequesterIin(String Iin) {
        return requester.get(Iin);
    }

    public Requester findRequesterUsername(String username) {
        return requester.get(username);
    }

    public void checkIinInDB(String Iin,
                             String firstName,
                             String lastName,
                             String patronymic) {
        RequesterTable requesterTable = new RequesterTable();
        requesterTable.setIin(Iin);
        requesterTable.setFirstName(firstName);
        requesterTable.setLastName(lastName);
        requesterTable.setPatronymic(patronymic);
        iRequesterRepository.save(requesterTable);

    }

    public Long addRequesterFormDB(String content, Long requesterId) {
        RequesterFormTable requesterFormTable = new RequesterFormTable();
        Long newGatewayId = iRequesterFormRepository.getMaxId() + 1L;
        System.out.println(newGatewayId);
        requesterFormTable.setGatewayId(newGatewayId);
        requesterFormTable.setRequesterId(requesterId);
        requesterFormTable.setContent(content);
        iRequesterFormRepository.save(requesterFormTable);
        return newGatewayId;
    }

    public void putListRequester(String Iin, String externalAppName, Long gatewayId) {
        if (!iRequesterRepository.existsByIin(Iin)) {
        } else {
            RequesterTable requesterTable = iRequesterRepository.findByIin(Iin);
            RequesterFormTable requesterFormTable = iRequesterFormRepository.findRequesterFormTableByGatewayId(gatewayId);
            RequesterMessageDTO requesterMessageDTO = new RequesterMessageDTO();
            requesterMessageDTO.setRequestId(requesterFormTable.getRequesterId());
            requesterMessageDTO.setStatus(putListExternalStatus(externalAppName));
            requester.put(requesterTable.getIin(), requesterMessageDTO);
        }
    }

    public void putListErrorRequester(String username) {
        RequesterMessageDTO requesterMessageDTO = new RequesterMessageDTO();

        requesterMessageDTO.setStatus(putListUsernameStatus(username));
        requester.put(username, requesterMessageDTO);
    }

    public Status putListExternalStatus(String externalAppName) {
        ExternalAppTable externalAppTable = iExternalAppRepository.findExternalAppTableByName(externalAppName);
        if (externalAppTable.getIsActive()) {
            return Status.OK;
        } else
            return Status.ERROR;
    }

    public Status putListUsernameStatus(String username) {
        if (!iUsersTableRepository.existsByUsername(username)) {
            return Status.ERROR;
        }
        UsersTable usersTable = iUsersTableRepository.findByUsername(username);
        if (usersTable.getIs_active()) {
            return Status.OK;
        } else
            return Status.ERROR;
    }


}
