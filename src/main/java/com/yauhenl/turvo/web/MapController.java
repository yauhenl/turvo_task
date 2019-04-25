package com.yauhenl.turvo.web;

import com.yauhenl.turvo.domain.Path;
import com.yauhenl.turvo.service.MapService;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MapController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MapController.class);

    @Autowired
    private MapService mapService;

    @GetMapping("/map/generate")
    public void generateMap() {
        mapService.generateMap();
    }

    @PostMapping("/distance/{cityA}/{cityB}/{distance}")
    public void addDistance(@PathVariable String cityA, @PathVariable String cityB, @PathVariable Integer distance) {
        mapService.addDistance(cityA, cityB, distance);
    }

    @GetMapping("/distance/{cityA}/{cityB}")
    @ResponseBody
    public Object[] getPaths(@PathVariable String cityA, @PathVariable String cityB) {
        List<GraphPath<String, DefaultWeightedEdge>> paths = mapService.getDistance(cityA, cityB);
        LOGGER.info("Paths count " + paths.size());
        if (paths.size() == 0) {
            return new String[]{"No any rotes between " + cityA + " and " + cityB};
        }
        if (cityA.equals(cityB)) {
            return new String[]{"The end city is the same as the start city!"};
        }
        List<Path> result = new ArrayList<>(paths.size());
        paths.forEach(it -> result.add(new Path(it)));
        return result.toArray();
    }
}
