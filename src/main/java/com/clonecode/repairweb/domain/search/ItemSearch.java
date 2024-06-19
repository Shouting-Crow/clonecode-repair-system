package com.clonecode.repairweb.domain.search;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemSearch {
    @NotEmpty(message = "시리얼 번호를 입력하세요.")
    private String serialNumber;
}
