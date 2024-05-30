package com.clonecode.repairweb.service.user;

import com.clonecode.repairweb.domain.login.User;

import java.util.List;

public interface UserService {

    List<User> findUsers();
    User findOne(Long userId);
}
