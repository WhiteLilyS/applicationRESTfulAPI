package com.example.applicationrestfulapi.Controller;


import com.example.applicationrestfulapi.DTO.RequesterFormDTO;
import com.example.applicationrestfulapi.modelRequester.IRequesterRepository;
import com.example.applicationrestfulapi.modelRequester.RequesterTable;
import com.example.applicationrestfulapi.modelRequesterForm.IRequesterFormRepository;
import com.example.applicationrestfulapi.modelRequesterForm.RequesterFormTable;
import com.example.applicationrestfulapi.service.PageRequesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/pageRequest")
@Controller
public class PageRequestController {

    @Autowired
    private IRequesterFormRepository iRequesterFormRepository;

    @Autowired
    private PageRequesterService pageRequesterService;
    @Autowired
    private IRequesterRepository iRequesterRepository;

    @GetMapping("")
    public String getPageRequest(Model model) {
        List<RequesterFormDTO> requesterFormTableDTOList = new ArrayList<>();
        List<RequesterFormTable> requesterFormTableList = iRequesterFormRepository.findAll();
        for (RequesterFormTable requesterFormTable : requesterFormTableList) {
            RequesterFormDTO requesterFormDTOS = new RequesterFormDTO();
            requesterFormDTOS.setId(requesterFormTable.getId());
            requesterFormDTOS.setGatewayId(requesterFormTable.getGatewayId());
            RequesterTable requesterTable = iRequesterRepository.getById(requesterFormTable.getRequesterId());
            requesterFormDTOS.setFirstNameFromRequester(requesterTable.getFirstName());
            requesterFormDTOS.setRequesterId(requesterFormTable.getRequesterId());
            requesterFormDTOS.setContent(requesterFormTable.getContent());
            requesterFormDTOS.setAnswer(requesterFormTable.getAnswer());
            requesterFormTableDTOList.add(requesterFormDTOS);
        }
        model.addAttribute("requesterFormTableDTOList", requesterFormTableDTOList);


        return "public/pageRequest";
    }

    //    //TODO исправить пустую отправку
    @PostMapping("/postRequestAnswer")
    public String postRequestAnswer(@RequestParam(name = "requesterFormTableId") Long requesterFormTableId, @RequestParam(name = "answer") String answer) {
        if (requesterFormTableId != null) {
            pageRequesterService.addBDAnswer(answer, requesterFormTableId);
        }
        return "redirect:/pageRequest";
    }


}

