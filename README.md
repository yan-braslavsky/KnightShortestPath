# Chess Knight Shortest Path Problem (Java)

I recently decided to brush up on my algorithmic skills and started practicing with Codility. Despite my aversion to Codility, I persevered through brain teasers and encountered a particularly challenging problem. After some research, I realized there wasn't a readily available solution, so I decided to share my solution with the community.

You can find a full explanation of this code in my [Blog Post](http://yanbraslavsky.blogspot.de/2016/01/chess-knight-shortest-path-problem-java.html)

![j6NhamL](https://github.com/yan-braslavsky/KnightShortestPath/assets/1155059/bc7b8115-d21f-4d3d-97ff-78eea4e69b2d)



## Problem Definition:

A knight is a chess piece that can move to a square that is two squares horizontally and one square vertically, or two squares vertically and one square horizontally, forming an "L" shape. Given an infinite NxN chessboard, find the minimum number of turns required for a knight to reach a destination square from the origin (0,0).

### Example:
What is the minimal amount of turns it will take for a knight to reach (0,2)?
Answer: 
The knight will move from (0,0) to (2,1), and then from (2,1) to (0,2).

### Bonus Objective:
Print the shortest path from the origin to the destination.

## Approach in 3 Steps:
1. Imagine the chessboard as a graph where each square is a node, and each valid knight move is an edge.
2. Create an adjacency list starting from the root node (0,0).
3. Use the Breadth-First Search (BFS) algorithm to find the shortest path from the origin node to the destination node.

### Step 1:
- Each square on the chessboard is represented as a node with unique coordinates.
- Identify all squares the knight can reach within one turn from the root node (0,0) and create edges between them.
- Repeat this process for all squares on the chessboard.

### Step 2:
- Store a list of reachable nodes for each node in the graph, forming an adjacency list.

### Step 3:
- Utilize BFS algorithm to find the shortest path from the origin to the destination node.

## Implementation:

### Vertex Class:
Represents a graph node with coordinates and adjacency list.

### ProblemConditions Class:
Encapsulates problem-related conditions such as board size and knight's movement rules.

### BfsAlgorithm Class:
Encapsulates the BFS algorithm for graph traversal.

### Main Class:
- Creates the chessboard graph.
- Solves the problem by finding the shortest path using BFS.
- Provides methods for printing the graph and converting paths to strings.

## Conclusion:
This solution offers a scalable and efficient approach to solving the Chess Knight Shortest Path Problem. Feel free to use and modify it, but note that it may not be the most optimized solution for all scenarios.

Let me know your thoughts and if you spot any mistakes or typos!

