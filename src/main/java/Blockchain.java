// File: src/main/java/Blockchain.java
package com.blockchain;


import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    private List<Block> chain;

    public Blockchain() {
        this.chain = new ArrayList<>();
        this.chain.add(createGenesisBlock());
    }

    public Block createGenesisBlock() {
        return new Block("Genesis Block", "0");
    }

    public void addBlock(Block newBlock) {
        this.chain.add(newBlock);
    }

    public List<Block> getChain() {
        return chain;
    }

    public boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[2]).replace('\0', '0');

        for (int i = 1; i < chain.size(); i++) {
            currentBlock = chain.get(i);
            previousBlock = chain.get(i - 1);

            // Check if the current block's hash is valid
            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                System.out.println("Current Block's hash is invalid.");
                return false;
            }

            // Check if the previous hash is correct
            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                System.out.println("Previous Block's hash is invalid.");
                return false;
            }
        }
        return true;
    }
}