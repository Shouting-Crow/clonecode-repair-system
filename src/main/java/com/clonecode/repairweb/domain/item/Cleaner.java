package com.clonecode.repairweb.domain.item;

import com.clonecode.repairweb.domain.ItemSpec;
import com.clonecode.repairweb.domain.ItemStatus;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("C")
@Getter @Setter
@EqualsAndHashCode(callSuper = true)
public class Cleaner extends Item{

    @Embedded
    ItemStatus cleanerStatus = new ItemStatus("세기가 작음", "소음이 심함", "내부 분리가 안됨", "버튼 작동 안함", "움직임 이상");

    @Embedded
    ItemSpec spec = new ItemSpec("청소기 부품 가이드", "세기가 작을 때 해결법", "필터 및 내부 청소 방법");
}
