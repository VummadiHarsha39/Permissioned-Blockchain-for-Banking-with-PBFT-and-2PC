// File: src/main/java/com/blockchain/Transaction.java
package com.blockchain;

import java.util.UUID;

public class Transaction {
    private final String transactionId;
    private final String senderAccountId;
    private final String receiverAccountId;
    private final double amount;
    private final long timestamp;

    public Transaction(String senderAccountId, String receiverAccountId, double amount) {
        this.transactionId = UUID.randomUUID().toString();
        this.senderAccountId = senderAccountId;
        this.receiverAccountId = receiverAccountId;
        this.amount = amount;
        this.timestamp = System.currentTimeMillis();
    }

    public String getTransactionId() { return transactionId; }
    public String getSenderAccountId() { return senderAccountId; }
    public String getReceiverAccountId() { return receiverAccountId; }
    public double getAmount() { return amount; }
    public long getTimestamp() { return timestamp; }
}