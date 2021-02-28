## How the gRPC client-side works

There are three parts to detail how client-side works as follows

+ Create a client call
+ Convert all operations to `Pending Runnables`
+ Execute all `Pending Runnables`

### Create a client call

In this part, you will know what we need to do before creating a client call.
![Create a client call](how-to-work-in-client-side/how-to-handle-a-request-in-grpc-client-side-newcall.svg)

### Convert all operations to `Pending Runnables`

Separate a `ClientCall` to different `Pending Runnables`.

![Convert all operations to Pending Runnables](how-to-work-in-client-side/how-to-handle-a-request-in-grpc-client-side-futureunarycall.svg)

### Execute all `Pending Runnables`

Now, the client need to execute all `Pending Runnables`.

![Execute all Pending Runnables](how-to-work-in-client-side/how-to-handle-a-request-in-grpc-client-side-executor-waitanddrain.svg)


