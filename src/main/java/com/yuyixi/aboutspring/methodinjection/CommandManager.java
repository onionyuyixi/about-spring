package com.yuyixi.aboutspring.methodinjection;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * In most application scenarios, most beans in the container are singletons.
 * When a singleton bean needs to collaborate with another singleton bean or a non-singleton bean
 * needs to collaborate with another non-singleton bean, you typically handle the dependency
 * by defining one bean as a property of the other. A problem arises when the bean lifecycles are different.
 * Suppose singleton bean A needs to use non-singleton (prototype) bean B, perhaps on each method invocation on A.
 * The container creates the singleton bean A only once, and thus only gets one opportunity to set the properties.
 * The container cannot provide bean A with a new instance of bean B every time one is needed.
 */
public class CommandManager implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public Object process(Map commandState) {
        // grab a new instance of the appropriate Command
        Command command = createCommand();
        // set the state on the (hopefully brand new) Command instance
        command.setState(commandState);
        return command.execute();
    }

    protected Command createCommand() {
        // notice the Spring API dependency!
        return this.applicationContext.getBean("command", Command.class);
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
