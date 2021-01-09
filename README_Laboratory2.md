## Networking Programming
## Laboratory No. 2 

The main purpose of this Laboratory work was to establish communication between Client and Server using UDP, which uses a simple connectionless communication model with a minimum of protocol mechanisms. UDP provides checksums for data integrity, and port numbers for addressing different functions at the source and destination of the datagram.

For running this project, first of all run the Server using this command:

```
java Server port
```
In code is hardcoded for 8888 port number, but it could be changed.

And after that run the Client using this command:

```
java Server host port
```
In my case i run both Server and Client on local machine, so the host = localhost, and the port number is the same 8888.
 
