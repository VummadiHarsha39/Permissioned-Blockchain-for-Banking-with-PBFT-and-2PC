// File: src/main/java/com/blockchain/Account.java
package com.blockchain;

public class Account {
    private String accountId;
    private double balance;
    private boolean isLocked;

    public Account(String accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
        this.isLocked = false;
    }

    public synchronized double getBalance() {
        return balance;
    }

    public synchronized void setBalance(double balance) {
        this.balance = balance;
    }

    public synchronized boolean isLocked() {
        return isLocked;
    }

    public synchronized void setLocked(boolean locked) {
        isLocked = locked;
    }

    public synchronized boolean canLock(double amount) {
        return !isLocked && balance >= amount;
    }
}