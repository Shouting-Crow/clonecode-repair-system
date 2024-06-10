package com.clonecode.repairweb.controller;

import com.clonecode.repairweb.domain.item.AirConditioner;
import com.clonecode.repairweb.domain.item.Cleaner;
import com.clonecode.repairweb.domain.item.Item;
import com.clonecode.repairweb.domain.item.Tv;
import com.clonecode.repairweb.domain.login.User;
import com.clonecode.repairweb.form.RepairRequestForm;
import com.clonecode.repairweb.service.item.ItemService;
import com.clonecode.repairweb.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class RepairController {

    private final ItemService itemService;

    //수리 요청 폼으로 변경해 해당 폼을 뷰에 전달해서 상속관계에 있는 도메인의 고장 상태 리스트를 표시하려함.
    //폼 변환 로직이 컨트롤러에 배치되어 있어 좋지 못한 코드!
    @GetMapping("/repair-request/{itemId}")
    public String repairRequestForm(@PathVariable("itemId") Long itemId,
                                    Model model,
                                    @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User user){
        Item item = itemService.findOne(itemId);
        if (item != null){
            RepairRequestForm form = new RepairRequestForm();
            form.setId(item.getId());
            form.setName(item.getName());
            form.setSerialNumber(item.getSerialNumber());
            form.setRepairFee(item.getRepairFee());
            form.setItemType(item.getItemType());

            model.addAttribute("repairRequestForm", form);
            model.addAttribute("isLoggedIn", user != null);

            if (item instanceof AirConditioner airConditioner){
                model.addAttribute("statusList", airConditioner.getAirConditionerStatus().getStatusArr());
            } else if(item instanceof Cleaner cleaner){
                model.addAttribute("statusList", cleaner.getCleanerStatus().getStatusArr());
            } else if (item instanceof Tv tv){
                model.addAttribute("statusList", tv.getTvStatus().getStatusArr());
            }

            return "repair/repairRequestForm";
        } else return "redirect:/";

    }

    //고객이 확인할 수 있는 수리 요청 리스트로 이동시킴
    //item과 고객이 선택한 제품 고장 상태를 입력할 수 있게 해야함.
    @PostMapping("/repair-request")
    public String repair(@ModelAttribute("item") Item item){

        return "redirect:/repairs";
    }

}
