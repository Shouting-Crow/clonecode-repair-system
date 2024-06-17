package com.clonecode.repairweb.service.repair;

import com.clonecode.repairweb.domain.Repair;
import com.clonecode.repairweb.domain.RepairStatus;
import com.clonecode.repairweb.domain.item.ItemType;
import com.clonecode.repairweb.domain.search.RepairSearch;
import com.clonecode.repairweb.form.RepairRequestForm;
import com.clonecode.repairweb.form.RepairSaveForm;

import java.time.LocalDateTime;
import java.util.List;

public interface RepairService {

    Long saveRepairRequest(RepairSaveForm form);
    void cancelRepair(Long repairId);
    List<Repair> searchRepairs(RepairSearch repairSearch);
    List<Repair> findRepairsByMemberId(Long memberId);
    Repair findById(Long repairId);
    void updateRepairRequest(RepairSaveForm form);
    void deleteRepair(Long repairId);
    List<Repair> findRepairsByRepairmanId(Long repairmanId);
    void updateRepairStatus(Long repairId, RepairStatus repairStatus);

}
