package com.yuyixi.aboutspring.service;

import com.yuyixi.aboutspring.entity.User;

public interface UserService {

    void addUser(User user);
    void addUserWithTranscationAndTryIsNoUse(User user);

    void addUserWithTranscationAndTryIsNoUseButWeCanThrow(User user) throws Exception;

    void addUserWithTranscationAndTryIsNoUseButWeCanHandleBySelf(User user) throws Exception;
}
