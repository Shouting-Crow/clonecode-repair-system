package com.clonecode.repairweb.controller;

import com.clonecode.repairweb.domain.login.User;
import com.clonecode.repairweb.form.LoginForm;
import com.clonecode.repairweb.service.login.LoginService;
import com.clonecode.repairweb.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String showLoginForm(Model model){
        model.addAttribute("loginForm", new LoginForm());
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String loginSession(@Valid @ModelAttribute(name = "loginForm") LoginForm loginForm,
                               BindingResult bindingResult,
                               HttpServletRequest request){
        if (bindingResult.hasErrors()){
            return "login/loginForm";
        }

        Optional<? extends User> user = loginService.login(loginForm.getLoginId(), loginForm.getPassword());

        if (!user.isPresent()){
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        log.info("login? : {}", user.get().getName());

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_USER, user.get());

        return "redirect:/";
    }


    @PostMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session != null){
            session.invalidate();
        }
        return "redirect:/";
    }


}
