package com.example.applicationrestfulapi.modelRequester;


//import com.example.gatewaysoap.modelExternalApp.IExternalAppRepository;

import com.example.applicationrestfulapi.modelExternalApp.ExternalAppRepository;


import com.example.applicationrestfulapi.modelRequesterForm.RequesterFormRepository;
import com.example.applicationrestfulapi.modelUsersTable.UsersTableRepository;
import com.example.applicationrestfulapi.service.RequesterService;

import kz.gatewaysoap.requester.GetMessageDataRequest;
import kz.gatewaysoap.requester.GetResponseInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ws.server.endpoint.annotation.*;

@Endpoint
public class RequesterEndpoint {
    private static final String NAMESPACE_URI = "http://www.gatewaySOAP.kz/requester";

    @Autowired
    private RequesterService requesterService;

    @Autowired
    private RequesterFormRepository requesterFormRepository;
    @Autowired
    private UsersTableRepository usersTableRepository;
    @Autowired
    private RequesterRepository requesterRepository;

    @Autowired
    private ExternalAppRepository externalAppRepository;

    @Autowired
    public RequesterEndpoint(RequesterService requesterService) {
        this.requesterService = requesterService;
    }

    public RequesterEndpoint() {

    }

    @GetMapping("/getResponce")
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMessageDataRequest")
    @ResponsePayload
    public GetResponseInfoResponse getResponseInfoResponse(@RequestPayload GetMessageDataRequest request) {
        GetResponseInfoResponse response = new GetResponseInfoResponse();
        if(!usersTableRepository.existsByUsername(request.getSender())){
            requesterService.putListErrorUsername(request.getSender());
            response.setSender(requesterService.findRequesterErrorUsername(request.getSender()));
            return response;
        }
        if(!requesterService.checkExternalStatus(request.getExternalAppName())){
            requesterService.putListErrorExternalApp(request.getSender());
            response.setSender(requesterService.findRequesterErrorUsername(request.getSender()));
            return response;
        }
        if(!requesterService.checkLenIin(request.getIin())){
            requesterService.putListErrorLenIin(request.getSender());
            response.setSender(requesterService.findRequesterErrorUsername(request.getSender()));
            return response;
        }
        if(!requesterRepository.existsByIin(request.getIin())){
            requesterService.addIinInDB(request.getIin(),request.getFirstName(),request.getLastName(),request.getPatronymic());
        }
        RequesterTable requesterTable = requesterRepository.findByIin(request.getIin());
        Long gatewayID = requesterService.addRequesterFormDB(request.getContent(), requesterTable.getId());
        requesterService.putListRequester(request.getIin(),gatewayID);
        response.setRequester(requesterService.findRequesterOkIin(request.getIin()));
        return response;
    }
}
