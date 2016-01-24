package com.company;

import java.util.*;

public class Main {

    public static final int SIZE_OF_CHESS_BOARD = 8;
    public static final Vertex DESTINATION_VERTEX = new Vertex(0, 2);
    private static final BfsAlgorithm bfs = new BfsAlgorithm();
    private static final ProblemConditions problemConditions = new ProblemConditions(SIZE_OF_CHESS_BOARD, 1, 2);

    /**
     * Creates chess board as a graph represented as adjacency list
     *
     * @return root node of the graph
     */
    private static Vertex createChessboardGraph() {
        //first we are creating all possible nodes in the graph
        //without associating any adjacent nodes to them
        final ArrayList<Vertex> vertexes = new ArrayList<>();
        for (int i = 0; i <= problemConditions.getChessBoardSize(); i++) {
            for (int j = 0; j <= problemConditions.getChessBoardSize(); j++) {
                vertexes.add(new Vertex(i, j));
            }
        }

        //and now we are actually associating adjacent nodes
        //to each created node
        for (Vertex v : vertexes) {
            if (v.getAdjVertices().isEmpty())
                createAdjListForVertex(v, vertexes);
        }

        //we return the root node as it will represent the entire graph
        return vertexes.get(0);
    }

    /**
     * Creates adjacency list for provided node , according to problem definition
     *
     * @param vertexes are used to define connections between one another
     */
    private static void createAdjListForVertex(Vertex v, ArrayList<Vertex> vertexes) {
        //Here we are just trying to create adjacent nodes in all possible directions

        int xRightFull = v.getX() + problemConditions.getFareMostMove();
        int xRightHalf = v.getX() + problemConditions.getNearestMove();
        int xLeftFull = v.getX() + -problemConditions.getFareMostMove();
        int xLeftHalf = v.getX() + -problemConditions.getNearestMove();

        int yUpFull = v.getY() + problemConditions.getFareMostMove();
        int yUpHalf = v.getY() + problemConditions.getNearestMove();
        int yDownFull = v.getY() + -problemConditions.getFareMostMove();
        int yDownHalf = v.getY() + -problemConditions.getNearestMove();

        //full right half up
        createAdjVertexIfInBounds(v, xRightFull, yUpHalf, vertexes);
        //half right Full up
        createAdjVertexIfInBounds(v, xRightHalf, yUpFull, vertexes);
        //half left Full up
        createAdjVertexIfInBounds(v, xLeftHalf, yUpFull, vertexes);
        //full left half up
        createAdjVertexIfInBounds(v, xLeftFull, yUpHalf, vertexes);
        //full left half down
        createAdjVertexIfInBounds(v, xLeftFull, yDownHalf, vertexes);
        //half left full down
        createAdjVertexIfInBounds(v, xLeftHalf, yDownFull, vertexes);
        //half right full down
        createAdjVertexIfInBounds(v, xRightHalf, yDownFull, vertexes);
        //full right half down
        createAdjVertexIfInBounds(v, xRightFull, yDownHalf, vertexes);
    }

    /**
     * adds adjacent nodes according to constrains in problem definition
     *
     * @param vertexes will be used to define connections between one another
     */
    private static void createAdjVertexIfInBounds(Vertex v, int x, int y, ArrayList<Vertex> vertexes) {
        //check problemConditions
        if (problemConditions.isPositionOnChessBoard(x, y)) {

            //we don't want to assign new instances of Vertexes
            //as adjacent nodes , that will not work.
            //we need to reuse the same instances and assign
            //connections between them
            final int indexOf = vertexes.indexOf(new Vertex(x, y));
            final Vertex reusedVertex = vertexes.get(indexOf);
            v.addAdjVertex(reusedVertex);
        }
    }

    /**
     * Finds a shortest path from source to destination
     *
     * @param from source node
     * @param to   destination node
     * @return string complete representation of solved problem
     */
    private static String solveProblem(final Vertex from, final Vertex to) {
        final List<Vertex> path = new ArrayList<>();
        final HashMap<Vertex, Vertex> parentToChild = new HashMap<>();
        bfs.run(from, new BfsAlgorithm.BfsAction() {

            private Vertex currParent;

            @Override
            public boolean onVisitParent(Vertex parent) {
                currParent = parent;
                return false;
            }

            @Override
            public boolean onVisitChild(Vertex vertex) {
                parentToChild.put(vertex, currParent);
                if (vertex.equals(to)) return true;
                else return false;
            }
        });
        path.add(to);
        Vertex par = parentToChild.get(to);
        while (par != null) {
            path.add(par);
            par = parentToChild.get(par);
        }

        Collections.reverse(path);
        return "Turns Required to reach destination: " + (path.size() - 1) + "\nPath : "
                + pathToString(path);
    }

    /**
     * Converts a list of nodes that represents a path
     * to simple string representation
     *
     * @param path list of nodes
     */
    private static String pathToString(List<Vertex> path) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            Vertex v = path.get(i);
            if (i != 0)
                sb.append("->");

            sb.append(v.getName());
        }
        return sb.toString();
    }

    /**
     * Prints the graph as adjacency list
     *
     * @param graph root node
     */
    private static void printGraph(Vertex graph) {
        List<Vertex> list = graphToList(graph);
        Collections.sort(list, new Comparator<Vertex>() {
            public int compare(Vertex o1, Vertex o2) {
                if (o1.getX() == o2.getX()) return o1.getY() - o2.getY();
                else return o1.getX() - o2.getX();
            }
        });

        StringBuilder sb = new StringBuilder();
        for (Vertex vert : list) {
            sb.append(vert + "->");
            for (Vertex adjV : vert.getAdjVertices()) {
                sb.append(" " + adjV);
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    /**
     * Uses bfs internally to convert a graph to list of
     * distinct nodes
     *
     * @param vert root node
     * @return list of all child nodes
     */
    private static List<Vertex> graphToList(Vertex vert) {
        final List<Vertex> list = new ArrayList<>();
        bfs.run(vert, new BfsAlgorithm.BfsAction() {
            @Override
            public boolean onVisitParent(Vertex v) {
                list.add(v);
                return false;
            }

            @Override
            public boolean onVisitChild(Vertex vertex) {
                return false;
            }
        });
        return list;
    }


    public static void main(String[] args) {
        //we are representing available moves on NxN chessboard
        //by vertex adjacency list
        Vertex root = createChessboardGraph();
        //printing the graph for visualisation
        System.out.println("Graph adjacency list :\n");
        printGraph(root);
        //printing the solution
        System.out.println(solveProblem(root, DESTINATION_VERTEX));
    }
}
