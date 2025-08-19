// File: src/main/java/com/blockchain/Message.java
package com.blockchain;

public abstract class Message {
    public enum Type {
        PRE_PREPARE,
        PREPARE,
        COMMIT,
        PREPARE_TX,
        COMMIT_TX,
        VOTE
    }

    private final Type type;
    private final int senderId;

    public Message(Type type, int senderId) {
        this.type = type;
        this.senderId = senderId;
    }

    public Type getType() {
        return type;
    }

    public int getSenderId() {
        return senderId;
    }
}