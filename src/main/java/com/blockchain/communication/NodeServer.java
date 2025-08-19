// File: src/main/java/com/blockchain/communication/NodeServer.java

package com.blockchain.communication;

import com.blockchain.Block;
import com.blockchain.Message;
import com.blockchain.Node;
import com.blockchain.PrePrepareMessage;
import com.blockchain.PrepareMessage;
import com.blockchain.CommitMessage;
import com.blockchain.PrepareTxMessage;
import com.blockchain.CommitTxMessage;
import com.blockchain.VoteMessage;
import com.google.protobuf.ByteString;

import io.grpc.stub.StreamObserver;

public class NodeServer extends NodeCommunicationGrpc.NodeCommunicationImplBase {

    private final Node node;

    public NodeServer(Node node) {
        this.node = node;
    }

    @Override
    public void ping(PingRequest request, StreamObserver<PingResponse> responseObserver) {
        System.out.println("Node " + node.getNodeId() + " received ping from Node " + request.getSenderId());

        PingResponse response = PingResponse.newBuilder()
                .setMessage("Ping received by server from Node " + request.getSenderId())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void sendPBFTMessage(PBFTMessage request, StreamObserver<Ack> responseObserver) {
        Message internalMessage = convertFromProto(request);
        if (internalMessage != null) {
            node.handleMessage(internalMessage);
        }

        Ack ack = Ack.newBuilder().setMessage("Message received.").build();
        responseObserver.onNext(ack);
        responseObserver.onCompleted();
    }

    @Override
    public void send2PCMessage(TwoPhaseCommitMessage request, StreamObserver<Ack> responseObserver) {
        Message internalMessage = convert2PCFromProto(request);
        if (internalMessage != null) {
            node.handleMessage(internalMessage);
        }

        Ack ack = Ack.newBuilder().setMessage("2PC message received.").build();
        responseObserver.onNext(ack);
        responseObserver.onCompleted();
    }

    @Override
    public void sendVote(com.blockchain.communication.VoteMessage request, StreamObserver<Ack> responseObserver) {
        node.handleMessage(new VoteMessage(request.getSenderId(), request.getTransactionId(), request.getVote()));

        Ack ack = Ack.newBuilder().setMessage("Vote received.").build();
        responseObserver.onNext(ack);
        responseObserver.onCompleted();
    }

    private Message convertFromProto(PBFTMessage protoMessage) {
        PBFTMessage.MsgCase msgCase = protoMessage.getMsgCase();

        switch (msgCase) {
            case PREPREPARE:
                PrePrepareProto protoPrePrepare = protoMessage.getPrePrepare();
                Block tempBlock = new Block("temp", "temp");
                return new PrePrepareMessage(protoPrePrepare.getSenderId(), protoPrePrepare.getViewNumber(), protoPrePrepare.getSequenceNumber(), tempBlock);
            case PREPARE:
                PrepareProto protoPrepare = protoMessage.getPrepare();
                return new PrepareMessage(protoPrepare.getSenderId(), protoPrepare.getViewNumber(), protoPrepare.getSequenceNumber(), protoPrepare.getBlockHash());
            case COMMIT:
                CommitProto protoCommit = protoMessage.getCommit();
                return new CommitMessage(protoCommit.getSenderId(), protoCommit.getViewNumber(), protoCommit.getSequenceNumber(), protoCommit.getBlockHash());
            default:
                return null;
        }
    }

    private Message convert2PCFromProto(TwoPhaseCommitMessage protoMessage) {
        TwoPhaseCommitMessage.MsgCase msgCase = protoMessage.getMsgCase();

        switch (msgCase) {
            case PREPARETX:
                PrepareTxProto protoPrepareTx = protoMessage.getPrepareTx();
                return new PrepareTxMessage(protoPrepareTx.getSenderId(), protoPrepareTx.getTransactionId(), protoPrepareTx.getSenderAccountId(), protoPrepareTx.getReceiverAccountId(), protoPrepareTx.getAmount());
            case COMMITTX:
                CommitTxProto protoCommitTx = protoMessage.getCommitTx();
                return new CommitTxMessage(protoCommitTx.getSenderId(), protoCommitTx.getTransactionId());
            default:
                return null;
        }
    }
}