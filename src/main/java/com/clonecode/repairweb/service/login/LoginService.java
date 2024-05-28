package com.clonecode.repairweb.service.login;

import com.clonecode.repairweb.domain.login.User;

import java.util.Optional;

public interface LoginService {
    Optional<? extends User> login(String loginId, String password);
}
