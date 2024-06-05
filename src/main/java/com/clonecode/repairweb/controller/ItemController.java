package com.clonecode.repairweb.controller;

import com.clonecode.repairweb.domain.item.AirConditioner;
import com.clonecode.repairweb.domain.item.Cleaner;
import com.clonecode.repairweb.domain.item.Item;
import com.clonecode.repairweb.domain.item.Tv;
import com.clonecode.repairweb.domain.login.User;
import com.clonecode.repairweb.domain.search.ItemSearch;
import com.clonecode.repairweb.form.item.ItemRegisterForm;
import com.clonecode.repairweb.service.item.ItemService;
import com.clonecode.repairweb.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

import static com.clonecode.repairweb.domain.item.ItemType.*;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;


    @PostMapping("/search")
    public String searchItem(@ModelAttribute(name = "itemSearch")ItemSearch itemSearch,
                             @SessionAttribute(name = SessionConst.LOGIN_USER, required = false)User user,
                             Model model){
        Item item = itemService.searchItem(itemSearch);
        if (item != null){
            model.addAttribute("item", item);
            return "searchResult";
        } else {
            model.addAttribute("error", "검색 결과가 없습니다.");
            if (user != null) {
                model.addAttribute("user", user);
                return "loginHome";
            }
            else return "home";
        }
    }

    @GetMapping("/items")
    public String list(Model model){
        List<Item> items = itemService.findAll();
        model.addAttribute("items", items);
        return "items";
    }

    @GetMapping("/item-register")
    public String createItemForm(Model model){
        model.addAttribute("itemRegisterForm", new ItemRegisterForm());
        return "items/createItemForm";
    }

    //컨트롤러에 변환과 로직이 있어서 좋지 못한 코드
    @PostMapping("/item-register")
    public String createItem(@ModelAttribute(name = "form") ItemRegisterForm form,
                             BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "items/createItemForm";
        }

        Item item = null;
        switch (form.getItemType()){
            case AIR_CONDITIONER:
                item = new AirConditioner();
                break;
            case CLEANER:
                item = new Cleaner();
                break;
            case TV:
                item = new Tv();
                break;
        }

        if (item != null){
            item.setId(form.getId());
            item.setName(form.getName());
            item.setSerialNumber(form.getSerialNumber());
            item.setRepairFee(form.getRepairFee());
            item.setItemType(form.getItemType());
            itemService.save(item);
        }

        return "redirect:/items";
    }
}
