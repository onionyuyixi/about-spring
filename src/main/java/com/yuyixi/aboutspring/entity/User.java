package com.yuyixi.aboutspring.entity;

import com.yuyixi.aboutspring.shiwu.DelayProvider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.concurrent.TimeUnit;

@Data
@Table
@Entity
@Builder
@AllArgsConstructor
public class User {

    @Transient
    private final DelayProvider delayProvider;

    @Id
    private Long id;

    private String name;


    public User() {
        this((interval, timeUnit, task) -> {
            try {
                Thread.sleep(timeUnit.toMillis(interval));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            task.run();
        });
    }

    public User(DelayProvider delayProvider) {
        this.delayProvider = delayProvider;
    }

    public void delay() {
        this.delayProvider.executeAfterDelay(5000, TimeUnit.MICROSECONDS, () -> {
            try {
                makeError();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    void makeError() throws Exception {
        throw new Exception("make error to taoyi shiwu ");
    }
}
