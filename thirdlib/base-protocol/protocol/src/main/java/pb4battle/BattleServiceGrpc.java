package pb4battle;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.4.0)",
    comments = "Source: battleMsg.proto")
public final class BattleServiceGrpc {

  private BattleServiceGrpc() {}

  public static final String SERVICE_NAME = "pb4battle.BattleService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<pb4battle.BattleMsg.DoHeroFightReq,
      pb4battle.BattleMsg.DoHeroFightResp> METHOD_DO_HERO_FIGHT =
      io.grpc.MethodDescriptor.<pb4battle.BattleMsg.DoHeroFightReq, pb4battle.BattleMsg.DoHeroFightResp>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "pb4battle.BattleService", "doHeroFight"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              pb4battle.BattleMsg.DoHeroFightReq.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              pb4battle.BattleMsg.DoHeroFightResp.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<pb4battle.BattleMsg.CheckHeroFightReq,
      pb4battle.BattleMsg.CheckHeroFightResp> METHOD_CHECK_HERO_FIGHT =
      io.grpc.MethodDescriptor.<pb4battle.BattleMsg.CheckHeroFightReq, pb4battle.BattleMsg.CheckHeroFightResp>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "pb4battle.BattleService", "CheckHeroFight"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              pb4battle.BattleMsg.CheckHeroFightReq.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              pb4battle.BattleMsg.CheckHeroFightResp.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static BattleServiceStub newStub(io.grpc.Channel channel) {
    return new BattleServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static BattleServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new BattleServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static BattleServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new BattleServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class BattleServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void doHeroFight(pb4battle.BattleMsg.DoHeroFightReq request,
        io.grpc.stub.StreamObserver<pb4battle.BattleMsg.DoHeroFightResp> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_DO_HERO_FIGHT, responseObserver);
    }

    /**
     */
    public void checkHeroFight(pb4battle.BattleMsg.CheckHeroFightReq request,
        io.grpc.stub.StreamObserver<pb4battle.BattleMsg.CheckHeroFightResp> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CHECK_HERO_FIGHT, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_DO_HERO_FIGHT,
            asyncUnaryCall(
              new MethodHandlers<
                pb4battle.BattleMsg.DoHeroFightReq,
                pb4battle.BattleMsg.DoHeroFightResp>(
                  this, METHODID_DO_HERO_FIGHT)))
          .addMethod(
            METHOD_CHECK_HERO_FIGHT,
            asyncUnaryCall(
              new MethodHandlers<
                pb4battle.BattleMsg.CheckHeroFightReq,
                pb4battle.BattleMsg.CheckHeroFightResp>(
                  this, METHODID_CHECK_HERO_FIGHT)))
          .build();
    }
  }

  /**
   */
  public static final class BattleServiceStub extends io.grpc.stub.AbstractStub<BattleServiceStub> {
    private BattleServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private BattleServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BattleServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new BattleServiceStub(channel, callOptions);
    }

    /**
     */
    public void doHeroFight(pb4battle.BattleMsg.DoHeroFightReq request,
        io.grpc.stub.StreamObserver<pb4battle.BattleMsg.DoHeroFightResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DO_HERO_FIGHT, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void checkHeroFight(pb4battle.BattleMsg.CheckHeroFightReq request,
        io.grpc.stub.StreamObserver<pb4battle.BattleMsg.CheckHeroFightResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CHECK_HERO_FIGHT, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class BattleServiceBlockingStub extends io.grpc.stub.AbstractStub<BattleServiceBlockingStub> {
    private BattleServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private BattleServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BattleServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new BattleServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public pb4battle.BattleMsg.DoHeroFightResp doHeroFight(pb4battle.BattleMsg.DoHeroFightReq request) {
      return blockingUnaryCall(
          getChannel(), METHOD_DO_HERO_FIGHT, getCallOptions(), request);
    }

    /**
     */
    public pb4battle.BattleMsg.CheckHeroFightResp checkHeroFight(pb4battle.BattleMsg.CheckHeroFightReq request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CHECK_HERO_FIGHT, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class BattleServiceFutureStub extends io.grpc.stub.AbstractStub<BattleServiceFutureStub> {
    private BattleServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private BattleServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BattleServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new BattleServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pb4battle.BattleMsg.DoHeroFightResp> doHeroFight(
        pb4battle.BattleMsg.DoHeroFightReq request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DO_HERO_FIGHT, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pb4battle.BattleMsg.CheckHeroFightResp> checkHeroFight(
        pb4battle.BattleMsg.CheckHeroFightReq request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CHECK_HERO_FIGHT, getCallOptions()), request);
    }
  }

  private static final int METHODID_DO_HERO_FIGHT = 0;
  private static final int METHODID_CHECK_HERO_FIGHT = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final BattleServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(BattleServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_DO_HERO_FIGHT:
          serviceImpl.doHeroFight((pb4battle.BattleMsg.DoHeroFightReq) request,
              (io.grpc.stub.StreamObserver<pb4battle.BattleMsg.DoHeroFightResp>) responseObserver);
          break;
        case METHODID_CHECK_HERO_FIGHT:
          serviceImpl.checkHeroFight((pb4battle.BattleMsg.CheckHeroFightReq) request,
              (io.grpc.stub.StreamObserver<pb4battle.BattleMsg.CheckHeroFightResp>) responseObserver);
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

  private static final class BattleServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return pb4battle.BattleMsg.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (BattleServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new BattleServiceDescriptorSupplier())
              .addMethod(METHOD_DO_HERO_FIGHT)
              .addMethod(METHOD_CHECK_HERO_FIGHT)
              .build();
        }
      }
    }
    return result;
  }
}
