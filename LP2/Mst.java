import java.util.*;

class Edge implements Comparable<Edge> {
    int src, dest, weight;

    public int compareTo(Edge edge) {
        return this.weight - edge.weight;
    }
}

class Graph {
    int vertices, edges;
    Edge[] edge;

    Graph(int v, int e) {
        vertices = v;
        edges = e;
        edge = new Edge[edges];
        for (int i = 0; i < edges; ++i) {
            edge[i] = new Edge();
        }
    }

    int find(int[] parent, int i) {
        if (parent[i] == -1) {
            return i;
        }
        return find(parent, parent[i]);
    }

    void union(int[] parent, int x, int y) {
        int xRoot = find(parent, x);
        int yRoot = find(parent, y);
        parent[xRoot] = yRoot;
    }

    void kruskalMST() {
        Edge[] result = new Edge[vertices];
        int e = 0;
        int i = 0;
        Arrays.sort(edge);

        int[] parent = new int[vertices];
        Arrays.fill(parent, -1);

        while (e < vertices - 1 && i < edges) {
            Edge nextEdge = edge[i++];

            int x = find(parent, nextEdge.src);
            int y = find(parent, nextEdge.dest);

            if (x != y) {
                result[e++] = nextEdge;
                union(parent, x, y);
            }
        }

        System.out.println("Minimum Spanning Tree:");
        for (i = 0; i < e; ++i) {
            System.out.println(result[i].src + " -- " + result[i].dest + " : " + result[i].weight);
        }
    }
}

public class Mst {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int vertices = scanner.nextInt();

        System.out.print("Enter the number of edges: ");
        int edges = scanner.nextInt();

        Graph graph = new Graph(vertices, edges);

        System.out.println("Enter the details of each edge:");
        for (int i = 0; i < edges; i++) {
            System.out.println("Edge " + (i + 1) + ":");
            System.out.print("Source vertex: ");
            graph.edge[i].src = scanner.nextInt();
            System.out.print("Destination vertex: ");
            graph.edge[i].dest = scanner.nextInt();
            System.out.print("Weight: ");
            graph.edge[i].weight = scanner.nextInt();
        }

        graph.kruskalMST();
    }
}
