package com.blockchain.communication;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.63.0)",
    comments = "Source: communication.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class NodeCommunicationGrpc {

  private NodeCommunicationGrpc() {}

  public static final java.lang.String SERVICE_NAME = "NodeCommunication";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.blockchain.communication.PingRequest,
      com.blockchain.communication.PingResponse> getPingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Ping",
      requestType = com.blockchain.communication.PingRequest.class,
      responseType = com.blockchain.communication.PingResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.blockchain.communication.PingRequest,
      com.blockchain.communication.PingResponse> getPingMethod() {
    io.grpc.MethodDescriptor<com.blockchain.communication.PingRequest, com.blockchain.communication.PingResponse> getPingMethod;
    if ((getPingMethod = NodeCommunicationGrpc.getPingMethod) == null) {
      synchronized (NodeCommunicationGrpc.class) {
        if ((getPingMethod = NodeCommunicationGrpc.getPingMethod) == null) {
          NodeCommunicationGrpc.getPingMethod = getPingMethod =
              io.grpc.MethodDescriptor.<com.blockchain.communication.PingRequest, com.blockchain.communication.PingResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Ping"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.communication.PingRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.communication.PingResponse.getDefaultInstance()))
              .setSchemaDescriptor(new NodeCommunicationMethodDescriptorSupplier("Ping"))
              .build();
        }
      }
    }
    return getPingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.blockchain.communication.PBFTMessage,
      com.blockchain.communication.Ack> getSendPBFTMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendPBFTMessage",
      requestType = com.blockchain.communication.PBFTMessage.class,
      responseType = com.blockchain.communication.Ack.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.blockchain.communication.PBFTMessage,
      com.blockchain.communication.Ack> getSendPBFTMessageMethod() {
    io.grpc.MethodDescriptor<com.blockchain.communication.PBFTMessage, com.blockchain.communication.Ack> getSendPBFTMessageMethod;
    if ((getSendPBFTMessageMethod = NodeCommunicationGrpc.getSendPBFTMessageMethod) == null) {
      synchronized (NodeCommunicationGrpc.class) {
        if ((getSendPBFTMessageMethod = NodeCommunicationGrpc.getSendPBFTMessageMethod) == null) {
          NodeCommunicationGrpc.getSendPBFTMessageMethod = getSendPBFTMessageMethod =
              io.grpc.MethodDescriptor.<com.blockchain.communication.PBFTMessage, com.blockchain.communication.Ack>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SendPBFTMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.communication.PBFTMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.communication.Ack.getDefaultInstance()))
              .setSchemaDescriptor(new NodeCommunicationMethodDescriptorSupplier("SendPBFTMessage"))
              .build();
        }
      }
    }
    return getSendPBFTMessageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.blockchain.communication.TwoPhaseCommitMessage,
      com.blockchain.communication.Ack> getSend2PCMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Send2PCMessage",
      requestType = com.blockchain.communication.TwoPhaseCommitMessage.class,
      responseType = com.blockchain.communication.Ack.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.blockchain.communication.TwoPhaseCommitMessage,
      com.blockchain.communication.Ack> getSend2PCMessageMethod() {
    io.grpc.MethodDescriptor<com.blockchain.communication.TwoPhaseCommitMessage, com.blockchain.communication.Ack> getSend2PCMessageMethod;
    if ((getSend2PCMessageMethod = NodeCommunicationGrpc.getSend2PCMessageMethod) == null) {
      synchronized (NodeCommunicationGrpc.class) {
        if ((getSend2PCMessageMethod = NodeCommunicationGrpc.getSend2PCMessageMethod) == null) {
          NodeCommunicationGrpc.getSend2PCMessageMethod = getSend2PCMessageMethod =
              io.grpc.MethodDescriptor.<com.blockchain.communication.TwoPhaseCommitMessage, com.blockchain.communication.Ack>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Send2PCMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.communication.TwoPhaseCommitMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.communication.Ack.getDefaultInstance()))
              .setSchemaDescriptor(new NodeCommunicationMethodDescriptorSupplier("Send2PCMessage"))
              .build();
        }
      }
    }
    return getSend2PCMessageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.blockchain.communication.VoteMessage,
      com.blockchain.communication.Ack> getSendVoteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendVote",
      requestType = com.blockchain.communication.VoteMessage.class,
      responseType = com.blockchain.communication.Ack.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.blockchain.communication.VoteMessage,
      com.blockchain.communication.Ack> getSendVoteMethod() {
    io.grpc.MethodDescriptor<com.blockchain.communication.VoteMessage, com.blockchain.communication.Ack> getSendVoteMethod;
    if ((getSendVoteMethod = NodeCommunicationGrpc.getSendVoteMethod) == null) {
      synchronized (NodeCommunicationGrpc.class) {
        if ((getSendVoteMethod = NodeCommunicationGrpc.getSendVoteMethod) == null) {
          NodeCommunicationGrpc.getSendVoteMethod = getSendVoteMethod =
              io.grpc.MethodDescriptor.<com.blockchain.communication.VoteMessage, com.blockchain.communication.Ack>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SendVote"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.communication.VoteMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.blockchain.communication.Ack.getDefaultInstance()))
              .setSchemaDescriptor(new NodeCommunicationMethodDescriptorSupplier("SendVote"))
              .build();
        }
      }
    }
    return getSendVoteMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static NodeCommunicationStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<NodeCommunicationStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<NodeCommunicationStub>() {
        @java.lang.Override
        public NodeCommunicationStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new NodeCommunicationStub(channel, callOptions);
        }
      };
    return NodeCommunicationStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static NodeCommunicationBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<NodeCommunicationBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<NodeCommunicationBlockingStub>() {
        @java.lang.Override
        public NodeCommunicationBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new NodeCommunicationBlockingStub(channel, callOptions);
        }
      };
    return NodeCommunicationBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static NodeCommunicationFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<NodeCommunicationFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<NodeCommunicationFutureStub>() {
        @java.lang.Override
        public NodeCommunicationFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new NodeCommunicationFutureStub(channel, callOptions);
        }
      };
    return NodeCommunicationFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void ping(com.blockchain.communication.PingRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.communication.PingResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPingMethod(), responseObserver);
    }

    /**
     */
    default void sendPBFTMessage(com.blockchain.communication.PBFTMessage request,
        io.grpc.stub.StreamObserver<com.blockchain.communication.Ack> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSendPBFTMessageMethod(), responseObserver);
    }

    /**
     */
    default void send2PCMessage(com.blockchain.communication.TwoPhaseCommitMessage request,
        io.grpc.stub.StreamObserver<com.blockchain.communication.Ack> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSend2PCMessageMethod(), responseObserver);
    }

    /**
     */
    default void sendVote(com.blockchain.communication.VoteMessage request,
        io.grpc.stub.StreamObserver<com.blockchain.communication.Ack> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSendVoteMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service NodeCommunication.
   */
  public static abstract class NodeCommunicationImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return NodeCommunicationGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service NodeCommunication.
   */
  public static final class NodeCommunicationStub
      extends io.grpc.stub.AbstractAsyncStub<NodeCommunicationStub> {
    private NodeCommunicationStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NodeCommunicationStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new NodeCommunicationStub(channel, callOptions);
    }

    /**
     */
    public void ping(com.blockchain.communication.PingRequest request,
        io.grpc.stub.StreamObserver<com.blockchain.communication.PingResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void sendPBFTMessage(com.blockchain.communication.PBFTMessage request,
        io.grpc.stub.StreamObserver<com.blockchain.communication.Ack> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSendPBFTMessageMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void send2PCMessage(com.blockchain.communication.TwoPhaseCommitMessage request,
        io.grpc.stub.StreamObserver<com.blockchain.communication.Ack> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSend2PCMessageMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void sendVote(com.blockchain.communication.VoteMessage request,
        io.grpc.stub.StreamObserver<com.blockchain.communication.Ack> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSendVoteMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service NodeCommunication.
   */
  public static final class NodeCommunicationBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<NodeCommunicationBlockingStub> {
    private NodeCommunicationBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NodeCommunicationBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new NodeCommunicationBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.blockchain.communication.PingResponse ping(com.blockchain.communication.PingRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPingMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.blockchain.communication.Ack sendPBFTMessage(com.blockchain.communication.PBFTMessage request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSendPBFTMessageMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.blockchain.communication.Ack send2PCMessage(com.blockchain.communication.TwoPhaseCommitMessage request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSend2PCMessageMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.blockchain.communication.Ack sendVote(com.blockchain.communication.VoteMessage request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSendVoteMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service NodeCommunication.
   */
  public static final class NodeCommunicationFutureStub
      extends io.grpc.stub.AbstractFutureStub<NodeCommunicationFutureStub> {
    private NodeCommunicationFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NodeCommunicationFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new NodeCommunicationFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.blockchain.communication.PingResponse> ping(
        com.blockchain.communication.PingRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.blockchain.communication.Ack> sendPBFTMessage(
        com.blockchain.communication.PBFTMessage request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSendPBFTMessageMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.blockchain.communication.Ack> send2PCMessage(
        com.blockchain.communication.TwoPhaseCommitMessage request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSend2PCMessageMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.blockchain.communication.Ack> sendVote(
        com.blockchain.communication.VoteMessage request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSendVoteMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PING = 0;
  private static final int METHODID_SEND_PBFTMESSAGE = 1;
  private static final int METHODID_SEND2PCMESSAGE = 2;
  private static final int METHODID_SEND_VOTE = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PING:
          serviceImpl.ping((com.blockchain.communication.PingRequest) request,
              (io.grpc.stub.StreamObserver<com.blockchain.communication.PingResponse>) responseObserver);
          break;
        case METHODID_SEND_PBFTMESSAGE:
          serviceImpl.sendPBFTMessage((com.blockchain.communication.PBFTMessage) request,
              (io.grpc.stub.StreamObserver<com.blockchain.communication.Ack>) responseObserver);
          break;
        case METHODID_SEND2PCMESSAGE:
          serviceImpl.send2PCMessage((com.blockchain.communication.TwoPhaseCommitMessage) request,
              (io.grpc.stub.StreamObserver<com.blockchain.communication.Ack>) responseObserver);
          break;
        case METHODID_SEND_VOTE:
          serviceImpl.sendVote((com.blockchain.communication.VoteMessage) request,
              (io.grpc.stub.StreamObserver<com.blockchain.communication.Ack>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getPingMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.blockchain.communication.PingRequest,
              com.blockchain.communication.PingResponse>(
                service, METHODID_PING)))
        .addMethod(
          getSendPBFTMessageMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.blockchain.communication.PBFTMessage,
              com.blockchain.communication.Ack>(
                service, METHODID_SEND_PBFTMESSAGE)))
        .addMethod(
          getSend2PCMessageMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.blockchain.communication.TwoPhaseCommitMessage,
              com.blockchain.communication.Ack>(
                service, METHODID_SEND2PCMESSAGE)))
        .addMethod(
          getSendVoteMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.blockchain.communication.VoteMessage,
              com.blockchain.communication.Ack>(
                service, METHODID_SEND_VOTE)))
        .build();
  }

  private static abstract class NodeCommunicationBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    NodeCommunicationBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.blockchain.communication.CommunicationProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("NodeCommunication");
    }
  }

  private static final class NodeCommunicationFileDescriptorSupplier
      extends NodeCommunicationBaseDescriptorSupplier {
    NodeCommunicationFileDescriptorSupplier() {}
  }

  private static final class NodeCommunicationMethodDescriptorSupplier
      extends NodeCommunicationBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    NodeCommunicationMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (NodeCommunicationGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new NodeCommunicationFileDescriptorSupplier())
              .addMethod(getPingMethod())
              .addMethod(getSendPBFTMessageMethod())
              .addMethod(getSend2PCMessageMethod())
              .addMethod(getSendVoteMethod())
              .build();
        }
      }
    }
    return result;
  }
}
