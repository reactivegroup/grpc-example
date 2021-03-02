package io.grpc.examples.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.examples.user.*;
import io.grpc.stub.StreamObserver;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zjfan
 * @Description:
 * @Date: Created in 21:23 2021/2/5
 */
public class GrpcClient {
    public static void main(String[] args) throws InterruptedException {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost",8888)
                .usePlaintext().build();
        UserServiceGrpc.UserServiceBlockingStub blockingStub = UserServiceGrpc.newBlockingStub(managedChannel);

        // 获取单个用户
        GetUserResponse response = blockingStub.getUser(GetUserRequest.newBuilder().setId(3).build());
        System.out.println(response.getUser().getName());

        // 获取用户列表
        Iterator<User> allUser = blockingStub.getUserList(GetUserListRequest.newBuilder().setPage(1).setSize(5).build());
        while (allUser.hasNext()){
            User user = allUser.next();
            System.out.println("name: " + user.getName());
        }

        // 间隔一定时间获取用户
        UserServiceGrpc.UserServiceStub stub = UserServiceGrpc.newStub(managedChannel);

        StreamObserver<GetUserRequest> requestStreamObserver = stub.getUserOneByOne(new StreamObserver<User>() {
            @Override
            public void onNext(User value) {
                // 模拟间断请求
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("name: " + value.getName());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                System.out.println("Completed!");
            }
        });

        for(int i=5; i<=10; i++) {
            requestStreamObserver.onNext(GetUserRequest.newBuilder().setId(i).build());
        }

        requestStreamObserver.onCompleted();

        TimeUnit.SECONDS.sleep(30);

    }
}
