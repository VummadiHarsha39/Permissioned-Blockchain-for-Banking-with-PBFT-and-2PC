// File: src/main/java/com/blockchain/CommitMessage.java
package com.blockchain;

public class CommitMessage extends Message {
    private final int viewNumber;
    private final int sequenceNumber;
    private final String blockHash;

    public CommitMessage(int senderId, int viewNumber, int sequenceNumber, String blockHash) {
        super(Message.Type.COMMIT, senderId);
        this.viewNumber = viewNumber;
        this.sequenceNumber = sequenceNumber;
        this.blockHash = blockHash;
    }

    public int getViewNumber() { return viewNumber; }
    public int getSequenceNumber() { return sequenceNumber; }
    public String getBlockHash() { return blockHash; }
}
