package com.example.applicationrestfulapi.service;

import com.example.applicationrestfulapi.modelRequester.RequesterTable;
import com.example.applicationrestfulapi.modelRequesterForm.IRequesterFormRepository;
import com.example.applicationrestfulapi.modelRequesterForm.RequesterFormTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PageRequesterService {

    @Autowired
    private IRequesterFormRepository iRequesterFormRepository;
    @Transactional
    public void addBDAnswer(String answer,Long requestFormTableId){
        RequesterFormTable requesterFormTable = iRequesterFormRepository.findById(requestFormTableId).get();
        if(answer.equals("positiveAnswer")){
            requesterFormTable.setAnswer("positiveAnswer");
            System.out.println("positiveAnswer");
        }else {
            requesterFormTable.setAnswer("negativeAnswer");
            System.out.println("negativeAnswer");
        }
    }
}
