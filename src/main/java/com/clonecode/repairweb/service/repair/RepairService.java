package com.clonecode.repairweb.service.repair;

public interface RepairService {

    Long repairRegister(Long memberId, Long repairmanId, Long itemId);

    void repairCancel(Long repairId);
}
