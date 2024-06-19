package com.clonecode.repairweb.form.item;

import com.clonecode.repairweb.domain.item.ItemType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemRegisterForm {

    private Long id;

    @NotEmpty(message = "이름을 입력하세요.")
    private String name;

    @NotEmpty(message = "시리얼 번호를 입력하세요.")
    private String serialNumber;

    @Min(message = "최소값은 5000입니다.", value = 5000)
    @NotNull(message = "수리 비용을 입력해주세요.")
    private Integer repairFee;

    @NotNull(message = "제품 종류를 골라주세요.")
    private ItemType itemType;

}
