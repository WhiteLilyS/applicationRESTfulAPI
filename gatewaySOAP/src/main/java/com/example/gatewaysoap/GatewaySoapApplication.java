package com.example.gatewaysoap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GatewaySoapApplication {

    public static void main(String[] args) {
        try{
            SpringApplication.run(GatewaySoapApplication.class, args);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
