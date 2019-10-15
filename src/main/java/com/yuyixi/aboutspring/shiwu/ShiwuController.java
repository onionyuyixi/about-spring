package com.yuyixi.aboutspring.shiwu;

import com.yuyixi.aboutspring.entity.User;
import com.yuyixi.aboutspring.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangcong
 * @date 2019/7/21 19:05
 */
@Slf4j
@RestController
@RequestMapping("/shiwu/")
public class ShiwuController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("saveShiwu")
    @Transactional(rollbackFor = Exception.class)
    public void saveShiwu() {
        log.info("事务性保存");
        User user = new User();
        user.setId(122345767L);
        user.setName("yuyixi");
        userRepository.save(user);
    }


    @Transactional(rollbackFor = Exception.class)
    @PostMapping("saveShiwuTaoyiChenggong")
    public void saveShiwu1() {
        log.info("事务逃逸成功");
        User user = new User();
        user.setId(122345767L);
        user.setName("yuyixi");
        userRepository.save(user);
        user.delay();
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("saveShiwuTaoyiShibai")
    public void saveShiwu2() throws Exception {
        log.info("事务逃逸失败");
        User user = new User();
        user.setId(122345767L);
        user.setName("yuyixi");
        userRepository.save(user);
        throw new Exception("saveShiwuTaoyiShibai");
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("saveShiwuTaoyiShibai1")
    public void saveShiwu3()  {
        log.info("事务逃逸失败");
        User user = new User();
        user.setId(122345767L);
        user.setName("yuyixi");
        userRepository.save(user);
        //用新的线程去访问栈中数据出错 也依然没能逃出事务
        ((Runnable) () -> System.err.println("出错"+1/0)).run();
    }
}
