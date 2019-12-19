package com.yuyixi.aboutspring.jgraph;

import org.jgrapht.Graph;
import org.jgrapht.alg.cycle.CycleDetector;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.TopologicalOrderIterator;

import java.util.Iterator;
import java.util.Set;

public class SimpleDemo {

    public static void main(String[] args) {

        testGraph(false);
        testGraph(true);
    }

    static void  testGraph(Boolean createCycle){
        CycleDetector<String, DefaultEdge> cycleDetector;
        Graph<String,DefaultEdge> g;

        g= new DefaultDirectedGraph(DefaultEdge.class);



        g.addVertex("a");
        g.addVertex("b");
        g.addVertex("c");
        g.addVertex("d");
        g.addVertex("e");


        g.addEdge("b","a");
        g.addEdge("c","b");
        if(createCycle){
            g.addEdge("a","c");
        }
        g.addEdge("e","d");
        if(createCycle){
            g.addEdge("d","e");
        }

        System.err.println(g.toString());


        cycleDetector = new CycleDetector<>(g);

        if(cycleDetector.detectCycles()){
            System.err.println("循环侦探");
            Set<String> cycles = cycleDetector.findCycles();
            while (!cycles.isEmpty()){
                Iterator<String> iterator = cycles.iterator();
                String cycle = iterator.next();
                Set<String> strings = cycleDetector.findCyclesContainingVertex(cycle);
                strings.stream().forEach(a->{
                    System.err.println("     "+ a);
                    cycles.remove(a);
                });
            }
        }else {
            TopologicalOrderIterator<String, DefaultEdge> orderIterator = new TopologicalOrderIterator<>(g);
            System.err.println("有序的拓扑结构 不含有循环节点");
            while (orderIterator.hasNext()){
                System.err.println(orderIterator.next());
            }
        }

    }
}
