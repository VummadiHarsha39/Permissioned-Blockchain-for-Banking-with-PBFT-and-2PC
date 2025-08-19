// File: src/main/java/com/blockchain/Node.java
package com.blockchain;

import com.blockchain.communication.NodeServer;
import com.blockchain.communication.NodeCommunicationGrpc;
import com.blockchain.communication.PingRequest;
import com.blockchain.communication.PingResponse;
import com.blockchain.communication.PBFTMessage;
import com.blockchain.communication.Ack;
import com.blockchain.communication.PrePrepareProto;
import com.blockchain.communication.PrepareProto;
import com.blockchain.communication.CommitProto;
import com.blockchain.communication.PrepareTxProto;
import com.blockchain.communication.CommitTxProto;
import com.blockchain.communication.TwoPhaseCommitMessage;

import com.blockchain.VoteMessage;

import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Node {
    private final int nodeId;
    private final int port;
    private Server server;
    private PBFTState state;
    private final Network network;
    private Blockchain blockchain;
    private ConcurrentMap<String, Account> accounts;
    private ConcurrentMap<String, Transaction> pendingTransactions;

    public Node(int nodeId, int port, int totalNodes, Network network) {
        this.nodeId = nodeId;
        this.port = port;
        this.state = new PBFTState(totalNodes);
        this.network = network;
        this.blockchain = new Blockchain();
        this.accounts = new ConcurrentHashMap<>();
        this.pendingTransactions = new ConcurrentHashMap<>();
        // Create a dummy account for testing
        this.accounts.put("Account_" + nodeId, new Account("Account_" + nodeId, 1000.0));
        System.out.println("Node " + this.nodeId + " created on port " + this.port + ".");
    }

    public int getNodeId() { return nodeId; }
    public int getPort() { return port; }

    public void startServer() throws IOException {
        server = ServerBuilder.forPort(port)
                .addService(new NodeServer(this))
                .build()
                .start();
        System.out.println("Node " + nodeId + " server started, listening on port " + port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("Shutting down gRPC server since JVM is shutting down.");
            Node.this.shutdownServer();
            System.err.println("Server shut down.");
        }));
    }

    public void shutdownServer() {
        if (server != null) {
            server.shutdown();
        }
    }

    // ----------------- Communication -----------------

    public void pingOtherNode(int targetNodeId, int targetPort) {
        System.out.println("Node " + this.nodeId + " is attempting to ping Node " + targetNodeId);

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", targetPort)
                .usePlaintext()
                .build();

        NodeCommunicationGrpc.NodeCommunicationBlockingStub stub = NodeCommunicationGrpc.newBlockingStub(channel);

        PingRequest request = PingRequest.newBuilder()
                .setSenderId(this.nodeId)
                .build();

        try {
            PingResponse response = stub.ping(request);
            System.out.println("Node " + this.nodeId + " received response: '" + response.getMessage() + "' from Node " + targetNodeId);
        } catch (Exception e) {
            System.err.println("Node " + this.nodeId + " failed to ping Node " + targetNodeId + ". Error: " + e.getMessage());
        } finally {
            channel.shutdownNow();
        }
    }

    public void sendPBFTMessage(int targetNodeId, int targetPort, Message message) {
        new Thread(() -> {
            System.out.println("Node " + this.nodeId + " is sending a PBFT " + message.getType() + " message to Node " + targetNodeId);

            ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", targetPort)
                    .usePlaintext()
                    .build();

            NodeCommunicationGrpc.NodeCommunicationBlockingStub stub = NodeCommunicationGrpc.newBlockingStub(channel);

            PBFTMessage protoMessage = convertToProto(message);

            try {
                stub.sendPBFTMessage(protoMessage);
            } catch (Exception e) {
                System.err.println("Node " + this.nodeId + " failed to send PBFT message to Node " + targetNodeId + ". Error: " + e.getMessage());
            } finally {
                channel.shutdownNow();
            }
        }).start();
    }

    public void send2PCMessage(int targetNodeId, int targetPort, Message message) {
        new Thread(() -> {
            System.out.println("Node " + this.nodeId + " is sending a 2PC " + message.getType() + " message to Node " + targetNodeId);

            ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", targetPort)
                    .usePlaintext()
                    .build();

            NodeCommunicationGrpc.NodeCommunicationBlockingStub stub = NodeCommunicationGrpc.newBlockingStub(channel);

            TwoPhaseCommitMessage protoMessage = convert2PCtoProto(message);

            try {
                stub.send2PCMessage(protoMessage);
            } catch (Exception e) {
                System.err.println("Node " + this.nodeId + " failed to send 2PC message to Node " + targetNodeId + ". Error: " + e.getMessage());
            } finally {
                channel.shutdownNow();
            }
        }).start();
    }

    public void sendVote(int coordinatorId, int coordinatorPort, VoteMessage message) {
        new Thread(() -> {
            System.out.println("Node " + this.nodeId + " is sending a vote for transaction " + message.getTransactionId() + " to Node " + coordinatorId);

            ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", coordinatorPort)
                    .usePlaintext()
                    .build();

            NodeCommunicationGrpc.NodeCommunicationBlockingStub stub = NodeCommunicationGrpc.newBlockingStub(channel);

            // ✅ convert domain VoteMessage → proto VoteMessage
            com.blockchain.communication.VoteMessage protoVote = convertVoteToProto(message);

            try {
                stub.sendVote(protoVote);
            } catch (Exception e) {
                System.err.println("Node " + this.nodeId + " failed to send vote to Node " + coordinatorId + ". Error: " + e.getMessage());
            } finally {
                channel.shutdownNow();
            }
        }).start();
    }

    // ----------------- Conversion Helpers -----------------

    private PBFTMessage convertToProto(Message message) {
        PBFTMessage.Builder builder = PBFTMessage.newBuilder();

        if (message instanceof PrePrepareMessage) {
            PrePrepareMessage prePrepare = (PrePrepareMessage) message;

            PrePrepareProto protoPrePrepare = PrePrepareProto.newBuilder()
                    .setSenderId(prePrepare.getSenderId())
                    .setViewNumber(prePrepare.getViewNumber())
                    .setSequenceNumber(prePrepare.getSequenceNumber())
                    .setBlock(ByteString.copyFromUtf8(prePrepare.getBlock().getHash()))
                    .build();
            builder.setPrePrepare(protoPrePrepare);
        } else if (message instanceof PrepareMessage) {
            PrepareMessage prepare = (PrepareMessage) message;
            PrepareProto protoPrepare = PrepareProto.newBuilder()
                    .setSenderId(prepare.getSenderId())
                    .setViewNumber(prepare.getViewNumber())
                    .setSequenceNumber(prepare.getSequenceNumber())
                    .setBlockHash(prepare.getBlockHash())
                    .build();
            builder.setPrepare(protoPrepare);
        } else if (message instanceof CommitMessage) {
            CommitMessage commit = (CommitMessage) message;
            CommitProto protoCommit = CommitProto.newBuilder()
                    .setSenderId(commit.getSenderId())
                    .setViewNumber(commit.getViewNumber())
                    .setSequenceNumber(commit.getSequenceNumber())
                    .setBlockHash(commit.getBlockHash())
                    .build();
            builder.setCommit(protoCommit);
        }

        return builder.build();
    }

    private TwoPhaseCommitMessage convert2PCtoProto(Message message) {
        TwoPhaseCommitMessage.Builder builder = TwoPhaseCommitMessage.newBuilder();

        if (message instanceof PrepareTxMessage) {
            PrepareTxMessage prepareTx = (PrepareTxMessage) message;
            PrepareTxProto protoPrepareTx = PrepareTxProto.newBuilder()
                    .setSenderId(prepareTx.getSenderId())
                    .setTransactionId(prepareTx.getTransactionId())
                    .setSenderAccountId(prepareTx.getSenderAccountId())
                    .setReceiverAccountId(prepareTx.getReceiverAccountId())
                    .setAmount(prepareTx.getAmount())
                    .build();
            builder.setPrepareTx(protoPrepareTx);
        } else if (message instanceof CommitTxMessage) {
            CommitTxMessage commitTx = (CommitTxMessage) message;
            CommitTxProto protoCommitTx = CommitTxProto.newBuilder()
                    .setSenderId(commitTx.getSenderId())
                    .setTransactionId(commitTx.getTransactionId())
                    .build();
            builder.setCommitTx(protoCommitTx);
        }

        return builder.build();
    }

    private com.blockchain.communication.VoteMessage convertVoteToProto(VoteMessage voteMessage) {
        return com.blockchain.communication.VoteMessage.newBuilder()
                .setSenderId(voteMessage.getSenderId())
                .setTransactionId(voteMessage.getTransactionId())
                .setVote(voteMessage.getVote())
                .build();
    }

    // ----------------- PBFT/2PC Handling -----------------

    public void broadcast(Message message) {
        for (Cluster cluster : network.getClusters()) {
            for (Node otherNode : cluster.getNodes()) {
                if (otherNode.getNodeId() != this.nodeId) {
                    sendPBFTMessage(otherNode.getNodeId(), otherNode.getPort(), message);
                }
            }
        }
    }

    public void broadcast2PC(Message message, List<Integer> participants) {
        for (int nodeId : participants) {
            Node targetNode = findNodeById(nodeId);
            if (targetNode != null && targetNode.getNodeId() != this.nodeId) {
                send2PCMessage(targetNode.getNodeId(), targetNode.getPort(), message);
            }
        }
    }

    private Node findNodeById(int nodeId) {
        for (Cluster cluster : network.getClusters()) {
            for (Node node : cluster.getNodes()) {
                if (node.getNodeId() == nodeId) {
                    return node;
                }
            }
        }
        return null;
    }

    private void lockAccount(String accountId, double amount, String transactionId) {
        Account account = accounts.get(accountId);
        if (account != null && account.canLock(amount)) {
            account.setLocked(true);
            System.out.println("Node " + this.nodeId + ": Account " + accountId + " has been LOCKED for transaction " + transactionId);
        } else {
            System.out.println("Node " + this.nodeId + ": Account " + accountId + " could not be LOCKED for transaction " + transactionId + ".");
        }
    }

    private void unlockAccount(String accountId, String transactionId) {
        Account account = accounts.get(accountId);
        if (account != null) {
            account.setLocked(false);
            System.out.println("Node " + this.nodeId + ": Account " + accountId + " has been UNLOCKED for transaction " + transactionId);
        }
    }

    private void applyTransaction(String senderAccountId, String receiverAccountId, double amount, String transactionId) {
        Account sender = accounts.get(senderAccountId);
        Account receiver = accounts.get(receiverAccountId);
        if (sender != null && receiver != null) {
            sender.setBalance(sender.getBalance() - amount);
            receiver.setBalance(receiver.getBalance() + amount);
            System.out.println("Node " + this.nodeId + ": Transaction " + transactionId + " applied successfully.");
        }
    }

    public void initiate2PC(Transaction transaction, List<Integer> participants) {
        System.out.println("Node " + this.nodeId + " is initiating 2PC for transaction " + transaction.getSenderAccountId() + " -> " + transaction.getReceiverAccountId());

        String transactionId = transaction.getTransactionId();
        PrepareTxMessage prepareTx = new PrepareTxMessage(this.nodeId, transactionId, transaction.getSenderAccountId(), transaction.getReceiverAccountId(), transaction.getAmount());
        broadcast2PC(prepareTx, participants);
    }

    public void handleMessage(Message message) {
        switch (message.getType()) {
            case PRE_PREPARE:
                if (message instanceof PrePrepareMessage) handlePrePrepare((PrePrepareMessage) message);
                break;
            case PREPARE:
                if (message instanceof PrepareMessage) handlePrepare((PrepareMessage) message);
                break;
            case COMMIT:
                if (message instanceof CommitMessage) handleCommit((CommitMessage) message);
                break;
            case PREPARE_TX:
                if (message instanceof PrepareTxMessage) handlePrepareTx((PrepareTxMessage) message);
                break;
            case COMMIT_TX:
                if (message instanceof CommitTxMessage) handleCommitTx((CommitTxMessage) message);
                break;
            case VOTE:
                if (message instanceof VoteMessage) handleVote((VoteMessage) message);
                break;
            default:
                System.err.println("Node " + this.nodeId + " received an unknown message type.");
        }
    }

    private void handlePrePrepare(PrePrepareMessage message) {
        System.out.println("Node " + this.nodeId + " is handling a Pre-prepare message from Node " + message.getSenderId());
        state.addPrePrepareMessage(message);

        PrepareMessage prepareMsg = new PrepareMessage(this.nodeId, message.getViewNumber(), message.getSequenceNumber(), message.getBlock().getHash());
        broadcast(prepareMsg);
    }

    private void handlePrepare(PrepareMessage message) {
        System.out.println("Node " + this.nodeId + " is handling a Prepare message from Node " + message.getSenderId());
        state.addPrepareMessage(message);

        if (state.hasSufficientPrepareMessages(message.getBlockHash(), message.getViewNumber(), message.getSequenceNumber())) {
            CommitMessage commitMsg = new CommitMessage(this.nodeId, message.getViewNumber(), message.getSequenceNumber(), message.getBlockHash());
            broadcast(commitMsg);
        }
    }

    private void handleCommit(CommitMessage message) {
        System.out.println("Node " + this.nodeId + " is handling a Commit message from Node " + message.getSenderId());
        state.addCommitMessage(message);

        if (state.hasSufficientCommitMessages(message.getBlockHash(), message.getViewNumber(), message.getSequenceNumber())) {
            commitBlock(message.getBlockHash());
        }
    }

    private void commitBlock(String blockHash) {
        System.out.println("Node " + this.nodeId + " has committed a block with hash " + blockHash + ".");
    }

    private void handlePrepareTx(PrepareTxMessage message) {
        System.out.println("Node " + this.nodeId + " received PREPARE_TX for transaction " + message.getTransactionId());

        boolean canCommit = false;
        Account senderAccount = accounts.get(message.getSenderAccountId());

        if (senderAccount != null && senderAccount.canLock(message.getAmount())) {
            lockAccount(message.getSenderAccountId(), message.getAmount(), message.getTransactionId());
            canCommit = true;
        } else {
            System.out.println("Node " + this.nodeId + ": Votes NO for transaction " + message.getTransactionId());
        }

        Node coordinator = findNodeById(message.getSenderId());
        VoteMessage vote = new VoteMessage(this.nodeId, message.getTransactionId(), canCommit);
        sendVote(coordinator.getNodeId(), coordinator.getPort(), vote);
    }

    private void handleCommitTx(CommitTxMessage message) {
        System.out.println("Node " + this.nodeId + " received COMMIT_TX for transaction " + message.getTransactionId());
        System.out.println("Node " + this.nodeId + ": Transaction " + message.getTransactionId() + " COMMITTED locally.");
    }
    // Add to: src/main/java/com/blockchain/Node.java (inside class Node)
    public void seedReplicaAccount(String accountId, double balance) {
        accounts.putIfAbsent(accountId, new Account(accountId, balance));
    }

    private void handleVote(VoteMessage message) {
        System.out.println("Node " + this.nodeId + " received VOTE from Node " + message.getSenderId());
        state.addVoteMessage(message);

        if (state.hasAllYesVotes(message.getTransactionId(), 4)) {
            CommitTxMessage commitTx = new CommitTxMessage(this.nodeId, message.getTransactionId());
            List<Integer> participants = List.of(5, 6, 7, 8);
            broadcast2PC(commitTx, participants);
        }
    }
}

