package com.clonecode.repairweb.controller;

import com.clonecode.repairweb.domain.login.User;
import com.clonecode.repairweb.form.LoginForm;
import com.clonecode.repairweb.service.login.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model){
        model.addAttribute("loginForm", new LoginForm());
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute(name = "loginForm") LoginForm loginForm, Model model){
        Optional<? extends User> user = loginService.login(loginForm.getLoginId(), loginForm.getPassword());
        if (user.isPresent()){
            model.addAttribute("user", user.get());
            return "redirect:/";
        } else {
            model.addAttribute("error", "Invalid username or password!");
            return "login/loginForm";
        }
    }


}
