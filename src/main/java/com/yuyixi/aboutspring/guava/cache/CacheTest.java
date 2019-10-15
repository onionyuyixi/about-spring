package com.yuyixi.aboutspring.guava.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author yangcong
 * @date 2019/10/14 18:14
 */

public class CacheTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Cache<String, String> tokenCache = CacheBuilder.newBuilder()
                .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                .expireAfterWrite(2, TimeUnit.SECONDS)
                .build();

        System.err.println(tokenCache.get("name", () -> {
            tokenCache.put("name", "onion");
            return "onion";
        }));



        Thread.sleep(1000);
        System.err.println(tokenCache.getIfPresent("name"));

        Thread.sleep(1000);
        System.err.println(tokenCache.getIfPresent("name"));

        Thread.sleep(1000);
        System.err.println(tokenCache.getIfPresent("name"));

        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
                .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                .expireAfterWrite(2, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return "loadingcache";
                    }
                });

        System.err.println(loadingCache.get("1"));


    }
}
