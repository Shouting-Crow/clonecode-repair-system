package com.clonecode.repairweb.domain.item;

import com.clonecode.repairweb.domain.ItemSpec;
import com.clonecode.repairweb.domain.ItemStatus;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("A")
@Getter @Setter
public class AirConditioner extends Item{

    @Embedded
    ItemStatus airConditionerStatus = new ItemStatus("시원하지 않음", "디스플레이 고장", "냄새가 남", "센서 고장");

    @Embedded
    ItemSpec spec = new ItemSpec("온도 조절 사용 설명서", "디스플레이 조정법", "필터 및 내부 청소 방법");

}
