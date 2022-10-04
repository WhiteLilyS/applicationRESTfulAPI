package com.example.applicationrestfulapi.modelRequester;


//import com.example.gatewaysoap.modelExternalApp.IExternalAppRepository;

import com.example.applicationrestfulapi.DTO.SOAPDataDTO;
import com.example.applicationrestfulapi.modelExternalApp.IExternalAppRepository;


import com.example.applicationrestfulapi.modelRequesterForm.IRequesterFormRepository;
import com.example.applicationrestfulapi.modelUsersTable.IUsersTableRepository;
import kz.gatewaysoap.requester.GetMessageDataRequest;
import kz.gatewaysoap.requester.GetResponseInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class RequesterEndpoint {
    private static final String NAMESPACE_URI = "http://www.gatewaySOAP.kz/requester";

    @Autowired
    private RequesterRepository requesterRepository;

    @Autowired
    private IRequesterFormRepository iRequesterFormRepository;
    @Autowired
    private IUsersTableRepository iUsersTableRepository;
    @Autowired
    private IRequesterRepository iRequesterRepository;

    @Autowired
    private IExternalAppRepository iExternalAppRepository;

    @Autowired
    public RequesterEndpoint(RequesterRepository requesterRepository) {
        this.requesterRepository = requesterRepository;
    }

    public RequesterEndpoint() {

    }

//    @GetMapping("/getResponse")
//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMessageDataRequest")
//    @ResponsePayload
//    public GetResponseInfoResponse GetResponse(@RequestPayload GetMessageDataRequest request) {
//        GetResponseInfoResponse response = new GetResponseInfoResponse();
//        if (!iUsersTableRepository.existsByUsername(request.getSender())) {
//            requesterRepository.putListErrorRequester(request.getSender());
//            response.setMessageError(request.getSender() + " неверный отправитель");
//            response.setRequester(requesterRepository.findRequesterUsername(request.getSender()));
//            return response;
//        }
//        if (!iRequesterRepository.existsByIin(request.getIin())) {
//            requesterRepository.checkIinInDB(request.getIin(), request.getFirstName(), request.getLastName(), request.getPatronymic());
//        }
//        RequesterTable requesterTable = iRequesterRepository.findByIin(request.getIin());
//        Long gatewayID = requesterRepository.addRequesterFormDB(request.getContent(), requesterTable.getId());
//        requesterRepository.putListRequester(request.getIin(), request.getExternalAppName(), gatewayID);
//        response.setRequester(requesterRepository.findRequesterIin(request.getIin()));
//
//        return response;
//    }

    @GetMapping("/getResponse")
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMessageDataRequest")
    @ResponsePayload
    public GetResponseInfoResponse GetResponseInfo(@RequestPayload GetMessageDataRequest request) {
        System.out.println(request.getSender()+" "+request.getFirstName()+" sender");
//        iRequesterRepository = (IRequesterRepository) iUsersTableRepository.findByUsername(request.getSender());
        GetResponseInfoResponse response = new GetResponseInfoResponse();
        if (!iUsersTableRepository.existsByUsername(request.getSender())) {
            requesterRepository.putListErrorRequester(request.getSender());
            response.setMessageError(request.getSender() + " неверный отправитель");
            response.setRequester(requesterRepository.findRequesterUsername(request.getSender()));
            return response;
        }
        if (!iRequesterRepository.existsByIin(request.getIin())) {
            requesterRepository.checkIinInDB(request.getIin(), request.getFirstName(), request.getLastName(), request.getPatronymic());
        }
        RequesterTable requesterTable = iRequesterRepository.findByIin(request.getIin());
        Long gatewayID = requesterRepository.addRequesterFormDB(request.getContent(), requesterTable.getId());
        requesterRepository.putListRequester(request.getIin(), request.getExternalAppName(), gatewayID);
        response.setRequester(requesterRepository.findRequesterIin(request.getIin()));

        return response;
    }
}
