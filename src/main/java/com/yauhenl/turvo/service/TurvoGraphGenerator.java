package com.yauhenl.turvo.service;

import org.jgrapht.Graph;
import org.jgrapht.generate.GraphGenerator;

import java.util.*;

public class TurvoGraphGenerator<V, E> implements GraphGenerator<V, E, V> {
    private final int size;
    private final Random rng;

    public TurvoGraphGenerator(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("invalid size: " + size + " (must be non-negative)");
        }
        this.size = size;
        this.rng = new Random();
    }

    @Override
    public void generateGraph(Graph<V, E> target, Map<String, V> resultMap) {
        List<V> vertexList = new ArrayList<>();
        vertexList.add(target.addVertex());
        for (int i = 0; i < size; i++) {
            V newVertex = target.addVertex();
            for (int j = 0; j < rng.nextInt(vertexList.size()); j++) {
                int index = rng.nextInt(vertexList.size());
                if (rng.nextBoolean()) {
                    target.addEdge(vertexList.get(index), newVertex);
                    target.setEdgeWeight(vertexList.get(index), newVertex, rng.nextInt(100));
                } else {
                    target.addEdge(newVertex, vertexList.get(index));
                    target.setEdgeWeight(newVertex, vertexList.get(index), rng.nextInt(100));
                }
            }
            vertexList.add(newVertex);
        }
    }
}
