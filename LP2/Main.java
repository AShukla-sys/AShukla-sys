//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.Queue;
//import java.util.Scanner;
import java.util.*;
public class Main {
    static class Edge {
        int src;
        int dest;
        int wt;

        public Edge(int s, int d, int w) {
            this.src = s;
            this.dest = d;
            this.wt = w;
        } 
    }

    static void createGraph(ArrayList<Edge>[] graph, int numVertices, Scanner scanner) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        System.out.println("Enter the number of edges: ");
        int numEdges = scanner.nextInt();

        for (int i = 0; i < numEdges; i++) {
            System.out.println("Enter edge " + (i + 1) + " (from to weight): ");
            int s = scanner.nextInt();
            int d = scanner.nextInt();
            int w = scanner.nextInt();

            graph[s].add(new Edge(s, d, w));
        }
    }
    public static void bfs(ArrayList<Edge>[] graph, int V) {
        boolean visited[] = new boolean[V];
        Queue<Integer> q = new LinkedList<>();
        q.add(0); // Source = 0
        while (!q.isEmpty()) {
            int curr = q.remove();
            if (!visited[curr]) {
                System.out.print(curr + " ");
                visited[curr] = true;
                for (int i = 0; i < graph[curr].size(); i++) {
                    Edge e = graph[curr].get(i);
                    q.add(e.dest);
                }
            }
        }
        System.out.println();
    }

    public static void dfs(ArrayList<Edge>[] graph, int curr, boolean visited[]) {
        if (visited[curr]) {
            return;
        }
        System.out.print(curr + " ");
        visited[curr] = true;
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            dfs(graph, e.dest, visited);
        }
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();
        ArrayList<Edge>[] graph = new ArrayList[V];

        createGraph(graph, V, scanner);

        System.out.println("Select traversal: ");
        System.out.println("1. BFS");
        System.out.println("2. DFS");
        int choice = scanner.nextInt();

        if (choice == 1) {
            bfs(graph, V);
        } else if (choice == 2) {
            dfs(graph, 0, new boolean[V]);
        } else {
            System.out.println("Invalid choice!");
        }

        scanner.close();
    }
}