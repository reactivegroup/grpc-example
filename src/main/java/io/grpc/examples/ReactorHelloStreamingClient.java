package io.grpc.examples;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.examples.manualflowcontrol.HelloRequest;
import io.grpc.examples.manualflowcontrol.ReactorStreamingGreeterGrpc;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates building a gRPC streaming client using Reactor and Reactive-Grpc.
 */
public class ReactorHelloStreamingClient {

    /**
     * Localhost gRPC port
     */
    private static final int PORT = 50052;

    private ReactorHelloStreamingClient() {
    }

    public static void main(String[] args) throws Exception {
        // Connect to the sever
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", PORT)
                .usePlaintext()
                .build();
        // Create Reactor stub
        ReactorStreamingGreeterGrpc.ReactorStreamingGreeterStub stub = ReactorStreamingGreeterGrpc.newReactorStub(channel);

        CountDownLatch done = new CountDownLatch(1);
        String name = "grpc-client";

        Flux<HelloRequest> helloRequestFlux = Flux
                .range(1, 100)
                .map(msg -> toMessage(name + msg));

        // Subscribe messages
        Disposable disposable = stub.sayHelloStreaming(helloRequestFlux)
                .subscribe(helloReply -> {
                            System.out.println("HelloReply message: " + helloReply.getMessage());
                        },
                        throwable -> {
                            System.out.println("HelloReply error: " + throwable.getMessage());
                            done.countDown();
                        },
                        done::countDown);

        // Wait for a signal to exit, then clean up
        done.await();
        disposable.dispose();
        channel.shutdown();
        channel.awaitTermination(1, TimeUnit.SECONDS);
    }

    private static HelloRequest toMessage(String name) {
        return HelloRequest.newBuilder()
                .setName(name)
                .build();
    }
}
