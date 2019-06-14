package com.yuyixi.aboutspring.methodinjection;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class Command {
    Map state;

    public Object execute() {
        return  new Object();
    }
}
