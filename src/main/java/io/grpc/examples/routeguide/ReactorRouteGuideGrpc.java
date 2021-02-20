package io.grpc.examples.routeguide;

import static io.grpc.examples.routeguide.RouteGuideGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.*;


@javax.annotation.Generated(
value = "by ReactorGrpc generator",
comments = "Source: route_guide.proto")
public final class ReactorRouteGuideGrpc {
    private ReactorRouteGuideGrpc() {}

    public static ReactorRouteGuideStub newReactorStub(io.grpc.Channel channel) {
        return new ReactorRouteGuideStub(channel);
    }

    /**
     * <pre>
     *  Interface exported by the server.
     * </pre>
     */
    public static final class ReactorRouteGuideStub extends io.grpc.stub.AbstractStub<ReactorRouteGuideStub> {
        private RouteGuideGrpc.RouteGuideStub delegateStub;

        private ReactorRouteGuideStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = RouteGuideGrpc.newStub(channel);
        }

        private ReactorRouteGuideStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = RouteGuideGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected ReactorRouteGuideStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ReactorRouteGuideStub(channel, callOptions);
        }

        /**
         * <pre>
         *  A simple RPC.
         * 
         *  Obtains the feature at a given position.
         * 
         *  A feature with an empty name is returned if there&#39;s no feature at the given
         *  position.
         * </pre>
         */
        public reactor.core.publisher.Mono<Feature> getFeature(reactor.core.publisher.Mono<Point> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::getFeature);
        }

        /**
         * <pre>
         *  A server-to-client streaming RPC.
         * 
         *  Obtains the Features available within the given Rectangle.  Results are
         *  streamed rather than returned at once (e.g. in a response message with a
         *  repeated field), as the rectangle may cover a large area and contain a
         *  huge number of features.
         * </pre>
         */
        public reactor.core.publisher.Flux<Feature> listFeatures(reactor.core.publisher.Mono<Rectangle> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToMany(reactorRequest, delegateStub::listFeatures);
        }

        /**
         * <pre>
         *  A client-to-server streaming RPC.
         * 
         *  Accepts a stream of Points on a route being traversed, returning a
         *  RouteSummary when traversal is completed.
         * </pre>
         */
        public reactor.core.publisher.Mono<RouteSummary> recordRoute(reactor.core.publisher.Flux<Point> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.manyToOne(reactorRequest, delegateStub::recordRoute);
        }

        /**
         * <pre>
         *  A Bidirectional streaming RPC.
         * 
         *  Accepts a stream of RouteNotes sent while a route is being traversed,
         *  while receiving other RouteNotes (e.g. from other users).
         * </pre>
         */
        public reactor.core.publisher.Flux<RouteNote> routeChat(reactor.core.publisher.Flux<RouteNote> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.manyToMany(reactorRequest, delegateStub::routeChat);
        }

        /**
         * <pre>
         *  A simple RPC.
         * 
         *  Obtains the feature at a given position.
         * 
         *  A feature with an empty name is returned if there&#39;s no feature at the given
         *  position.
         * </pre>
         */
        public reactor.core.publisher.Mono<Feature> getFeature(Point reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::getFeature);
        }

        /**
         * <pre>
         *  A server-to-client streaming RPC.
         * 
         *  Obtains the Features available within the given Rectangle.  Results are
         *  streamed rather than returned at once (e.g. in a response message with a
         *  repeated field), as the rectangle may cover a large area and contain a
         *  huge number of features.
         * </pre>
         */
        public reactor.core.publisher.Flux<Feature> listFeatures(Rectangle reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToMany(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::listFeatures);
        }

    }

    /**
     * <pre>
     *  Interface exported by the server.
     * </pre>
     */
    public static abstract class RouteGuideImplBase implements io.grpc.BindableService {

        /**
         * <pre>
         *  A simple RPC.
         * 
         *  Obtains the feature at a given position.
         * 
         *  A feature with an empty name is returned if there&#39;s no feature at the given
         *  position.
         * </pre>
         */
        public reactor.core.publisher.Mono<Feature> getFeature(reactor.core.publisher.Mono<Point> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         *  A server-to-client streaming RPC.
         * 
         *  Obtains the Features available within the given Rectangle.  Results are
         *  streamed rather than returned at once (e.g. in a response message with a
         *  repeated field), as the rectangle may cover a large area and contain a
         *  huge number of features.
         * </pre>
         */
        public reactor.core.publisher.Flux<Feature> listFeatures(reactor.core.publisher.Mono<Rectangle> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         *  A client-to-server streaming RPC.
         * 
         *  Accepts a stream of Points on a route being traversed, returning a
         *  RouteSummary when traversal is completed.
         * </pre>
         */
        public reactor.core.publisher.Mono<RouteSummary> recordRoute(reactor.core.publisher.Flux<Point> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         *  A Bidirectional streaming RPC.
         * 
         *  Accepts a stream of RouteNotes sent while a route is being traversed,
         *  while receiving other RouteNotes (e.g. from other users).
         * </pre>
         */
        public reactor.core.publisher.Flux<RouteNote> routeChat(reactor.core.publisher.Flux<RouteNote> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @Override public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            RouteGuideGrpc.getGetFeatureMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            Point,
                                            Feature>(
                                            this, METHODID_GET_FEATURE)))
                    .addMethod(
                            RouteGuideGrpc.getListFeaturesMethod(),
                            asyncServerStreamingCall(
                                    new MethodHandlers<
                                            Rectangle,
                                            Feature>(
                                            this, METHODID_LIST_FEATURES)))
                    .addMethod(
                            RouteGuideGrpc.getRecordRouteMethod(),
                            asyncClientStreamingCall(
                                    new MethodHandlers<
                                            Point,
                                            RouteSummary>(
                                            this, METHODID_RECORD_ROUTE)))
                    .addMethod(
                            RouteGuideGrpc.getRouteChatMethod(),
                            asyncBidiStreamingCall(
                                    new MethodHandlers<
                                            RouteNote,
                                            RouteNote>(
                                            this, METHODID_ROUTE_CHAT)))
                    .build();
        }
    }

    private static final int METHODID_GET_FEATURE = 0;
    private static final int METHODID_LIST_FEATURES = 1;
    private static final int METHODID_RECORD_ROUTE = 2;
    private static final int METHODID_ROUTE_CHAT = 3;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final RouteGuideImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(RouteGuideImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @Override
        @SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_GET_FEATURE:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((Point) request,
                            (io.grpc.stub.StreamObserver<Feature>) responseObserver,
                            serviceImpl::getFeature);
                    break;
                case METHODID_LIST_FEATURES:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToMany((Rectangle) request,
                            (io.grpc.stub.StreamObserver<Feature>) responseObserver,
                            serviceImpl::listFeatures);
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_RECORD_ROUTE:
                    return (io.grpc.stub.StreamObserver<Req>) com.salesforce.reactorgrpc.stub.ServerCalls.manyToOne(
                            (io.grpc.stub.StreamObserver<RouteSummary>) responseObserver,
                            serviceImpl::recordRoute);
                case METHODID_ROUTE_CHAT:
                    return (io.grpc.stub.StreamObserver<Req>) com.salesforce.reactorgrpc.stub.ServerCalls.manyToMany(
                            (io.grpc.stub.StreamObserver<RouteNote>) responseObserver,
                            serviceImpl::routeChat);
                default:
                    throw new AssertionError();
            }
        }
    }

}
