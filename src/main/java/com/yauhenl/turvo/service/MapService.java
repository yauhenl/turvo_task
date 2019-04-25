package com.yauhenl.turvo.service;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.alg.shortestpath.KShortestSimplePaths;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.jgrapht.util.SupplierUtil.createDefaultWeightedEdgeSupplier;
import static org.jgrapht.util.SupplierUtil.createStringSupplier;

@Service
public class MapService {

    @Value("${turvo.mapSize}")
    private Integer mapSize;

    private static final Graph<String, DefaultWeightedEdge> map = new SimpleWeightedGraph<>(createStringSupplier(), createDefaultWeightedEdgeSupplier());

    public void generateMap() {
        TurvoGraphGenerator<String, DefaultWeightedEdge> generator = new TurvoGraphGenerator<>(mapSize);
        generator.generateGraph(map);
    }

    public synchronized void addDistance(String cityA, String cityB, Integer distance) {
        map.addVertex(cityA);
        map.addVertex(cityB);
        map.addEdge(cityA, cityB);
        map.setEdgeWeight(cityA, cityB, distance);
    }

    public List<GraphPath<String, DefaultWeightedEdge>> getDistance(String cityA, String cityB) {
        Graph<String, DefaultWeightedEdge> copy = new SimpleWeightedGraph<>(createStringSupplier(), createDefaultWeightedEdgeSupplier());
        Graphs.addGraph(copy, map);
        KShortestSimplePaths<String, DefaultWeightedEdge> paths = new KShortestSimplePaths<>(copy);
        return paths.getPaths(cityA, cityB, Integer.MAX_VALUE);
    }
}
