// File: src/main/java/com/blockchain/CommitTxMessage.java
package com.blockchain;

public class CommitTxMessage extends Message {
    private final String transactionId;

    public CommitTxMessage(int senderId, String transactionId) {
        super(Message.Type.COMMIT_TX, senderId);
        this.transactionId = transactionId;
    }

    public String getTransactionId() { return transactionId; }
}
