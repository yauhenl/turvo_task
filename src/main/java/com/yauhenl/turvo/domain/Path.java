package com.yauhenl.turvo.domain;

import org.jgrapht.GraphPath;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.stream.Collectors;

public class Path {
    private String cities;
    private Double distance;

    public Path(GraphPath<String, DefaultWeightedEdge> graphPath) {
        cities = graphPath.getVertexList().stream()
                .map(Object::toString)
                .collect(Collectors.joining(" "));
        distance = graphPath.getWeight();
    }

    public String getCities() {
        return cities;
    }

    public Double getDistance() {
        return distance;
    }
}
