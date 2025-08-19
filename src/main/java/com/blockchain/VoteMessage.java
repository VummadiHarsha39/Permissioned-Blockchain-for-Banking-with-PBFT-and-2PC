/// File: src/main/java/com/blockchain/VoteMessage.java
package com.blockchain;

public class VoteMessage extends Message {
    private final String transactionId;
    private final boolean vote;

    public VoteMessage(int senderId, String transactionId, boolean vote) {
        super(Message.Type.VOTE, senderId);
        this.transactionId = transactionId;
        this.vote = vote;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public boolean getVote() {
        return vote;
    }
}
