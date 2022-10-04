package com.example.applicationrestfulapi.Controller;

import com.example.applicationrestfulapi.modelRequester.RequesterEndpoint;
import kz.gatewaysoap.requester.GetMessageDataRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/requester")
public class RequesterController {

    @GetMapping("")
    public String getRequesterFill() {
        return "public/requestFill";
    }

    @ResponseBody
    @GetMapping("/getSOAPData")
    public void getSOAPData(@RequestParam(name = "sender") String sender,
                            @RequestParam(name = "iin") String iin,
                            @RequestParam(name = "firstName") String firstName,
                            @RequestParam(name = "lastName") String lastName,
                            @RequestParam(name = "patronymic") String patronymic,
                            @RequestParam(name = "content") String content,
                            @RequestParam(name = "externalAppName") String externalAppName) {
        System.out.println(iin + " " + firstName + " " + lastName + " " + patronymic + " " + content);
        GetMessageDataRequest getMessageDataRequest = new GetMessageDataRequest();
        getMessageDataRequest.setSender(sender);
        getMessageDataRequest.setIin(iin);
        getMessageDataRequest.setFirstName(firstName);
        getMessageDataRequest.setLastName(lastName);
        getMessageDataRequest.setPatronymic(patronymic);
        getMessageDataRequest.setContent(content);
        getMessageDataRequest.setExternalAppName(externalAppName);
        RequesterEndpoint requesterEndpoint = new RequesterEndpoint();
//        requesterEndpoint.GetResponseInfo(getMessageDataRequest);
//        requesterEndpoint.GetResponseInfo(getMessageDataRequest);

//        SOAPDataDTO soapDataDTO = new SOAPDataDTO();
//        soapDataDTO.setSender(sender);
//        soapDataDTO.setIin(iin);
//        soapDataDTO.setFirstName(firstName);
//        soapDataDTO.setLastName(lastName);
//        soapDataDTO.setPatronymic(patronymic);
//        soapDataDTO.setContent(content);
//        soapDataDTO.setNameExternalApp("external");
//        RequesterEndpoint requesterEndpoint = new RequesterEndpoint();
//        requesterEndpoint.GetResponseInfo(soapDataDTO);

//        return "applicationRESTfulAPI/gatewaySOAP/src/main/resources/SOAPResponce.xml";
//        return new GetMessageDataRequest();
    }


}

