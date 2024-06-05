package com.clonecode.repairweb.controller;

import com.clonecode.repairweb.domain.login.User;
import com.clonecode.repairweb.domain.search.ItemSearch;
import com.clonecode.repairweb.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String homeLogin(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User user,
                            Model model){
        if (user == null){
            model.addAttribute("itemSearch", new ItemSearch());
            return "home";
        }

        model.addAttribute("user", user);
        model.addAttribute("itemSearch", new ItemSearch());
        return "loginHome";
    }

}
