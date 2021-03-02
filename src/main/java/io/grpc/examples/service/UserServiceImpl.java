package io.grpc.examples.service;

import io.grpc.examples.user.*;
import io.grpc.stub.StreamObserver;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zjfan
 * @Description: 服务实现类
 * @Date: Created in 21:06 2021/2/5
 */
public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase{

    private static Map<Integer, User> userStore = new HashMap<>();

    static {
        for(int i=1; i<=10; i++) {
            userStore.put(i, User.newBuilder().setName("小明" + i + "号").build());
        }
    }

    /**
     * 获取单个用户
     * @param request
     * @param responseObserver
     */
    @Override
    public void getUser(GetUserRequest request, StreamObserver<GetUserResponse> responseObserver) {

        System.out.println("Receive a message from the client： " + request.getId());
        User user = userStore.get(request.getId());
        responseObserver.onNext(GetUserResponse.newBuilder().setUser(user).build());
        responseObserver.onCompleted();
    }

    /**
     * 流式获取用户列表
     * @param request
     * @param responseObserver
     */
    @Override
    public void getUserList(GetUserListRequest request, StreamObserver<User> responseObserver) {
        List<User> userList = new ArrayList<>();
        userList.addAll(userStore.values());
        int startIndex = (request.getPage()-1) * request.getSize();
        int endIndex = request.getPage() * request.getSize();
        endIndex = endIndex > userStore.size() ? userStore.size() : endIndex;
        if(startIndex < userStore.size()){
            Iterator<User> userIterator = userList.subList(startIndex, endIndex).iterator();
            while (userIterator.hasNext()){

                // 模拟耗时操作
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                responseObserver.onNext(userIterator.next());
            }
        }
        responseObserver.onCompleted();
    }

    /**
     * 请求和响应都以流式进行
     * @param responseObserver
     * @return
     */
    @Override
    public StreamObserver<GetUserRequest> getUserOneByOne(StreamObserver<User> responseObserver) {
        return new StreamObserver<GetUserRequest>() {
            @Override
            public void onNext(GetUserRequest request) {
                User user = userStore.get(request.getId());
                responseObserver.onNext(user);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}
