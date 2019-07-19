# This is "Shortest-Path-Searcher" application.

Using Eureka, Feign Client, Zuul, MySQL.

This application will help you find the shortest path in the graph among the set of its vertices.

___________________________________________

1) At first you need to start Eureka Server
2) Then create schema "distance_db" in MySQL with login: root and password: root
3) Then you need start addDistanceClient on 8081 port (localhost:8081)
4) Then start showDistanceClient (localhost:8082)

Go to the localhost:8080 and add some points.
For example: 
A, B, 100
A, D, 120
B, C, 100
D, C, 80

Go to localhost:8082 and then search the shortest way from A to C.

The second service (showDistanceClient) receives data from the database through the first service (addDistanceClient) by using Feign Client.

If first service (addDistanceClient) will be disable, the second service (showDistanceClient) will show you personal message (not error page 404).
This is achieved by using the Hystrix library from the Netflix stack, which is embedded in the Feign Client.
