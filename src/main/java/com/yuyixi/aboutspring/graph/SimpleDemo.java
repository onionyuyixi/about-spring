package com.yuyixi.aboutspring.graph;

import com.google.common.graph.*;

import java.util.Set;

public class SimpleDemo {

    public static void main(String[] args) {
        MutableGraph<Integer> graph = GraphBuilder.directed().build();
        graph.addNode(1);
        graph.addNode(2);
        System.err.println(graph.nodes());
        MutableValueGraph<String, Integer> valueGraph = ValueGraphBuilder.directed().build();
//        valueGraph.allowsSelfLoops();
        valueGraph.addNode("one");
        valueGraph.addNode("two");
        valueGraph.addNode("three");
        valueGraph.addNode("four");
//        valueGraph.putEdgeValue("one","one",1);
        valueGraph.putEdgeValue("one","two",2);
        valueGraph.putEdgeValue("one","three",3);
        valueGraph.putEdgeValue("two","four",4);
        valueGraph.putEdgeValue("three","four",4);
        System.err.println(valueGraph.edges());
        Set<EndpointPair<String>> edges = valueGraph.edges();
        System.err.println(valueGraph.edgeValue("one","three"));

    }
}
