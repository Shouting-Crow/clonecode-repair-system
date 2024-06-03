package com.clonecode.repairweb.repository;

import com.clonecode.repairweb.domain.Repair;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepairRepository extends JpaRepository<Repair, Long>, RepairRepositoryCustom {
}
