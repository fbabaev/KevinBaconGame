import java.util.*;
/**
 * Kevin Bacon Game
 * Dartmouth CS 10, Winter 2024
 * Authors Firdavskhon Babaev & Dhanush Balaji
 */
public class GraphLib<V, E> {

    public GraphLib() { // default constructor
    }

    /**
     * performs BFS to build a graph centered around a specific actor
     *
     * @param gr graph to traverse
     * @param source starting vertex
     * @return
     */
    public static <V, E> Graph<V, E> bfs(Graph<V, E> gr, V source) {

        if (!gr.hasVertex(source)) {  // check if the source vertex exists in the graph.
            System.out.println("Source vertex not found in the graph.");
            return new AdjacencyMapGraph<>();
        }
        Graph<V, E> createdPath = new AdjacencyMapGraph<>(); // graph to store BFS result
        Set<V> visited = new HashSet<>();
        Queue<V> queue = new LinkedList<>();

        createdPath.insertVertex(source);
        queue.add(source);
        visited.add(source);

        while (!queue.isEmpty()) {
            V u = queue.remove();
            for (V v : gr.outNeighbors(u)) {
                if (!visited.contains(v)) {
                    queue.add(v);
                    visited.add(v);
                    createdPath.insertVertex(v); // add vertex to the result graph
                    createdPath.insertDirected(v, u, gr.getLabel(v, u)); // add edge to the result graph
                }
            }
        }
        return createdPath; // return the BFS tree
    }

    /**
     * Traversing to the source and compiling a list of vertices along path
     *
     * @param tree
     * @param v
     * @return list of vertices
     */
    public static <V, E> List<V> getPath(Graph<V, E> tree, V v) {
        if (tree.numVertices() == 0 || !tree.hasVertex(v)) {
            System.out.println("Vertex not found or graph is empty.");
            return new ArrayList<>();
        }
        List<V> path = new ArrayList<>();
        V curr = v;

        path.add(v);
        while (tree.outDegree(curr) > 0) {
            curr = tree.outNeighbors(curr).iterator().next();
            path.add(curr); // add current vertex to path
        }
        return path;
    }

    /**
     * Identifies vertices in the main graph that are missing from a subgraph.
     *
     * @param graph The main graph.
     * @param subgraph The subgraph to compare against.
     * @return A set of vertices present in graph but missing from subgraph.
     */
    public static <V, E> Set<V> missingVertices(Graph<V, E> graph, Graph<V, E> subgraph) {
        // handle empty graphs
        if (graph.numVertices() == 0 || subgraph.numVertices() == 0) return new HashSet<>();

        Set<V> missing = new HashSet<>();
        // find missing vertices
        for (V v : graph.vertices()) {
            if (!subgraph.hasVertex(v)) missing.add(v);
        }
        return missing; // return the set of missing vertices
    }

    /**
     * Calculates the average separation between the root and all other vertices in the graph
     *
     * @param tree
     * @param root
     * @return average separation
     */
    public static <V, E> double averageSeparation(Graph<V, E> tree, V root) {
        if (!tree.hasVertex(root) || tree.numVertices() == 0) return -1;

        double totalSteps = helperAverage(tree, root, 0, new HashSet<>()); // total steps from root
        return totalSteps / tree.numEdges();
    }

    /**
     * Helper method
     *
     * @param tree
     * @param root
     * @param steps
     * @param visited
     * @return The total number of steps
     */
    public static <V, E> double helperAverage(Graph<V, E> tree, V root, double steps, HashSet<V> visited) {
        double total = steps;
        visited.add(root);
        for (V v : tree.inNeighbors(root)){
            if(!visited.contains(v)) {                //check if their visited
                total += helperAverage(tree, v, steps + 1, visited);
                //adding number of steps
            }
        }
        return total;
    }
}
