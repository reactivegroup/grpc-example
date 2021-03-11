package io.grpc.examples;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.examples.routeguide.Point;
import io.grpc.examples.routeguide.ReactorRouteGuideGrpc;
import io.grpc.examples.routeguide.Rectangle;
import io.grpc.examples.routeguide.RouteNote;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates building a gRPC streaming client using Reactor and Reactive-Grpc.
 */
public class ReactorRouteGuideClient {

    /**
     * Localhost gRPC port
     */
    private static final int PORT = 50053;

    private ReactorRouteGuideClient() {
    }

    public static void main(String[] args) throws Exception {
        // Connect to the sever
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", PORT)
                .usePlaintext()
                .build();
        // Create Reactor stub
        ReactorRouteGuideGrpc.ReactorRouteGuideStub stub = ReactorRouteGuideGrpc.newReactorStub(channel);

        CountDownLatch done = new CountDownLatch(4);
        String name = "grpc-client";

        // Subscribe messages
        Disposable disposable1 = stub
                .getFeature(Mono.just(
                        Point.newBuilder()
                                .setLatitude((int) (Math.random() * 100))
                                .build()))
                .subscribe(feature -> {
                            System.out.println("[getFeature] message: " + feature.getName());
                        },
                        throwable -> {
                            System.out.println("[getFeature] error: " + throwable.getMessage());
                            done.countDown();
                        },
                        done::countDown);
        // Subscribe messages
        Disposable disposable2 = stub
                .listFeatures(Mono.just(
                        Rectangle.newBuilder()
                                .build()))
                .subscribe(feature -> {
                            System.out.println("[listFeatures] message: " + feature.getName());
                        },
                        throwable -> {
                            System.out.println("[listFeatures] error: " + throwable.getMessage());
                            done.countDown();
                        },
                        done::countDown);
        // Subscribe messages
        Disposable disposable3 = stub
                .recordRoute(Flux
                        .range(1, 100)
                        .map(msg -> Point.newBuilder()
                                .setLatitude(msg)
                                .build()))
                .subscribe(routeSummary -> {
                            System.out.println("[recordRoute] message: " + routeSummary.getPointCount());
                        },
                        throwable -> {
                            System.out.println("[recordRoute] error: " + throwable.getMessage());
                            done.countDown();
                        },
                        done::countDown);
        // Subscribe messages
        Disposable disposable4 = stub
                .routeChat(Flux
                        .range(1, 100)
                        .map(msg -> RouteNote.newBuilder()
                                .setMessage(name + msg)
                                .build()))
                .subscribe(routeNote -> {
                            System.out.println("[routeChat] message: " + routeNote.getMessage());
                        },
                        throwable -> {
                            System.out.println("[routeChat] error: " + throwable.getMessage());
                            done.countDown();
                        },
                        done::countDown);

        // Wait for a signal to exit, then clean up
        done.await();
        disposable1.dispose();
        disposable2.dispose();
        disposable3.dispose();
        disposable4.dispose();
        channel.shutdown();
        channel.awaitTermination(1, TimeUnit.SECONDS);
    }
}
