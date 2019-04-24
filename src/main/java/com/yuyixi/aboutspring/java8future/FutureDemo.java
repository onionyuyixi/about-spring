package com.yuyixi.aboutspring.java8future;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class FutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Integer> longs = Lists.newLinkedList();
        CompletableFuture.supplyAsync(() -> Lists.newArrayList(1, 2, 3, 4, 6, 7)).thenApplyAsync(list->longs.addAll(list));
        System.err.println(longs);
        CompletableFuture.supplyAsync(() -> Lists.newArrayList(1, 2, 3, 4, 6, 7)).thenRunAsync(()->longs.add(1));
        System.err.println(longs);
        CompletableFuture<List<Integer>> future =CompletableFuture.supplyAsync(() -> Lists.newArrayList(1, 2, 3, 4, 6, 7)).thenApplyAsync(list->{
            List<Integer> datas = Lists.newLinkedList();
            datas.addAll(list);
            return datas;
        });
        System.err.println(future.get());
    }
}
