package com.yuyixi.aboutspring.context;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.util.StringUtils;

import static org.springframework.core.env.StandardEnvironment.SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME;
import static org.springframework.core.env.StandardEnvironment.SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME;

/**
 * @author yangcong
 * @date 2019/9/19 15:08
 */

public class EnvironmentTest {
    public static void main(String[] args) {
        StandardEnvironment environment = new StandardEnvironment();
        MutablePropertySources propertySources = environment.getPropertySources();
        //添加属性
        propertySources.addFirst(new OnionPropertySource("name"));

        //name----->onion
        //systemProperties----->null
        //systemEnvironment----->null
        propertySources.iterator().forEachRemaining(a -> System.err.println(a.getName() + "----->" + a.getProperty(a.getName())));

        //删除默认属性 systemProperties----->null
        propertySources.remove(SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME);

        //systemEnvironment----->null
        propertySources.remove(SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME);

        propertySources.iterator().forEachRemaining(a -> System.err.println(a.getName() + "----->" + a.getProperty(a.getName())));


        //添加自定义的转换器 通过可配置的转换服务
        ConfigurableConversionService cs = environment.getConversionService();
        cs.addConverter(new FooConverter());

    }


    private static class OnionPropertySource extends PropertySource<String> {
        public OnionPropertySource(String name) {
            super(name);
        }

        @Override
        public Object getProperty(String name) {
            return "onion";
        }
    }

    private static class FooConverter implements Converter<Object,String> {

        @Override
        public String convert(Object source) {
            if(source instanceof String){
                return (String) source;
            }
            return "";
        }
    }
}
