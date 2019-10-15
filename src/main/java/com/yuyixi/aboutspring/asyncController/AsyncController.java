package com.yuyixi.aboutspring.asyncController;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/async/")
public class AsyncController {

    @Autowired
    ThreadPoolTaskExecutor mvcTaskExecutor;

    @PostMapping("callable")
    public Callable<List<String>> callable() {
        return () -> {
            List<String> reservations = Lists.newArrayList();
            reservations.add("Callable 4 asnyc");
            return reservations;
        };
    }

    @PostMapping("deferredResult")
    public DeferredResult<String> deferredResult() {
        final DeferredResult<String> result = new DeferredResult<>();
        result.setResult("deferredResult 4 async");
        return result;
    }

    @GetMapping("completableFuture")
    public CompletableFuture<String> completableFuture() {
        return CompletableFuture.supplyAsync(() -> "completableFuture 4 async");
    }


    @GetMapping("listenableFuture")
    public ListenableFuture<String> listenableFuture() {
        AsyncListenableTaskExecutor executor = new ConcurrentTaskExecutor();
        ListenableFuture<String> listenableFuture = executor.submitListenable(() -> "listenableFuture 4 async");
        return listenableFuture;
    }






}
