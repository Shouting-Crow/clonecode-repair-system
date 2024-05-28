package com.clonecode.repairweb.controller;

import com.clonecode.repairweb.domain.login.Admin;
import com.clonecode.repairweb.domain.login.Member;
import com.clonecode.repairweb.domain.login.Repairman;
import com.clonecode.repairweb.domain.login.User;
import com.clonecode.repairweb.service.register.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegisterController {

    private final RegisterService registerService;

    @GetMapping("/admin")
    public String showAdminRegisterForm(Model model){
        model.addAttribute("admin", new Admin());
        return "register/admin";
    }

    @GetMapping("/member")
    public String showMemberRegisterForm(Model model){
        model.addAttribute("member", new Member());
        return "register/member";
    }

    @GetMapping("/repairman")
    public String showRepairmanRegisterForm(Model model){
        model.addAttribute("repairman", new Repairman());
        return "register/repairman";
    }

    @PostMapping("/admin")
    public String registerAdmin(@ModelAttribute(name = "admin") Admin admin, Model model){
        Admin savedAdmin = (Admin) registerService.registerUser(admin);
        model.addAttribute("admin", savedAdmin);
        return "redirect:/";
    }

    @PostMapping("/member")
    public String registerMember(@ModelAttribute Member member, Model model) {
        Member savedMember = (Member) registerService.registerUser(member);
        model.addAttribute("member", savedMember);
        return "redirect:/";
    }

    @PostMapping("/repairman")
    public String registerRepairman(@ModelAttribute Repairman repairman, Model model) {
        Repairman savedRepairman = (Repairman) registerService.registerUser(repairman);
        model.addAttribute("repairman", savedRepairman);
        return "redirect:/";
    }



}
