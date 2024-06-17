package com.clonecode.repairweb.service.repair;

import com.clonecode.repairweb.domain.Repair;
import com.clonecode.repairweb.domain.RepairItem;
import com.clonecode.repairweb.domain.RepairStatus;
import com.clonecode.repairweb.domain.item.Item;
import com.clonecode.repairweb.domain.item.ItemType;
import com.clonecode.repairweb.domain.login.Member;
import com.clonecode.repairweb.domain.login.Repairman;
import com.clonecode.repairweb.domain.search.RepairSearch;
import com.clonecode.repairweb.form.RepairRequestForm;
import com.clonecode.repairweb.form.RepairSaveForm;
import com.clonecode.repairweb.repository.ItemRepository;
import com.clonecode.repairweb.repository.MemberRepository;
import com.clonecode.repairweb.repository.RepairRepository;
import com.clonecode.repairweb.repository.RepairmanRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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

    @PersistenceContext
    private EntityManager em;

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
    public void cancelRepair(Long repairId) {

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

    @Override
    @Transactional
    public Repair findById(Long repairId) {
        return em.createQuery(
                "select r from Repair r join fetch r.repairItems ri" +
                        " join fetch ri.item where r.id =:repairId", Repair.class)
                .setParameter("repairId", repairId)
                .getSingleResult();
    }

    @Override
    @Transactional
    public void updateRepairRequest(RepairSaveForm form) {
        Repair repair = repairRepository.findById(form.getRepairId()).orElseThrow();
        repair.setRepairman(repairmanRepository.findById(form.getRepairmanId()).orElseThrow());
        repair.setBookDate(form.getBookDate());
        repair.setStatus(repair.getStatus());
        repair.getRepairItems().get(0).getItem().setItemType(form.getItemType());
        repair.setMember(memberRepository.findById(form.getMemberId()).orElseThrow());
        repair.setItemStatus(form.getStatus());
        repair.getRepairItems().get(0).getItem().setSerialNumber(form.getSerialNumber());
        repair.getRepairItems().get(0).getItem().setRepairFee(form.getRepairFee());

        repairRepository.save(repair);
    }

    @Override
    @Transactional
    public void deleteRepair(Long repairId) {
        Repair repair = repairRepository.findById(repairId).orElse(null);
        if(repair == null) throw new NullPointerException();
        repairRepository.delete(repair);
    }

    @Override
    public List<Repair> findRepairsByRepairmanId(Long repairmanId) {
        return repairRepository.findByRepairmanId(repairmanId);
    }

    @Override
    @Transactional
    public void updateRepairStatus(Long repairId, RepairStatus repairStatus) {
        Repair repair = repairRepository.findById(repairId).orElseThrow();
        repair.setStatus(repairStatus);
        repairRepository.save(repair);
    }
}
