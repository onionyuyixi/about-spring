package com.yuyixi.aboutspring.service.impl;

import com.yuyixi.aboutspring.entity.User;
import com.yuyixi.aboutspring.repository.UserRepository;
import com.yuyixi.aboutspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }
}
