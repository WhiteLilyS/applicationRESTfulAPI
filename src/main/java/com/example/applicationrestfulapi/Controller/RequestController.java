package com.example.applicationrestfulapi.Controller;

import com.example.applicationrestfulapi.DTO.RequesterFormDTO;
import com.example.applicationrestfulapi.entity.RequesterFormTable;
import com.example.applicationrestfulapi.entity.RequesterTable;
import com.example.applicationrestfulapi.repository.RequesterFormRepository;
import com.example.applicationrestfulapi.repository.RequesterRepository;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RequestController {
    @Autowired
    private RequesterFormRepository requesterFormRepository;
    @Autowired
    private RequesterRepository requesterRepository;

    @GetMapping("/request")
    public List<RequesterFormDTO> getRequest() {
        List<RequesterFormDTO> requesterFormTableDTOList = new ArrayList<>();
        List<RequesterFormTable> requesterFormTableList = requesterFormRepository.findAll();
        for (RequesterFormTable requesterFormTable : requesterFormTableList) {
            RequesterFormDTO requesterFormDTOS = new RequesterFormDTO();
            requesterFormDTOS.setId(requesterFormTable.getId());
            requesterFormDTOS.setGatewayId(requesterFormTable.getGatewayId());
            RequesterTable requesterTable = requesterRepository.getById(requesterFormTable.getRequesterId());
            requesterFormDTOS.setFirstNameFromRequester(requesterTable.getFirstName());
            requesterFormDTOS.setRequesterId(requesterFormTable.getRequesterId());
            requesterFormDTOS.setContent(requesterFormTable.getContent());
            requesterFormDTOS.setAnswer(requesterFormTable.getAnswer());
            requesterFormTableDTOList.add(requesterFormDTOS);
        }
//        model.addAttribute("requesterFormTableDTOList", requesterFormTableDTOList);
        return requesterFormTableDTOList;
//    return null;
    }
}
