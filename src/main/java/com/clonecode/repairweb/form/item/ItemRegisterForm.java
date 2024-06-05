package com.clonecode.repairweb.form.item;

import com.clonecode.repairweb.domain.item.ItemType;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemRegisterForm {

    private Long id;
    private String name;
    private String serialNumber;
    private int repairFee;
    private ItemType itemType;

}
