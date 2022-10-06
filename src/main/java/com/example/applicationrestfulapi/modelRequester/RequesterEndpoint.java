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

//    @GetMapping("/getResponse")
//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMessageDataRequest")
//    @ResponsePayload
//    public GetResponseInfoResponse GetResponseInfo(@RequestPayload GetMessageDataRequest request) {
//        System.out.println(request.getSender()+" "+request.getFirstName()+" sender");
////        RequesterRepository =  UsersTableRepository.findByUsername(request.getSender());
//        GetResponseInfoResponse response = new GetResponseInfoResponse();
////        RequesterOK responseOK = new RequesterOK();
//
//        if (!usersTableRepository.existsByUsername(request.getSender()) || !requesterService.checkExternalStatus(request.getExternalAppName())) {
//            requesterService.putListErrorRequester(request.getSender(),request.getExternalAppName());
//            response.setSender(requesterService.findRequesterErrorUsername(request.getSender()));
//            return response;
//        }
//        if (!requesterRepository.existsByIin(request.getIin())) {
//            requesterService.checkIinInDB(request.getIin(), request.getFirstName(), request.getLastName(), request.getPatronymic());
//        }
//        RequesterTable requesterTable = requesterRepository.findByIin(request.getIin());
//        Long gatewayID = requesterService.addRequesterFormDB(request.getContent(), requesterTable.getId());
//        requesterService.putListRequester(request.getIin(), request.getExternalAppName(), gatewayID);
//        response.setRequester(requesterService.findRequesterOkIin(request.getIin()));
//
//        return response;
//    }

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
