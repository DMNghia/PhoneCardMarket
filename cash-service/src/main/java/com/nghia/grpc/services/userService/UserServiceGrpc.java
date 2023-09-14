package com.nghia.grpc.services.userService;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.56.1)",
    comments = "Source: user_services.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class UserServiceGrpc {

  private UserServiceGrpc() {}

  public static final String SERVICE_NAME = "UserService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.nghia.grpc.entities.user.FindUserByIdRequest,
      com.nghia.grpc.entities.user.FindUserResponse> getFindUserByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "findUserById",
      requestType = com.nghia.grpc.entities.user.FindUserByIdRequest.class,
      responseType = com.nghia.grpc.entities.user.FindUserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.nghia.grpc.entities.user.FindUserByIdRequest,
      com.nghia.grpc.entities.user.FindUserResponse> getFindUserByIdMethod() {
    io.grpc.MethodDescriptor<com.nghia.grpc.entities.user.FindUserByIdRequest, com.nghia.grpc.entities.user.FindUserResponse> getFindUserByIdMethod;
    if ((getFindUserByIdMethod = UserServiceGrpc.getFindUserByIdMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getFindUserByIdMethod = UserServiceGrpc.getFindUserByIdMethod) == null) {
          UserServiceGrpc.getFindUserByIdMethod = getFindUserByIdMethod =
              io.grpc.MethodDescriptor.<com.nghia.grpc.entities.user.FindUserByIdRequest, com.nghia.grpc.entities.user.FindUserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "findUserById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.nghia.grpc.entities.user.FindUserByIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.nghia.grpc.entities.user.FindUserResponse.getDefaultInstance()))
              .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("findUserById"))
              .build();
        }
      }
    }
    return getFindUserByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.nghia.grpc.entities.user.FindUserByUsernameRequest,
      com.nghia.grpc.entities.user.FindUserResponse> getFindUserByUsernameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "findUserByUsername",
      requestType = com.nghia.grpc.entities.user.FindUserByUsernameRequest.class,
      responseType = com.nghia.grpc.entities.user.FindUserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.nghia.grpc.entities.user.FindUserByUsernameRequest,
      com.nghia.grpc.entities.user.FindUserResponse> getFindUserByUsernameMethod() {
    io.grpc.MethodDescriptor<com.nghia.grpc.entities.user.FindUserByUsernameRequest, com.nghia.grpc.entities.user.FindUserResponse> getFindUserByUsernameMethod;
    if ((getFindUserByUsernameMethod = UserServiceGrpc.getFindUserByUsernameMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getFindUserByUsernameMethod = UserServiceGrpc.getFindUserByUsernameMethod) == null) {
          UserServiceGrpc.getFindUserByUsernameMethod = getFindUserByUsernameMethod =
              io.grpc.MethodDescriptor.<com.nghia.grpc.entities.user.FindUserByUsernameRequest, com.nghia.grpc.entities.user.FindUserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "findUserByUsername"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.nghia.grpc.entities.user.FindUserByUsernameRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.nghia.grpc.entities.user.FindUserResponse.getDefaultInstance()))
              .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("findUserByUsername"))
              .build();
        }
      }
    }
    return getFindUserByUsernameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.nghia.grpc.entities.user.UpdateUserRequest,
      com.nghia.grpc.entities.user.UpdateUserResponse> getUpdateUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "updateUser",
      requestType = com.nghia.grpc.entities.user.UpdateUserRequest.class,
      responseType = com.nghia.grpc.entities.user.UpdateUserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.nghia.grpc.entities.user.UpdateUserRequest,
      com.nghia.grpc.entities.user.UpdateUserResponse> getUpdateUserMethod() {
    io.grpc.MethodDescriptor<com.nghia.grpc.entities.user.UpdateUserRequest, com.nghia.grpc.entities.user.UpdateUserResponse> getUpdateUserMethod;
    if ((getUpdateUserMethod = UserServiceGrpc.getUpdateUserMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getUpdateUserMethod = UserServiceGrpc.getUpdateUserMethod) == null) {
          UserServiceGrpc.getUpdateUserMethod = getUpdateUserMethod =
              io.grpc.MethodDescriptor.<com.nghia.grpc.entities.user.UpdateUserRequest, com.nghia.grpc.entities.user.UpdateUserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "updateUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.nghia.grpc.entities.user.UpdateUserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.nghia.grpc.entities.user.UpdateUserResponse.getDefaultInstance()))
              .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("updateUser"))
              .build();
        }
      }
    }
    return getUpdateUserMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UserServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserServiceStub>() {
        @java.lang.Override
        public UserServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserServiceStub(channel, callOptions);
        }
      };
    return UserServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UserServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserServiceBlockingStub>() {
        @java.lang.Override
        public UserServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserServiceBlockingStub(channel, callOptions);
        }
      };
    return UserServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UserServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserServiceFutureStub>() {
        @java.lang.Override
        public UserServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserServiceFutureStub(channel, callOptions);
        }
      };
    return UserServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void findUserById(com.nghia.grpc.entities.user.FindUserByIdRequest request,
        io.grpc.stub.StreamObserver<com.nghia.grpc.entities.user.FindUserResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFindUserByIdMethod(), responseObserver);
    }

    /**
     */
    default void findUserByUsername(com.nghia.grpc.entities.user.FindUserByUsernameRequest request,
        io.grpc.stub.StreamObserver<com.nghia.grpc.entities.user.FindUserResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFindUserByUsernameMethod(), responseObserver);
    }

    /**
     */
    default void updateUser(com.nghia.grpc.entities.user.UpdateUserRequest request,
        io.grpc.stub.StreamObserver<com.nghia.grpc.entities.user.UpdateUserResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateUserMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service UserService.
   */
  public static abstract class UserServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return UserServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service UserService.
   */
  public static final class UserServiceStub
      extends io.grpc.stub.AbstractAsyncStub<UserServiceStub> {
    private UserServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserServiceStub(channel, callOptions);
    }

    /**
     */
    public void findUserById(com.nghia.grpc.entities.user.FindUserByIdRequest request,
        io.grpc.stub.StreamObserver<com.nghia.grpc.entities.user.FindUserResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFindUserByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void findUserByUsername(com.nghia.grpc.entities.user.FindUserByUsernameRequest request,
        io.grpc.stub.StreamObserver<com.nghia.grpc.entities.user.FindUserResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFindUserByUsernameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateUser(com.nghia.grpc.entities.user.UpdateUserRequest request,
        io.grpc.stub.StreamObserver<com.nghia.grpc.entities.user.UpdateUserResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateUserMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service UserService.
   */
  public static final class UserServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<UserServiceBlockingStub> {
    private UserServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.nghia.grpc.entities.user.FindUserResponse findUserById(com.nghia.grpc.entities.user.FindUserByIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFindUserByIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.nghia.grpc.entities.user.FindUserResponse findUserByUsername(com.nghia.grpc.entities.user.FindUserByUsernameRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFindUserByUsernameMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.nghia.grpc.entities.user.UpdateUserResponse updateUser(com.nghia.grpc.entities.user.UpdateUserRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateUserMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service UserService.
   */
  public static final class UserServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<UserServiceFutureStub> {
    private UserServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.nghia.grpc.entities.user.FindUserResponse> findUserById(
        com.nghia.grpc.entities.user.FindUserByIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFindUserByIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.nghia.grpc.entities.user.FindUserResponse> findUserByUsername(
        com.nghia.grpc.entities.user.FindUserByUsernameRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFindUserByUsernameMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.nghia.grpc.entities.user.UpdateUserResponse> updateUser(
        com.nghia.grpc.entities.user.UpdateUserRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateUserMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_FIND_USER_BY_ID = 0;
  private static final int METHODID_FIND_USER_BY_USERNAME = 1;
  private static final int METHODID_UPDATE_USER = 2;

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
        case METHODID_FIND_USER_BY_ID:
          serviceImpl.findUserById((com.nghia.grpc.entities.user.FindUserByIdRequest) request,
              (io.grpc.stub.StreamObserver<com.nghia.grpc.entities.user.FindUserResponse>) responseObserver);
          break;
        case METHODID_FIND_USER_BY_USERNAME:
          serviceImpl.findUserByUsername((com.nghia.grpc.entities.user.FindUserByUsernameRequest) request,
              (io.grpc.stub.StreamObserver<com.nghia.grpc.entities.user.FindUserResponse>) responseObserver);
          break;
        case METHODID_UPDATE_USER:
          serviceImpl.updateUser((com.nghia.grpc.entities.user.UpdateUserRequest) request,
              (io.grpc.stub.StreamObserver<com.nghia.grpc.entities.user.UpdateUserResponse>) responseObserver);
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
          getFindUserByIdMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.nghia.grpc.entities.user.FindUserByIdRequest,
              com.nghia.grpc.entities.user.FindUserResponse>(
                service, METHODID_FIND_USER_BY_ID)))
        .addMethod(
          getFindUserByUsernameMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.nghia.grpc.entities.user.FindUserByUsernameRequest,
              com.nghia.grpc.entities.user.FindUserResponse>(
                service, METHODID_FIND_USER_BY_USERNAME)))
        .addMethod(
          getUpdateUserMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.nghia.grpc.entities.user.UpdateUserRequest,
              com.nghia.grpc.entities.user.UpdateUserResponse>(
                service, METHODID_UPDATE_USER)))
        .build();
  }

  private static abstract class UserServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    UserServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.nghia.grpc.services.userService.UserServices.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("UserService");
    }
  }

  private static final class UserServiceFileDescriptorSupplier
      extends UserServiceBaseDescriptorSupplier {
    UserServiceFileDescriptorSupplier() {}
  }

  private static final class UserServiceMethodDescriptorSupplier
      extends UserServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    UserServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (UserServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new UserServiceFileDescriptorSupplier())
              .addMethod(getFindUserByIdMethod())
              .addMethod(getFindUserByUsernameMethod())
              .addMethod(getUpdateUserMethod())
              .build();
        }
      }
    }
    return result;
  }
}
