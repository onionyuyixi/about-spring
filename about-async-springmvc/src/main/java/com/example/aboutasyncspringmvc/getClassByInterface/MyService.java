package com.example.aboutasyncspringmvc.getClassByInterface;

public interface MyService {
    default String getDescription(){
        return "Interface";
    }
}
