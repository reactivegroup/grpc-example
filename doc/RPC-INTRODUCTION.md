## RPC Introduction

A service caller can invoke a remote service provider as if it were a local interface, without caring about the underlying communication details and invocation process.

![RPC Introduction](introduction/rpc-introduction.svg)

## What the gRPC is
gRPC is an RPC framework that is based around the idea of defining a service, specifying the methods that can be called remotely with their parameters and return types.
Both server-side and client-side can be implemented by different programming languages. [More details](https://grpc.io/docs/what-is-grpc/introduction/).

![Call Relationship Between Server-side and Client-side](introduction/grpc-server-and-clent-relationship.svg)