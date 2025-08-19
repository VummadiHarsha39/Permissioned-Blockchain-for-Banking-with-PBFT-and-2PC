// File: src/main/java/com/blockchain/PrePrepareMessage.java
package com.blockchain;

public class PrePrepareMessage extends Message {
    private final int viewNumber;
    private final int sequenceNumber;
    private final Block block;

    public PrePrepareMessage(int senderId, int viewNumber, int sequenceNumber, Block block) {
        super(Message.Type.PRE_PREPARE, senderId);
        this.viewNumber = viewNumber;
        this.sequenceNumber = sequenceNumber;
        this.block = block;
    }

    public int getViewNumber() { return viewNumber; }
    public int getSequenceNumber() { return sequenceNumber; }
    public Block getBlock() { return block; }

    @Override
    public Type getType() { return Type.PRE_PREPARE; }
}
