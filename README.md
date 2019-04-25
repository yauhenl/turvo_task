[![Build Status](https://travis-ci.org/yauhenl/turvo_task.svg?branch=master)](https://travis-ci.org/yauhenl/turvo_task)

# City Distance Service

Build a micro­service which calculates the distance between two cities. 

The micro­service will contain two endpoints:
one endpoint which will allow for users to define a distance between two arbitrary cities, and a second endpoint that allows the user to retrieve the distance between two cities.

The endpoint which calculates distance between cities should be able to work with cities which are not directly connected: i.e. if a query wants a distance between A and D, there may be a definition for A ­ B (5 miles), and B ­ C (10 miles), and C ­ D (1 mile).

The query endpoint should be able to produce an output for the above inputs of 16 (5 + 10 + 1).

The output of the query endpoint should also include the path which is necessary to take which covers the specified distance.

So in the above example, the service must return the cities A, B, C, and D, along with the total distance.
If there are MULTIPLE paths from A to D in the example above, the query endpoint will return ALL of the paths from A to D along with their computed distance.
If there is no connection between cities A and D (e.g. San Francisco to Tokyo), your service will return an error.

Points to consider:
- you have a lot of freedom of how you want to design your restful api: it's all up to you how you design it.
- don't worry about scalability, this is a toy problem. assume you can have a single JVM process which scales to infinite amount of memory and CPU processing
- how do you guarantee that concurrent writes and reads do not cause inconsistencies in your queries? this is important. consider the performance impacts.
- use Spring Boot to speed up your development
- write up a half­page/page with bullet points about what decisions you took and why

# Bullet poins

I made decision use jgrapht library for solve this task because I don't have deep knowledge in graph theory. Also it's very common problem as I see in google.

For finding all routes between two cities I'm using KShortestSimplePaths class. 
The algorithm inside determines the k shortest simple paths in increasing order of weight. 
The algorithm is a variant of the Bellman-Ford algorithm but instead of only storing the best path it stores the "k" best paths at each pass, yielding a complexity of $O(k \cdot n \cdot * (m^2))$ where $m$ is the number of edges and $n$ is the number of vertices.

Before start searching of all paths I do the copy of main graph and do the search on copy.
Also I made method for adding cities in graph synchronized

For increasing search performance we can organize some cache with calculated answers. For example we can use ConcurrentHashMap with several versions of graphs and cached answers for them.  