// File: src/main/java/com/blockchain/PBFTState.java
package com.blockchain;

import java.util.concurrent.ConcurrentHashMap; // Use this import
import java.util.Map;

public class PBFTState {
    private int viewNumber = 0;
    private int sequenceNumber = 0;
    private final int totalNodes;
    private final int faultTolerance;

    // Message logs for each phase
    private Map<String, PrePrepareMessage> prePrepareLog;
    private Map<String, Map<Integer, PrepareMessage>> prepareLog;
    private Map<String, Map<Integer, CommitMessage>> commitLog;
    private Map<String, Map<Integer, VoteMessage>> voteLog;

    public PBFTState(int totalNodes) {
        this.totalNodes = totalNodes;
        this.faultTolerance = (totalNodes - 1) / 3;

        // Initialize all maps with ConcurrentHashMap
        this.prePrepareLog = new ConcurrentHashMap<>();
        this.prepareLog = new ConcurrentHashMap<>();
        this.commitLog = new ConcurrentHashMap<>();
        this.voteLog = new ConcurrentHashMap<>();
    }

    // Getters and Setters
    public int getViewNumber() { return viewNumber; }
    public void setViewNumber(int viewNumber) { this.viewNumber = viewNumber; }

    public int getSequenceNumber() { return sequenceNumber; }
    public void setSequenceNumber(int sequenceNumber) { this.sequenceNumber = sequenceNumber; }
    public int getNextSequenceNumber() { return ++sequenceNumber; }

    public int getTotalNodes() { return totalNodes; }
    public int getFaultTolerance() { return faultTolerance; }
    public int getQuorumSize() { return 2 * faultTolerance + 1; }

    // Method to add messages to the logs
    public void addPrePrepareMessage(PrePrepareMessage message) {
        prePrepareLog.put(message.getSequenceNumber() + "-" + message.getViewNumber(), message);
    }

    public void addPrepareMessage(PrepareMessage message) {
        String key = message.getSequenceNumber() + "-" + message.getViewNumber();
        prepareLog.computeIfAbsent(key, k -> new ConcurrentHashMap<>()).put(message.getSenderId(), message);
    }

    public void addCommitMessage(CommitMessage message) {
        String key = message.getSequenceNumber() + "-" + message.getViewNumber();
        commitLog.computeIfAbsent(key, k -> new ConcurrentHashMap<>()).put(message.getSenderId(), message);
    }

    public void addVoteMessage(VoteMessage message) {
        String key = message.getTransactionId();
        voteLog.computeIfAbsent(key, k -> new ConcurrentHashMap<>()).put(message.getSenderId(), message);
    }

    public boolean hasSufficientPrepareMessages(String blockHash, int view, int sequence) {
        String key = sequence + "-" + view;
        if (prepareLog.containsKey(key)) {
            Map<Integer, PrepareMessage> messages = prepareLog.get(key);
            int count = 0;
            for (PrepareMessage message : messages.values()) {
                if (message.getBlockHash().equals(blockHash)) {
                    count++;
                }
            }
            return count >= getQuorumSize();
        }
        return false;
    }

    public boolean hasSufficientCommitMessages(String blockHash, int view, int sequence) {
        String key = sequence + "-" + view;
        if (commitLog.containsKey(key)) {
            Map<Integer, CommitMessage> messages = commitLog.get(key);
            int count = 0;
            for (CommitMessage message : messages.values()) {
                if (message.getBlockHash().equals(blockHash)) {
                    count++;
                }
            }
            return count >= getQuorumSize();
        }
        return false;
    }

    public boolean hasAllYesVotes(String transactionId, int totalParticipants) {
        if (voteLog.containsKey(transactionId)) {
            Map<Integer, VoteMessage> votes = voteLog.get(transactionId);
            int yesVotes = 0;
            for (VoteMessage vote : votes.values()) {
                if (vote.getVote()) {
                    yesVotes++;
                }
            }
            return yesVotes == totalParticipants;
        }
        return false;
    }
}