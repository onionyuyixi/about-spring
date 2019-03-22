package com.yuyixi.aboutspring.guavafuture;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.Data;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class GuavaConcurrency {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture<Object> listenableFuture = service.submit(() -> Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8));
        Futures.addCallback(listenableFuture, new FutureCallback<Object>() {
            @Override
            public void onSuccess(@Nullable Object result) {
                if (result instanceof List) {
                    ((List) result).stream().forEach(a -> System.err.println(a));
                }
            }

            @Override
            public void onFailure(Throwable t) {
                System.err.println("error");
            }
        }, service);


        ListenableFuture<JsonObject> transform = Futures.transform(listenableFuture, new Function<Object, JsonObject>() {
            @Nullable
            @Override
            public JsonObject apply(@Nullable Object input) {
                if (input instanceof List) {
                    JsonObject jsonObject = new JsonObject();
                    List list = (List) input;
                    list.stream().forEach(a -> jsonObject.addProperty(a.toString(), a.toString()));
                    return jsonObject;
                } else {
                    return null;
                }
            }
        }, service);

        ListenableFuture transformAsync = Futures.transformAsync(listenableFuture, input -> {
            InnerClass innerClass = new InnerClass();
            ListenableFuture<InnerClass> submit = service.submit(
                    () -> {
                        if (input instanceof List) {
                            List list = (List) input;
                            StringBuilder stringBuilder = new StringBuilder();
                            list.stream().forEach(a -> stringBuilder.append(a));
                            innerClass.setMsg(stringBuilder.toString());
                        }
                    }, innerClass);
            return submit;
        }, service);
        ListenableFuture<List<Object>> all = Futures.allAsList(listenableFuture, transform, transformAsync);
        all.get().stream().forEach(a -> System.err.println(a + "----"));



    }
}


@Data
class InnerClass {
    private String msg;
}
