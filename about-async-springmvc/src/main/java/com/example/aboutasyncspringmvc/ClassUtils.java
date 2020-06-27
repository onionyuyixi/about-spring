package com.example.aboutasyncspringmvc;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

public class ClassUtils {

    public static List<Object> getAllObjectByInterface(@NotNull final Class<?> c, @NotBlank final String implPackage) throws
            IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Assert.isTrue(!StringUtils.isEmpty(implPackage), "接口的实现类路径不能为空");
        Assert.isTrue(Objects.nonNull(c), "接口名不能为null");
        boolean anInterface = c.isInterface();
        if (!anInterface) {
            return new ArrayList<>();
        }
        final String implPackagePath = implPackage.replace(".", "/");
        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> implUrls = classLoader.getResources(implPackagePath);
        List<Object> results = new ArrayList();
        while (implUrls.hasMoreElements()) {
            URL url = implUrls.nextElement();
            String path = url.getPath();
            File file = new File(path);
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File childFile : files) {
                    if (childFile.isFile()) {
                        String fullName = childFile.getName();
                        String nameWithoutSuffix = fullName.substring(0, fullName.lastIndexOf("."));
                        String className = implPackage.concat(".").concat(nameWithoutSuffix);
                        Class<?> aClass = classLoader.loadClass(className);
                        if (!aClass.isInterface()) {
                            Class<?>[] interfaces = aClass.getInterfaces();
                            for (Class<?> clazz : interfaces) {
                                if (clazz.equals(c)) {
                                    results.add(aClass.newInstance());
                                }
                            }
                        }
                    }
                }
            }
        }
        return results;
    }
}


