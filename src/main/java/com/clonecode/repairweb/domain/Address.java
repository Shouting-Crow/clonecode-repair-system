package com.clonecode.repairweb.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Address {

    private String city;
    private String streetAddress;

    protected Address(){}

    public Address(String city, String streetAddress){
        this.city = city;
        this.streetAddress = streetAddress;
    }
}
