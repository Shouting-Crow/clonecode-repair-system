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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.clonecode.repairweb.domain.item.ItemType.*;

@Controller
@RequiredArgsConstructor
@Slf4j
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

    @PostMapping("/item-register")
    public String createItem(@ModelAttribute(name = "form") ItemRegisterForm form,
                             BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "items/createItemForm";
        }

        itemService.saveItem(form);
        return "redirect:/items";
    }

    @GetMapping("/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model){

        Item item = itemService.findOne(itemId);

        ItemRegisterForm form = itemService.convertToForm(item);
        model.addAttribute("form", form);

        return "items/updateItemForm";
    }

    @PostMapping("/items/{itemId}/edit")
    public String updateItem(@PathVariable("itemId") Long itemId,
                             @ModelAttribute("form") ItemRegisterForm form,
                             BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "items/updateItemForm";
        }

        itemService.updateItem(itemId, form);
        return "redirect:/items";
    }

    @PostMapping("/items/{itemId}/delete")
    public String deleteItem(@PathVariable("itemId") Long itemId){
        itemService.deleteItem(itemId);
        return "redirect:/items";
    }

}
