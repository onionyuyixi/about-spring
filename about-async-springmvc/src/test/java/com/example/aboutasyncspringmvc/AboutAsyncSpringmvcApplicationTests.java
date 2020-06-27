package com.example.aboutasyncspringmvc;

import com.example.aboutasyncspringmvc.getClassByInterface.MyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

@SpringBootTest
class AboutAsyncSpringmvcApplicationTests {

    @Test
    void contextLoads() {
    }


     class FactoryBeanDemo  implements FactoryBean<String> {

        @Override
        public String getObject() throws Exception {
            return "onion";
        }

        @Override
        public Class<?> getObjectType() {
            return String.class;
        }

    }

    @Autowired
    ApplicationContext context;


    @Test
    public void testGetClassByInterface() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        Map<String, MyService> beansOfType = context.getBeansOfType(MyService.class);
        beansOfType.entrySet().forEach(a-> System.err.println(a.getKey()+"description"+
                a.getValue().getDescription()));
        List<Object> objectList = ClassUtils.getAllObjectByInterface(MyService.class,
                "com.example.aboutasyncspringmvc.getClassByInterface");
        objectList.forEach(a->{
            System.err.println(a.getClass().getName());
        });


    }


}
