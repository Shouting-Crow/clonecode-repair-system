package com.clonecode.repairweb.controller;

import com.clonecode.repairweb.domain.item.Item;
import com.clonecode.repairweb.domain.login.User;
import com.clonecode.repairweb.domain.search.ItemSearch;
import com.clonecode.repairweb.service.item.ItemService;
import com.clonecode.repairweb.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

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
}
