package io.grpc.examples.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.examples.service.UserServiceImpl;

import java.io.IOException;

/**
 * @Author: zjfan
 * @Description:
 * @Date: Created in 21:21 2021/2/5
 */
public class GrpcServer {
    private Server server;

    private void start() throws IOException {
        this.server = ServerBuilder
                .forPort(8888)
                .addService(new UserServiceImpl())
                .build()
                .start();

        System.out.println("server started!");

        //这是在服务端jvm关闭之前主动退出grpc服务，且关闭其相应的资源
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("close server");
            GrpcServer.this.stop();
        }));
    }

    private void stop(){
        if(null != this.server){
            this.server.shutdown();
        }
    }

    //让服务启动后处于等待状态，不然服务已启动马上就停止了
    private void awaitTermination() throws InterruptedException {
        if(null != this.server){
            this.server.awaitTermination();
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        GrpcServer grpcServer = new GrpcServer();

        grpcServer.start();
        grpcServer.awaitTermination();
    }
}
