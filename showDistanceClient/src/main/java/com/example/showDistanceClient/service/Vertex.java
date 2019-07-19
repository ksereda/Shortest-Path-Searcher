package com.example.showDistanceClient.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vertex implements Comparable<Vertex> {

    private final String name;
    int dist = Integer.MAX_VALUE;
    Vertex previous = null;
    final Map<Vertex, Integer> neighbours = new HashMap<>();

    Vertex(String name) {
        this.name = name;
    }

    protected void printPath(List list, String startName) {

        if (this == this.previous) {
        } else if (this.previous == null) {
            list.add(" %s(unreached) " + this.name);
        } else {
            if (!list.contains(startName + " -> ")) {
                list.add(startName + " -> ");
            }
            this.previous.printPath(list, startName);
            list.add(" -> " + this.name + " " + this.dist);
        }
    }

    public int compareTo(Vertex other) {
        if (dist == other.dist) {
            return name.compareTo(other.name);
        }
        return Integer.compare(dist, other.dist);
    }

    @Override public String toString() {
        return "(" + name + ", " + dist + ")";
    }

}

