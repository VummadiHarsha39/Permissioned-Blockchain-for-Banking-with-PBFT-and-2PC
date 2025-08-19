// File: src/main/java/com/blockchain/PrepareTxMessage.java
package com.blockchain;

public class PrepareTxMessage extends Message {
    private final String transactionId;
    private final String senderAccountId;
    private final String receiverAccountId;
    private final double amount;

    public PrepareTxMessage(int senderId, String transactionId, String senderAccountId, String receiverAccountId, double amount) {
        super(Message.Type.PREPARE_TX, senderId);
        this.transactionId = transactionId;
        this.senderAccountId = senderAccountId;
        this.receiverAccountId = receiverAccountId;
        this.amount = amount;
    }

    public String getTransactionId() { return transactionId; }
    public String getSenderAccountId() { return senderAccountId; }
    public String getReceiverAccountId() { return receiverAccountId; }
    public double getAmount() { return amount; }
}
