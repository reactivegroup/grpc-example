package io.grpc.examples.manualflowcontrol;

import static io.grpc.examples.manualflowcontrol.StreamingGreeterGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;


@javax.annotation.Generated(
value = "by ReactorGrpc generator",
comments = "Source: hello_streaming.proto")
public final class ReactorStreamingGreeterGrpc {
    private ReactorStreamingGreeterGrpc() {}

    public static ReactorStreamingGreeterStub newReactorStub(io.grpc.Channel channel) {
        return new ReactorStreamingGreeterStub(channel);
    }

    /**
     * <pre>
     *  The greeting service definition.
     * </pre>
     */
    public static final class ReactorStreamingGreeterStub extends io.grpc.stub.AbstractStub<ReactorStreamingGreeterStub> {
        private StreamingGreeterGrpc.StreamingGreeterStub delegateStub;

        private ReactorStreamingGreeterStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = StreamingGreeterGrpc.newStub(channel);
        }

        private ReactorStreamingGreeterStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = StreamingGreeterGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected ReactorStreamingGreeterStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ReactorStreamingGreeterStub(channel, callOptions);
        }

        /**
         * <pre>
         *  Streams a many greetings
         * </pre>
         */
        public reactor.core.publisher.Flux<HelloReply> sayHelloStreaming(reactor.core.publisher.Flux<HelloRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.manyToMany(reactorRequest, delegateStub::sayHelloStreaming);
        }

    }

    /**
     * <pre>
     *  The greeting service definition.
     * </pre>
     */
    public static abstract class StreamingGreeterImplBase implements io.grpc.BindableService {

        /**
         * <pre>
         *  Streams a many greetings
         * </pre>
         */
        public reactor.core.publisher.Flux<HelloReply> sayHelloStreaming(reactor.core.publisher.Flux<HelloRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @Override public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            StreamingGreeterGrpc.getSayHelloStreamingMethod(),
                            asyncBidiStreamingCall(
                                    new MethodHandlers<
                                            HelloRequest,
                                            HelloReply>(
                                            this, METHODID_SAY_HELLO_STREAMING)))
                    .build();
        }
    }

    private static final int METHODID_SAY_HELLO_STREAMING = 0;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final StreamingGreeterImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(StreamingGreeterImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @Override
        @SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                default:
                    throw new AssertionError();
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_SAY_HELLO_STREAMING:
                    return (io.grpc.stub.StreamObserver<Req>) com.salesforce.reactorgrpc.stub.ServerCalls.manyToMany(
                            (io.grpc.stub.StreamObserver<HelloReply>) responseObserver,
                            serviceImpl::sayHelloStreaming);
                default:
                    throw new AssertionError();
            }
        }
    }

}
