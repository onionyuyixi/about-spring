package com.yuyixi.aboutspring;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.yuyixi.aboutspring.akka.SpringExtProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yangcong
 * @date 2019/10/15 14:42
 */


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAkka {


    @Autowired
    private ActorSystem actorSystem;

    @Test
    public void contextLoads() {
        ActorRef ref = actorSystem.actorOf(SpringExtProvider.getInstance().get(actorSystem).create("testActor"), "testActor");
        ref.tell("hello", ActorRef.noSender());
    }
}
