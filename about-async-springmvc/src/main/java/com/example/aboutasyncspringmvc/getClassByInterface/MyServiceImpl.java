package com.example.aboutasyncspringmvc.getClassByInterface;

import org.springframework.stereotype.Component;

@Component
public class MyServiceImpl implements MyService {

    @Override
    public String getDescription() {
        return "MyServiceImpl";
    }


}
