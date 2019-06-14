package com.yuyixi.aboutspring.ioc.postProcessors;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Demo4Instance {

    private  String name;

    public Demo4Instance() {
    }
}
