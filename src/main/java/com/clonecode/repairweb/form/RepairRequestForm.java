package com.clonecode.repairweb.form;

import com.clonecode.repairweb.domain.item.ItemType;
import com.clonecode.repairweb.domain.login.Repairman;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class RepairRequestForm {
    private Long id;
    private String name;
    private String serialNumber;
    private Integer repairFee;
    private ItemType itemType;
    private String status;
    private LocalDateTime bookDate;
    private Long memberId;
    private Long repairmanId;
}
