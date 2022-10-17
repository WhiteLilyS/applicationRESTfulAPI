package com.example.applicationrestfulapi.entity;

import lombok.Data;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlRegistry;
import javax.persistence.*;

@Entity
@Data
@Table(name = "requester_form")
public class RequesterFormTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(Long gatewayId) {
        this.gatewayId = gatewayId;
    }

    @Column(name = "gateway_id")
    private Long gatewayId;
    @Column(name = "requester_id")
    private Long requesterId;
    private String Content;
    private String answer;


}
