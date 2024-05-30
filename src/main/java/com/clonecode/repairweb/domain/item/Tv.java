package com.clonecode.repairweb.domain.item;

import com.clonecode.repairweb.domain.ItemSpec;
import com.clonecode.repairweb.domain.ItemStatus;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("T")
@Getter @Setter
public class Tv extends Item{

    @Embedded
    ItemStatus tvStatus = new ItemStatus("소리 고장", "디스플레이 고장", "채널이 안나옴", "열이 많이 남", "벽걸이로 전환");

    @Embedded
    ItemSpec spec = new ItemSpec("셋톱박스 설정법", "리모컨 사용법", "간단 해결 설명서", "내부 구조 설계도");

}
