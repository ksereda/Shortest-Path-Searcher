package com.example.showDistanceClient.service;

import com.example.showDistanceClient.model.EmployeeEntity;
import java.util.*;

public class Graph {

    private final Map<String, Vertex> graph;

    public Graph(EmployeeEntity[] edges) {
        graph = new HashMap<>(edges.length);

        for (EmployeeEntity e : edges) {
            if (!graph.containsKey(e.getCity_from())) graph.put(e.getCity_from(), new Vertex(e.getCity_from()));
            if (!graph.containsKey(e.getCity_to())) graph.put(e.getCity_to(), new Vertex(e.getCity_to()));
        }

        for (EmployeeEntity e : edges) {
            graph.get(e.getCity_from()).neighbours.put(graph.get(e.getCity_to()), e.getDistance());
        }
    }

    protected void deikstra(String city_from) {
        final Vertex source = graph.get(city_from);
        NavigableSet<Vertex> store = new TreeSet<>();

        if (!graph.containsKey(city_from)) {
            throw new RuntimeException("Graph doesn't contain start vertex " + city_from);
        }

        for (Vertex v : graph.values()) {
            v.previous = v == source ? source : null;
            v.dist = v == source ? 0 : Integer.MAX_VALUE;
            store.add(v);
        }

        deikstra(store, city_from);
    }

    private void deikstra(final NavigableSet<Vertex> store, String city_from) {
        Vertex u, v;

        while (!store.isEmpty()) {

            u = store.pollFirst();
            if (u.dist == Integer.MAX_VALUE) break;

            for (Map.Entry<Vertex, Integer> a : u.neighbours.entrySet()) {
                v = a.getKey();

                final int alternateDist = u.dist + a.getValue();
                if (alternateDist < v.dist) {
                    store.remove(v);
                    v.dist = alternateDist;
                    v.previous = u;
                    store.add(v);
                }
            }
        }
    }

    protected List printPath(String city_from, String city_to) {
        List list = new ArrayList();

        if (!graph.containsKey(city_to)) {
            throw new RuntimeException("Graph doesn't contain end vertex " + city_to);
        }

        graph.get(city_to).printPath(list, city_from);
        return list;
    }

}
