package io.grpc.examples;

import com.salesforce.grpc.contrib.spring.GrpcServerHost;
import com.salesforce.grpc.contrib.spring.GrpcService;
import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;
import io.grpc.examples.helloworld.ReactorGreeterGrpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

/**
 * Demonstrates building a gRPC streaming server using Reactor, Reactive-Grpc, grpc-spring, and Spring Boot.
 */
@SpringBootApplication
public class ReactorHelloWorldServer {

    private final Logger logger = LoggerFactory.getLogger(ReactorHelloWorldServer.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ReactorHelloWorldServer.class, args);
        Thread.currentThread().join();
    }

    /**
     * Start gRPC server.
     */
    @Bean(initMethod = "start")
    public GrpcServerHost grpcServerHost(@Value("${port}") int port) {
        logger.info("Listening for gRPC on port {}", port);
        return new GrpcServerHost(port);
    }

    /**
     * The controller instance.
     */
    @Bean
    public ReactorGreeterGrpc.GreeterImplBase reactorGreeterImpl() {
        return new GreeterImpl();
    }
}

/**
 * gRPC server
 */
@GrpcService
class GreeterImpl extends ReactorGreeterGrpc.GreeterImplBase {

    @Override
    public Mono<HelloReply> sayHello(Mono<HelloRequest> request) {
        return request
                .map(helloRequest ->
                        HelloReply.newBuilder()
                                .setMessage(helloRequest.getName())
                                .build())
                .log();
    }
}
