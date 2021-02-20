package io.grpc.examples;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.examples.helloworld.HelloRequest;
import io.grpc.examples.helloworld.ReactorGreeterGrpc;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates building a gRPC streaming client using Reactor and Reactive-Grpc.
 */
public class ReactorHelloWorldClient {

    /**
     * Localhost gRPC port
     */
    private static final int PORT = 9999;

    private ReactorHelloWorldClient() {
    }

    public static void main(String[] args) throws Exception {
        // Connect to the sever
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", PORT)
                .usePlaintext()
                .build();
        // Create Reactor stub
        ReactorGreeterGrpc.ReactorGreeterStub stub = ReactorGreeterGrpc.newReactorStub(channel);

        CountDownLatch done = new CountDownLatch(1);
        String name = "grpc-client";

        // Subscribe message
        Disposable chatSubscription = stub.sayHello(toMessage(name))
                .subscribe(
                        helloReply -> {
                            System.out.println("HelloReply message: " + helloReply.getMessage());
                        },
                        throwable -> {
                            System.out.println("HelloReply error: " + throwable.getMessage());
                            done.countDown();
                        },
                        done::countDown);

        // Wait for a signal to exit, then clean up
        done.await();
        chatSubscription.dispose();
        channel.shutdown();
        channel.awaitTermination(1, TimeUnit.SECONDS);
    }

    private static Mono<HelloRequest> toMessage(String name) {
        return Mono.just(
                HelloRequest.newBuilder()
                        .setName(name)
                        .build());
    }
}
