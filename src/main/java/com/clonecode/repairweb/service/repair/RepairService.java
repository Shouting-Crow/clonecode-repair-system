package com.clonecode.repairweb.service.repair;

import com.clonecode.repairweb.domain.Repair;
import com.clonecode.repairweb.domain.search.RepairSearch;
import com.clonecode.repairweb.form.RepairRequestForm;

import java.util.List;

public interface RepairService {

    Long saveRepairRequest(RepairRequestForm form);
    void repairCancel(Long repairId);
    List<Repair> searchRepairs(RepairSearch repairSearch);

}
