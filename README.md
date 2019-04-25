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
