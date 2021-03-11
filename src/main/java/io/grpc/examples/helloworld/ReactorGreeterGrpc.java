package io.grpc.examples.helloworld;

import static io.grpc.examples.helloworld.GreeterGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;


@javax.annotation.Generated(
value = "by ReactorGrpc generator",
comments = "Source: helloworld.proto")
public final class ReactorGreeterGrpc {
    private ReactorGreeterGrpc() {}

    public static ReactorGreeterStub newReactorStub(io.grpc.Channel channel) {
        return new ReactorGreeterStub(channel);
    }

    /**
     * <pre>
     *  The greeting service definition.
     * </pre>
     */
    public static final class ReactorGreeterStub extends io.grpc.stub.AbstractStub<ReactorGreeterStub> {
        private GreeterGrpc.GreeterStub delegateStub;

        private ReactorGreeterStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = GreeterGrpc.newStub(channel);
        }

        private ReactorGreeterStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = GreeterGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected ReactorGreeterStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ReactorGreeterStub(channel, callOptions);
        }

        /**
         * <pre>
         *  Sends a greeting
         * </pre>
         */
        public reactor.core.publisher.Mono<HelloReply> sayHello(reactor.core.publisher.Mono<HelloRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::sayHello);
        }

        /**
         * <pre>
         *  Sends a greeting
         * </pre>
         */
        public reactor.core.publisher.Mono<HelloReply> sayHello(HelloRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::sayHello);
        }

    }

    /**
     * <pre>
     *  The greeting service definition.
     * </pre>
     */
    public static abstract class GreeterImplBase implements io.grpc.BindableService {

        /**
         * <pre>
         *  Sends a greeting
         * </pre>
         */
        public reactor.core.publisher.Mono<HelloReply> sayHello(reactor.core.publisher.Mono<HelloRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @Override public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            GreeterGrpc.getSayHelloMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            HelloRequest,
                                            HelloReply>(
                                            this, METHODID_SAY_HELLO)))
                    .build();
        }
    }

    private static final int METHODID_SAY_HELLO = 0;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final GreeterImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(GreeterImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @Override
        @SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_SAY_HELLO:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((HelloRequest) request,
                            (io.grpc.stub.StreamObserver<HelloReply>) responseObserver,
                            serviceImpl::sayHello);
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                default:
                    throw new AssertionError();
            }
        }
    }

}
