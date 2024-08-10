import java.util.*;
import java.io.*;

/**
 * PS4
 * KevinBacon game
 * Dartmouth CS 10, Winter 2024.
 * Authors: Firdavskhon Babaev & Dhanush Balaji.
 */
public class ActorMoviesInfo<K, V> {

    public ActorMoviesInfo() {
        // Constructor
    }

    /**
     * reads actor details from a file and stores them in a map
     * Keys are actor IDs (String) and values are actor names (String)
     *
     * @param pathtoFile
     * @throws IOException
     */
    public static Map<String, String> readActor(String pathtoFile) throws IOException {
        Map<String, String> actorInfo = new HashMap<>(); // holds actor information
        String line;
        BufferedReader in = new BufferedReader(new FileReader(pathtoFile));
        try {
            while ((line = in.readLine()) != null) { // Read until end of file
                String[] info = line.split("\\|"); // split line
                actorInfo.put(info[0], info[1]);
            }
        } finally {
            in.close();
        }
        return actorInfo;
    }

    /**
     * Reads movie-actor relationships from a file and maps movie IDs to actor names.
     *
     * @param pathtoFile The path of the file containing movie-actor relationships.
     * @param pathname The path to the file containing actor information.
     * @throws IOException If either file is not found.
     */
    public static Map<String, Set<String>> movieActorIds(String pathtoFile, String pathname) throws IOException {
        Map<String, Set<String>> movieActor = new HashMap<>(); // maps movies to actors
        Map<String, String> actorInfo = readActor(pathname);
        String line; // For reading file lines
        BufferedReader in = new BufferedReader(new FileReader(pathtoFile));
        try {
            while ((line = in.readLine()) != null) {
                String[] idInfo = line.split("\\|"); // split line into ID parts
                movieActor.computeIfAbsent(idInfo[0], k -> new HashSet<>()).add(actorInfo.get(idInfo[1])); // map movie to actor names
            }
        } finally {
            in.close();
        }
        return movieActor;
    }

    /**
     * reads movie information from a file and maps movie IDs to movie names
     *
     * @param pathtoFile
     * @throws IOException
     */
    public static Map<String, String> readMoviesInfo(String pathtoFile) throws IOException {
        Map<String, String> movieInfo = new HashMap<>(); // to hold movie info
        String line;
        BufferedReader in = new BufferedReader(new FileReader(pathtoFile));
        try {
            while ((line = in.readLine()) != null) {
                String[] movies = line.split("\\|");
                movieInfo.put(movies[0], movies[1]);
            }
        } finally {
            in.close();
        }
        return movieInfo;
    }

}
