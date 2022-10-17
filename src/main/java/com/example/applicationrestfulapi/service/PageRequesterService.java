package com.example.applicationrestfulapi.service;

import com.example.applicationrestfulapi.repository.RequesterFormRepository;
import com.example.applicationrestfulapi.entity.RequesterFormTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PageRequesterService {

    @Autowired
    private RequesterFormRepository requesterFormRepository;

    public PageRequesterService(RequesterFormRepository requesterFormRepository) {
        this.requesterFormRepository = requesterFormRepository;
    }

    @Transactional
    public void addBDAnswer(String answer, Long requestFormTableId) {
        RequesterFormTable requesterFormTable = requesterFormRepository.findById(requestFormTableId).get();
        if (answer.equals("positiveAnswer")) {
            requesterFormTable.setAnswer("positiveAnswer");
        } else {
            requesterFormTable.setAnswer("negativeAnswer");
        }
    }

}
