// File: src/main/java/com/blockchain/Main.java
package com.blockchain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Configuring the distributed network and starting servers...");

        Network network = new Network();

        int totalNodes = 0;
        // Create 3 clusters
        for (int i = 1; i <= 3; i++) {
            Cluster cluster = new Cluster(i);
            System.out.println("Cluster " + i + " created.");

            // Create 4 nodes for each cluster
            for (int j = 1; j <= 4; j++) {
                int nodePort = 50050 + totalNodes;
                Node node = new Node(++totalNodes, nodePort, 12, network);
                cluster.addNode(node);
                System.out.println("  Node " + node.getNodeId() + " added to Cluster " + i + " on port " + nodePort);
                try {
                    node.startServer();
                } catch (IOException e) {
                    System.err.println("Failed to start server for Node " + node.getNodeId() + ": " + e.getMessage());
                }
            }
            network.addCluster(cluster);
        }

        System.out.println("\nNetwork configuration complete with " + network.getClusters().size() + " clusters and " + totalNodes + " nodes.");

        // -------- Seed replica accounts on participants (Cluster 2) --------
        // We want nodes 5–8 to have local copies of Account_1 and Account_9
        // so they can evaluate locks/balances during 2PC.
        List<Node> cluster2Nodes = network.getClusters().get(1).getNodes(); // Cluster 2
        for (Node n : cluster2Nodes) {
            n.seedReplicaAccount("Account_1", 1000.0); // copy of sender’s account
            n.seedReplicaAccount("Account_9", 1000.0); // copy of the other receiver’s account
        }

        // --- Testing Double-Spending with 2PC ---
        System.out.println("\n--- Testing 2PC with Locking Mechanism ---");
        Node coordinatorNode1 = network.getClusters().get(0).getNodes().get(0); // Node 1
        Node coordinatorNode2 = network.getClusters().get(0).getNodes().get(1); // Node 2

        // The sender is Node 1's account, and the receiver is Node 5's account
        String senderAccountId = "Account_1";
        String receiverAccountId = "Account_5";
        String receiverAccountId2 = "Account_9";

        // Define the participants (all nodes in Cluster 2)
        List<Integer> participants = new ArrayList<>();
        for (Node node : cluster2Nodes) {
            participants.add(node.getNodeId());
        }

        // Transaction 1: Should succeed
        Transaction transaction1 = new Transaction(senderAccountId, receiverAccountId, 100.0);

        // Transaction 2: Double-spend attempt, should fail due to lock (not missing account)
        Transaction transaction2 = new Transaction(senderAccountId, receiverAccountId2, 500.0);

        // Simulate a race condition: initiate both transactions at the same time
        System.out.println("\nNode 1 is initiating Transaction 1 (should succeed)...");
        coordinatorNode1.initiate2PC(transaction1, participants);

        System.out.println("\nNode 2 is initiating Transaction 2 (double-spend, should fail)...");
        coordinatorNode2.initiate2PC(transaction2, participants);

        // Wait for a few seconds to allow all phases to complete
        Thread.sleep(15000);
    }
}
