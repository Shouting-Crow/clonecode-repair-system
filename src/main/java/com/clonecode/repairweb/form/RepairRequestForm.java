package com.clonecode.repairweb.form;

import com.clonecode.repairweb.domain.item.ItemType;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RepairRequestForm {
    private Long id;
    private String name;
    private String serialNumber;
    private Integer repairFee;
    private ItemType itemType;
    private String status;
}
