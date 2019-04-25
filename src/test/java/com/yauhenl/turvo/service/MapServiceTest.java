package com.yauhenl.turvo.service;

import org.jgrapht.GraphPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
public class MapServiceTest {

    @InjectMocks
    private MapService mapService;

    @Test
    public void mainTest() {
        mapService.addDistance("A", "B", 5);
        mapService.addDistance("B", "C", 8);
        mapService.addDistance("C", "A", 13);
        mapService.addDistance("A", "D", 7);
        mapService.addDistance("B", "A", 9);
        mapService.addDistance("D", "C", 22);
        mapService.addDistance("E", "D", 13);
        mapService.addDistance("A", "E", 6);
        mapService.addDistance("J", "Q", 3);

        List<GraphPath<String, DefaultWeightedEdge>> distance = mapService.getDistance("A", "D");
        Assert.assertEquals(distance.size(), 4);
        distance = mapService.getDistance("Q", "J");
        Assert.assertEquals(distance.size(), 1);
        distance = mapService.getDistance("A", "J");
        Assert.assertEquals(distance.size(), 0);
    }
}
