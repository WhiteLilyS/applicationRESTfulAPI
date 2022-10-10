package com.example.applicationrestfulapi.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RegistrationService {
    public boolean booleanRand() {
        return new Random().nextBoolean();
    }
}
