// File: src/main/java/Block.java
package com.blockchain;
import java.util.Date;
import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;

public class Block {
    private String hash;
    private String previousHash;
    private String data;
    private long timeStamp;
    private int nonce;

    // Block constructor
    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    // Getters
    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getData() {
        return data;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public int getNonce() {
        return nonce;
    }

    // Setter for data (used for the tampering demonstration)
    public void setData(String data) {
        this.data = data;
        this.hash = calculateHash();
    }

    public String calculateHash() {
        String dataToHash = previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + data;

        String calculatedhash = Hashing.sha256()
                .hashString(dataToHash, StandardCharsets.UTF_8)
                .toString();
        return calculatedhash;
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }
}