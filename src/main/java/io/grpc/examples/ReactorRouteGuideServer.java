package io.grpc.examples;

import com.salesforce.grpc.contrib.spring.GrpcServerHost;
import com.salesforce.grpc.contrib.spring.GrpcService;
import io.grpc.examples.routeguide.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * Demonstrates building a gRPC streaming server using Reactor, Reactive-Grpc, grpc-spring, and Spring Boot.
 */
@SpringBootApplication
public class ReactorRouteGuideServer {

    private final Logger logger = LoggerFactory.getLogger(ReactorRouteGuideServer.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ReactorRouteGuideServer.class, args);
        Thread.currentThread().join();
    }

    /**
     * Start gRPC server.
     */
    @Bean(initMethod = "start")
    public GrpcServerHost routeGuideGrpcServerHost(@Value("${port3}") int port) {
        logger.info("Listening for gRPC on port {}", port);
        return new GrpcServerHost(port);
    }

    /**
     * The controller instance.
     */
    @Bean
    public ReactorRouteGuideGrpc.RouteGuideImplBase reactorRouteGuideImpl() {
        return new RouteGuideImpl();
    }
}

/**
 * gRPC server
 */
@GrpcService
class RouteGuideImpl extends ReactorRouteGuideGrpc.RouteGuideImplBase {

    @Override
    public Mono<Feature> getFeature(Mono<Point> request) {
        return request
                .map(point -> Feature.newBuilder()
                        .setName(String.valueOf(point.getLatitude()))
                        .build())
                .log();
    }

    @Override
    public Flux<Feature> listFeatures(Mono<Rectangle> request) {
        return Flux
                // create a new indexed Flux emitting one element every second
                .interval(Duration.ofSeconds(1))
                // create a Flux of new Messages using the indexed Flux
                .map(index -> Feature.newBuilder()
                        .setName("Time" + System.currentTimeMillis())
                        .build())
                .log();
    }

    @Override
    public Mono<RouteSummary> recordRoute(Flux<Point> request) {
        return request
                .map(point -> RouteSummary.newBuilder()
                        .setPointCount(point.getLatitude())
                        .build())
                // backpressure
                .limitRate(10)
                .log()
                .then(Mono.just(
                        RouteSummary.newBuilder()
                                .setPointCount((int) (Math.random() * 100))
                                .build()));
    }

    @Override
    public Flux<RouteNote> routeChat(Flux<RouteNote> request) {
        return request
                .map(routeNote -> RouteNote.newBuilder()
                        .setMessage(routeNote.getMessage())
                        .build())
                // backpressure
                .limitRate(10)
                .log();
    }
}
