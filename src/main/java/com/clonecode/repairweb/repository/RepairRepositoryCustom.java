package com.clonecode.repairweb.repository;

import com.clonecode.repairweb.domain.Repair;
import com.clonecode.repairweb.domain.search.RepairSearch;

import java.util.List;

public interface RepairRepositoryCustom {
    List<Repair> searchRepairs(RepairSearch repairSearch);
}
