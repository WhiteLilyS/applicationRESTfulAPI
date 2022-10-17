package com.example.applicationrestfulapi.Controller;


import com.example.applicationrestfulapi.DTO.RequesterFormDTO;
import com.example.applicationrestfulapi.repository.RequesterRepository;
import com.example.applicationrestfulapi.entity.RequesterTable;
import com.example.applicationrestfulapi.repository.RequesterFormRepository;
import com.example.applicationrestfulapi.entity.RequesterFormTable;
import com.example.applicationrestfulapi.service.PageRequesterService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("")
public class PageRequestController {
    @Autowired
    private RequesterFormRepository requesterFormRepository;
    @Autowired
    private PageRequesterService pageRequesterService;
    @Autowired
    private RequesterRepository requesterRepository;
    @GetMapping("/admin")
    public String getPageRequest(){
        System.out.println("HI admin");
        return "public/pageRequest";
    }

    @GetMapping("/user/pageRequest")
    public String getPageRequest(Model model , Authentication authentication) {
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
        model.addAttribute("requesterFormTableDTOList", requesterFormTableDTOList);
        return "public/pageRequest";
    }

    @PostMapping("/postRequestAnswer")
    public String postRequestAnswer(@RequestParam(name = "requesterFormTableId", defaultValue = "null") Long requesterFormTableId, @RequestParam(name = "answer") String answer, Model model) {
        pageRequesterService.addBDAnswer(answer, requesterFormTableId);
        return "redirect:/user/pageRequest";
    }


}

