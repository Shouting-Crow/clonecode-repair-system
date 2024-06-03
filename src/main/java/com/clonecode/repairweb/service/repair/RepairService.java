package com.clonecode.repairweb.service.repair;

import com.clonecode.repairweb.domain.Repair;
import com.clonecode.repairweb.domain.search.RepairSearch;

import java.util.List;

public interface RepairService {

    Long repairRegister(Long memberId, Long repairmanId, Long itemId);

    void repairCancel(Long repairId);

    List<Repair> searchRepairs(RepairSearch repairSearch);
}
