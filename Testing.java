import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Problem Set 4 (PS4)
 * Kevin Bacon Game
 * Dartmouth CS 10, Winter 2024
 * Authors: Firdavshkaon Babaev & Dhanush Balaji
 * This class demonstrates the use of graph data structures to model actor-movie relationships.
 */
public class Testing {
    public static void main(String[] args) throws IOException {

        Graph<String, Set<String>> allRelations = new AdjacencyMapGraph<>();
        // initializing graph to store actor relationships


        Map<String, Set<String>> movieId_actorName = ActorMoviesInfo.movieActorIds("PS4/movie-actors.txt", "PS4/actors.txt");
        Map<String, String> actors = ActorMoviesInfo.readActor("PS4/actors.txt"); // Maps actor Ids to names
        Map<String, String> movies = ActorMoviesInfo.readMoviesInfo("PS4/movies.txt"); // maps movie ids to names

        for (String id : actors.keySet()) {
            allRelations.insertVertex(actors.get(id));
        }

        // constructs edges between actors who have co-starred in the same movie
        for (Map.Entry<String, Set<String>> movieEntry : movieId_actorName.entrySet()) {
            for (String actorName : movieEntry.getValue()) {
                for (String coStarName : movieEntry.getValue()) {
                    if (!actorName.equals(coStarName)) {
                        // if an edge exists, add the movie to their set, else create a new edge with the movie
                        if (allRelations.hasEdge(actorName, coStarName)) {
                            allRelations.getLabel(actorName, coStarName).add(movies.get(movieEntry.getKey()));
                        } else {
                            allRelations.insertUndirected(actorName, coStarName, new HashSet<>());
                            allRelations.getLabel(actorName, coStarName).add(movies.get(movieEntry.getKey()));
                        }
                    }
                }
            }
        }

        Graph<String, Set<String>> bestPath = GraphLib.bfs(allRelations, "Kevin Bacon");
        // calculates the average separation from "Kevin Bacon" to all actors in the graph
        double averageSeparation = GraphLib.averageSeparation(bestPath, "Kevin Bacon");


        Interface.query(allRelations);
    }
}
