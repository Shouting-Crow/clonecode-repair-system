package com.clonecode.repairweb.form;

import com.clonecode.repairweb.domain.RepairStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter @Getter
public class RepairListForm {
    private Long repairId;
    private Long itemId;
    private String itemName;
    private String serialNumber;
    private String memberName;
    private String repairmanName;
    private LocalDateTime bookDate;
    private String itemStatus;
    private RepairStatus repairStatus;
}
