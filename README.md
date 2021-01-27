## Overview

gRPC is an RPC framework that is based around the idea of defining a service, specifying the methods that can be called remotely with their parameters and return types. 
Both server-side and client-side can be implemented by different programming languages. [More details](https://grpc.io/docs/what-is-grpc/introduction/).

![Call Relationship Between Server-side and Client-side](doc/grpc-server-and-clent-relationship.svg)

## How gRPC works

### How to build

This example will help you understand gRPC better.

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
