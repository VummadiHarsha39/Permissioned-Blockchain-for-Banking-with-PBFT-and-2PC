// File: src/main/java/Network.java
package com.blockchain;
import java.util.ArrayList;

import java.util.List;

public class Network {
    private List<Cluster> clusters;

    public Network() {
        this.clusters = new ArrayList<>();
    }

    public void addCluster(Cluster cluster) {
        this.clusters.add(cluster);
    }

    public List<Cluster> getClusters() {
        return clusters;
    }
}