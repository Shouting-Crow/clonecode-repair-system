package com.clonecode.repairweb.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Embeddable
@Getter
public class ItemStatus {

    private final List<String> statusArr = new ArrayList<>();

    protected ItemStatus(){}

    public ItemStatus(String... statuses){
        this.statusArr.addAll(Arrays.asList(statuses));
    }


}
