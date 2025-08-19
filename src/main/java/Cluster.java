// File: src/main/java/Cluster.java
package com.blockchain;
import java.util.ArrayList;

import java.util.List;

public class Cluster {
    private final int clusterId;
    private List<Node> nodes;

    public Cluster(int clusterId) {
        this.clusterId = clusterId;
        this.nodes = new ArrayList<>();
    }

    public int getClusterId() {
        return clusterId;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void addNode(Node node) {
        this.nodes.add(node);
    }
}