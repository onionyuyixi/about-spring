//package com.yuyixi.aboutspring.group;
//
//
//import org.junit.jupiter.api.Test;
//import reactor.core.publisher.Flux;
//
//import java.util.Random;
//import java.util.concurrent.ConcurrentLinkedQueue;
//
///**
// * @author yangcong
// * @date 2019/8/16 16:48
// */
//
//public class GroupTest {
//
//    private ConcurrentLinkedQueue<Element> queue = new ConcurrentLinkedQueue();
//
//    @Before
//    public void prepareDate() {
//
//        for (int i = 2018; i <= 2019; i++) {
//            for (int k = 1; k <= 3000000; k++) {
//                queue.add(Element.builder()
//                        .year(i)
//                        .month(new Random().nextInt(4))
//                        .day(new Random().nextInt(6))
//                        .num(new Random().nextInt(100))
//                        .build());
//            }
//        }
//    }
//
//
//    @Test
//    public void test1() {
//        long l = System.currentTimeMillis();
//        Flux.fromIterable(queue)
//                .groupBy(Element::getYear)
//                .subscribe(a -> a.collectList().subscribe(b -> {
//                    Flux.fromIterable(b).groupBy(Element::getMonth)
//                            .subscribe(d -> d.collectList()
//                                    .subscribe(e -> Flux.fromIterable(e)
//                                            .groupBy(Element::getDay)
//                                            .subscribe(f -> f.collectList().subscribe(g -> {
//                                                Integer integer = g.stream().map(Element::getNum).reduce((h, i) -> h + i).get();
//                                                System.err.println(a.key() + "年" + d.key() + "月" + f.key() + "日" + "的数量--->" + integer);
//                                            }))
//                                    ));
//                }));
//        System.err.println("耗时--->"+(System.currentTimeMillis()-l));
//    }
//}
