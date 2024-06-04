package com.clonecode.repairweb.controller;

import com.clonecode.repairweb.domain.Address;
import com.clonecode.repairweb.domain.login.Admin;
import com.clonecode.repairweb.domain.login.Member;
import com.clonecode.repairweb.domain.login.Repairman;
import com.clonecode.repairweb.domain.login.User;
import com.clonecode.repairweb.form.AdminRegisterForm;
import com.clonecode.repairweb.form.MemberRegisterForm;
import com.clonecode.repairweb.form.RepairmanRegisterForm;
import com.clonecode.repairweb.service.register.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegisterController {

    private final RegisterService registerService;

    @GetMapping("/home")
    public String showRegisterHome(){
        return "register/home";
    }

    @GetMapping("/admin")
    public String showAdminRegisterForm(Model model){
        model.addAttribute("admin", new AdminRegisterForm());
        return "register/admin";
    }

    @GetMapping("/member")
    public String showMemberRegisterForm(Model model){
        model.addAttribute("member", new MemberRegisterForm());
        return "register/member";
    }

    @GetMapping("/repairman")
    public String showRepairmanRegisterForm(Model model){
        model.addAttribute("repairman", new RepairmanRegisterForm());
        return "register/repairman";
    }

    @PostMapping("/admin")
    public String registerAdmin(@ModelAttribute(name = "adminForm") AdminRegisterForm form,
                                BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "register/admin";
        }

        registerService.registerAdmin(form);
        return "redirect:/";
    }

    @PostMapping("/member")
    public String registerMember(@ModelAttribute(name = "memberForm") MemberRegisterForm form,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "register/member";
        }

        registerService.registerMember(form);
        return "redirect:/";
    }

    @PostMapping("/repairman")
    public String registerRepairman(@ModelAttribute(name = "repairmanForm") RepairmanRegisterForm form,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "register/repairman";
        }

        registerService.registerRepairman(form);
        return "redirect:/";
    }



}
