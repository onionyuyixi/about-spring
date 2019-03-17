package com.yuyixi.aboutspring.guava.event;

import com.yuyixi.aboutspring.guava.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestEvent implements BaseEvent {

    private String name;
}
