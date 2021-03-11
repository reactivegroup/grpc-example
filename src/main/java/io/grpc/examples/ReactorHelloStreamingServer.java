package io.grpc.examples;

import com.salesforce.grpc.contrib.spring.GrpcServerHost;
import com.salesforce.grpc.contrib.spring.GrpcService;
import io.grpc.examples.manualflowcontrol.HelloReply;
import io.grpc.examples.manualflowcontrol.HelloRequest;
import io.grpc.examples.manualflowcontrol.ReactorStreamingGreeterGrpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

/**
 * Demonstrates building a gRPC streaming server using Reactor, Reactive-Grpc, grpc-spring, and Spring Boot.
 */
@SpringBootApplication
public class ReactorHelloStreamingServer {

    private final Logger logger = LoggerFactory.getLogger(ReactorHelloStreamingServer.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ReactorHelloStreamingServer.class, args);
        Thread.currentThread().join();
    }

    /**
     * Start gRPC server.
     */
    @Bean(initMethod = "start")
    public GrpcServerHost helloStreamingGrpcServerHost(@Value("${port2}") int port) {
        logger.info("Listening for gRPC on port {}", port);
        return new GrpcServerHost(port);
    }

    /**
     * The controller instance.
     */
    @Bean
    public ReactorStreamingGreeterGrpc.StreamingGreeterImplBase reactorStreamingGreeterImpl() {
        return new StreamingGreeterImpl();
    }
}

/**
 * gRPC server
 */
@GrpcService
class StreamingGreeterImpl extends ReactorStreamingGreeterGrpc.StreamingGreeterImplBase {

    @Override
    public Flux<HelloReply> sayHelloStreaming(Flux<HelloRequest> request) {
        return request
                .map(HelloRequest::getName)
                .buffer(2)
                .map(names -> HelloReply.newBuilder()
                        .setMessage("Hello " + names)
                        .build())
                .log();
    }
}