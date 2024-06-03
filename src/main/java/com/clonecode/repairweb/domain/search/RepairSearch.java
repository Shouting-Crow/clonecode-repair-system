package com.clonecode.repairweb.domain.search;

import com.clonecode.repairweb.domain.RepairStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RepairSearch {

    private String memberName;
    private RepairStatus repairStatus;
}
