## Overview

This example will help you understand gRPC better. More importantly, You can learn more about how gRPC works. 

## How to build

There are two steps as follows

+  Install `protoc`
    - download [protocol buffers](https://developers.google.com/protocol-buffers/)
    - `tar -xvf protobuf`
    - `cd protobuf`
    - `./configure --prefix=/usr/local/opt/protobuf --disable-shared`
    - `make`
    - `make check`
    - `make install`
+ Build project 
    - run `protoc src/main/proto/helloworld.proto --java_out=./src/main/java/`
    - open `targe->generated-sources->protobuf` folder, you can find `GreeterGrpc.java` file in `grpc-java` folder and all files in `java` folder, after that you can copy them into your project
    - run `mvn clean org.xolstice.maven.plugins:protobuf-maven-plugin:compile org.xolstice.maven.plugins:protobuf-maven-plugin:compile-custom`
    - run `HelloWorldServer#main()`
    - run `HelloWorldClient#main()`
    
    
## What you can learn

+ [RPC introduction](doc/RPC-INTRODUCTION.md)
+ [How to build gRPC server-side](doc/HOW-TO-BUILD-GRPC-SERVER-SIDE.md)
+ [How the gRPC server-side works](doc/HOW-THE-GRPC-SERVER-SIDE-WORKS.md)


### Server

+ ServerBuilder: Create a `Server` instance, which will call `ServerProvider` directly.

+ ServerProvider: Create a `NettyServerProvider` as default, which will call `ServerRegistry`.

+ ServerRegistry: Create a `ServerRegistry` instance using `ServerRegistry#getDefaultRegistry()` and `ServerRegistry` will add all available `ServerProvider` instances by calling `ServiceProviders#loadAll(Class<T>, Iterable<Class<?>>, ClassLoader, PriorityAccessor<T>)`

+ ServiceProviders: Call `ServiceLoader#load(Class<T>, ClassLoader)` to create `NettyServerProvider` using SPI(`grpc-netty-shaded-1.35.0.jar!/META-INF/services/io.grpc.ManagedChannelProvider`) and Reflection

+ NettyServer: `ServerBuilder#start()` will call `NettyServer#start()` to let server work well


### Client

+ ManagedChannelProvider: create a `NettyChannelProvider` instance by using  SPI(`grpc-netty-shaded-1.35.0.jar!/META-INF/services/io.grpc.ManagedChannelProvider`) and Reflection

+ NettyChannelBuilder: create a channel that will connect with `NettyServer`

+ ClientCalls: process different `Request`

## Contributing

[How to contribute](./CONTRIBUTING.md)
