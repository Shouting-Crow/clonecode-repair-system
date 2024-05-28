package com.clonecode.repairweb.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Embeddable
@Getter
public class ItemSpec {

    private final List<String> specArr = new ArrayList<>();

    protected ItemSpec(){}

    public ItemSpec(String... specs){
        this.specArr.addAll(Arrays.asList(specs));
    }

}
