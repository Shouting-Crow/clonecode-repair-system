package com.clonecode.repairweb.form;

import com.clonecode.repairweb.domain.ItemStatus;
import com.clonecode.repairweb.domain.item.ItemType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class RepairSaveForm {
    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String serialNumber;

    @NotNull
    private ItemStatus status;

    @NotNull
    private LocalDateTime bookDate;

    @NotNull
    private Long memberId;

    @NotNull
    private Long repairmanId;

    @NotNull
    private Long repairId;

    @NotNull
    private ItemType itemType;

    private Integer repairFee;

}
