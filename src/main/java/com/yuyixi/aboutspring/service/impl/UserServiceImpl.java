package com.yuyixi.aboutspring.service.impl;

import com.yuyixi.aboutspring.entity.User;
import com.yuyixi.aboutspring.repository.UserRepository;
import com.yuyixi.aboutspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override

    public void addUser(User user) {
        userRepository.save(user);
        userRepository.save(user);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void addUserWithTranscationAndTryIsNoUse(User user) {
        try {
            userRepository.save(user);
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUserWithTranscationAndTryIsNoUseButWeCanThrow(User user) throws Exception {
        try {
            userRepository.save(user);
            int i = 1/0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
    }

    @Override
    public void addUserWithTranscationAndTryIsNoUseButWeCanHandleBySelf(User user) throws Exception {
        try {
            userRepository.save(user);
            int i = 1/0;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }
}
