// File: src/main/java/com/blockchain/PrepareMessage.java
package com.blockchain;

public class PrepareMessage extends Message {
    private final int viewNumber;
    private final int sequenceNumber;
    private final String blockHash;

    public PrepareMessage(int senderId, int viewNumber, int sequenceNumber, String blockHash) {
        super(Message.Type.PREPARE, senderId);
        this.viewNumber = viewNumber;
        this.sequenceNumber = sequenceNumber;
        this.blockHash = blockHash;
    }

    public int getViewNumber() { return viewNumber; }
    public int getSequenceNumber() { return sequenceNumber; }
    public String getBlockHash() { return blockHash; }
}
