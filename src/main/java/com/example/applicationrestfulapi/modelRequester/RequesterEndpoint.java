package com.example.applicationrestfulapi.modelRequester;


//import com.example.gatewaysoap.modelExternalApp.IExternalAppRepository;

import com.example.applicationrestfulapi.modelExternalApp.ExternalAppRepository;


import com.example.applicationrestfulapi.modelRequesterForm.RequesterFormRepository;
import com.example.applicationrestfulapi.modelUsersTable.UsersTableRepository;
import com.example.applicationrestfulapi.service.RequesterErrorService;
import com.example.applicationrestfulapi.service.RequesterOKService;

import kz.gatewaysoap.requester.GetMessageDataRequest;
import kz.gatewaysoap.requester.GetResponseInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ws.server.endpoint.annotation.*;

@Endpoint
public class RequesterEndpoint {
    private static final String NAMESPACE_URI = "http://www.gatewaySOAP.kz/requester";

    @Autowired
    private RequesterOKService requesterOKService;

    @Autowired
    private RequesterFormRepository requesterFormRepository;
    @Autowired
    private UsersTableRepository usersTableRepository;
    @Autowired
    private RequesterRepository requesterRepository;

    @Autowired
    private ExternalAppRepository externalAppRepository;
    @Autowired
    private RequesterErrorService requesterErrorService;

    @Autowired
    public RequesterEndpoint(RequesterOKService requesterOKService) {
        this.requesterOKService = requesterOKService;
    }

    public RequesterEndpoint() {

    }


    @GetMapping("/getResponce")
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMessageDataRequest")
    @ResponsePayload
    public GetResponseInfoResponse getResponseInfoResponse(@RequestPayload GetMessageDataRequest request) {
        GetResponseInfoResponse response = new GetResponseInfoResponse();
        if(!usersTableRepository.existsByUsername(request.getSender())){
            requesterErrorService.putListErrorUsername(request.getSender());
            response.setSender(requesterErrorService.findRequesterErrorUsername(request.getSender()));
            return response;
        }
        if(!requesterErrorService.checkExternalStatus(request.getExternalAppName())){
            requesterErrorService.putListErrorExternalApp(request.getSender());
            response.setSender(requesterErrorService.findRequesterErrorUsername(request.getSender()));
            return response;
        }
        if(!requesterErrorService.checkLenIin(request.getIin())){
            requesterErrorService.putListErrorLenIin(request.getSender());
            response.setSender(requesterErrorService.findRequesterErrorUsername(request.getSender()));
            return response;
        }
        if(!requesterRepository.existsByIin(request.getIin())){
            requesterErrorService.addIinInDB(request.getIin(),request.getFirstName(),request.getLastName(),request.getPatronymic());
        }
        RequesterTable requesterTable = requesterRepository.findByIin(request.getIin());
        Long gatewayID = requesterOKService.addRequesterFormDB(request.getContent(), requesterTable.getId());
        requesterOKService.putListRequester(request.getIin(),gatewayID);
        response.setRequester(requesterOKService.findRequesterOkIin(request.getIin()));
        return response;
    }
}
