import java.util.*;

public class Dijkstra {
    private static final int INFINITY = Integer.MAX_VALUE; // Represents infinity value

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of vertices
        System.out.print("Enter the number of vertices: ");
        int vertices = scanner.nextInt();

        int[][] graph = new int[vertices][vertices]; // Adjacency matrix representation

        // Read the adjacency matrix
        System.out.println("Enter the adjacency matrix:");
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }

        // Read the source vertex
        System.out.print("Enter the source vertex: ");
        int source = scanner.nextInt();

        scanner.close();

        dijkstraMST(graph, source);
    }

    public static void dijkstraMST(int[][] graph, int source) {
        int vertices = graph.length;
        int[] distance = new int[vertices]; // Stores the shortest distance from the source vertex
        boolean[] visited = new boolean[vertices]; // Keeps track of visited vertices

        // Initialize the distance array with infinity and visited array with false
        Arrays.fill(distance, INFINITY);
        Arrays.fill(visited, false);

        distance[source] = 0; // Set the distance of the source vertex to 0

        for (int i = 0; i < vertices - 1; i++) {
            int minDistanceVertex = findMinDistanceVertex(distance, visited);

            visited[minDistanceVertex] = true;

            // Update the distance of adjacent vertices
            for (int j = 0; j < vertices; j++) {
                if (!visited[j] && graph[minDistanceVertex][j] != 0 &&
                        distance[minDistanceVertex] != INFINITY &&
                        distance[minDistanceVertex] + graph[minDistanceVertex][j] < distance[j]) {
                    distance[j] = distance[minDistanceVertex] + graph[minDistanceVertex][j];
                }
            }
        }

        // Print the minimum spanning tree
        printMST(distance, vertices, source);
    }

    public static int findMinDistanceVertex(int[] distance, boolean[] visited) {
        int minDistance = INFINITY;
        int minDistanceVertex = -1;

        for (int i = 0; i < distance.length; i++) {
            if (!visited[i] && distance[i] < minDistance) {
                minDistance = distance[i];
                minDistanceVertex = i;
            }
        }

        return minDistanceVertex;
    }

    public static void printMST(int[] distance, int vertices, int source) {
        System.out.println("Minimum Spanning Tree:");
        System.out.println("Vertex\tDistance from Source");

        for (int i = 0; i < vertices; i++) {
            System.out.println(i + "\t" + distance[i]);
        }
    }
}
