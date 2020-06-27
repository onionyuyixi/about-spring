package com.example.aboutasyncspringmvc.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/async/controller")
public class AsyncHelloController {

    private List<DeferredResult<List<String>>> deferredResultList = new ArrayList<>();

    @ResponseBody
    @GetMapping("/hello")
    public DeferredResult<List<String>> helloGet() throws Exception {

        final DeferredResult<List<String>> deferredResult = new DeferredResult<>();
        List<String> strings = new ArrayList();
        for (int i = 0; i < 20; i++) {

            strings.add(String.valueOf(i));

        }
//        deferredResult.setResult(strings);
        deferredResultList.add(deferredResult);
        return deferredResult;
    }

    @ResponseBody
    @GetMapping("/setHelloToAll/{content}")
    public void helloSet(@PathVariable("content") String content) throws Exception {
        // 让所有hold住的请求给与响应
        ArrayList<String> strings = new ArrayList<>();
        strings.add("从其他方法 更新内容");
        strings.add("数据得到更新");
        strings.add(content);
        deferredResultList.forEach(d->d.setResult(strings));
    }
}
