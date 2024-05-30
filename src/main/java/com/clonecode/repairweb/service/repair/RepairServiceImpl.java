package com.clonecode.repairweb.service.repair;

import com.clonecode.repairweb.domain.Repair;
import com.clonecode.repairweb.domain.RepairItem;
import com.clonecode.repairweb.domain.item.Item;
import com.clonecode.repairweb.domain.login.Member;
import com.clonecode.repairweb.domain.login.Repairman;
import com.clonecode.repairweb.repository.ItemRepository;
import com.clonecode.repairweb.repository.MemberRepository;
import com.clonecode.repairweb.repository.RepairRepository;
import com.clonecode.repairweb.repository.RepairmanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RepairServiceImpl implements RepairService{

    private final MemberRepository memberRepository;
    private final RepairmanRepository repairmanRepository;
    private final ItemRepository itemRepository;
    private final RepairRepository repairRepository;

    @Override
    @Transactional
    public Long repairRegister(Long memberId, Long repairmanId, Long itemId) {

        Member member = memberRepository.findById(memberId).orElse(null);
        Repairman repairman = repairmanRepository.findById(repairmanId).orElse(null);
        Item item = itemRepository.findById(itemId).orElse(null);

        if (item == null) return null;

        RepairItem repairItem = RepairItem.createRepairItem(item, item.getRepairFee());

        Repair repair = Repair.createRepair(member, repairman, repairItem);

        repairRepository.save(repair);
        return repair.getId();
    }

    @Override
    @Transactional
    public void repairCancel(Long repairId) {

        Repair repair = repairRepository.findById(repairId).orElse(null);
        if (repair == null) throw new NullPointerException();
        repair.cancelRepair();

    }
}
