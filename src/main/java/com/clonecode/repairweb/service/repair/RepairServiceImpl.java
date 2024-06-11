package com.clonecode.repairweb.service.repair;

import com.clonecode.repairweb.domain.Repair;
import com.clonecode.repairweb.domain.RepairItem;
import com.clonecode.repairweb.domain.item.Item;
import com.clonecode.repairweb.domain.login.Member;
import com.clonecode.repairweb.domain.login.Repairman;
import com.clonecode.repairweb.domain.search.RepairSearch;
import com.clonecode.repairweb.form.RepairRequestForm;
import com.clonecode.repairweb.form.RepairSaveForm;
import com.clonecode.repairweb.repository.ItemRepository;
import com.clonecode.repairweb.repository.MemberRepository;
import com.clonecode.repairweb.repository.RepairRepository;
import com.clonecode.repairweb.repository.RepairmanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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
    public Long saveRepairRequest(RepairSaveForm form) {

        Member member = memberRepository.findById(form.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid member ID"));
        Repairman repairman = repairmanRepository.findById(form.getRepairmanId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid repairman ID"));
        Item item = itemRepository.findById(form.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid item ID"));

        RepairItem repairItem = new RepairItem();
        repairItem.setItem(item);
        repairItem.setItemStatus(form.getStatus());
        repairItem.setRepairFee(form.getRepairFee());

        Repair repair = Repair.createRepair(member, repairman, form.getBookDate(), repairItem);
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

    @Override
    public List<Repair> searchRepairs(RepairSearch repairSearch) {
        return repairRepository.searchRepairs(repairSearch);
    }

    @Override
    public List<Repair> findRepairsByMemberId(Long memberId) {
        return repairRepository.findByMemberId(memberId);
    }
}
