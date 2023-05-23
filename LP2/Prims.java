import java.util.*;
public class Prims {
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
        int from = scanner.nextInt();
        int to = scanner.nextInt();
        int weight = scanner.nextInt();

        graph[from].add(new Edge(from, to, weight));
    }
}
   static class Pair implements Comparable<Pair> {
       int v;
       int wt;
    public Pair(int v, int wt) {
        this.v = v;

        this.wt = wt;
}
@Override
public int compareTo(Pair p2) {
return this.wt - p2.wt;
}
}
//O(ElogE)
    public static void primAlgo(ArrayList<Edge> graph[]) {
    boolean vis[] = new boolean[graph.length];
PriorityQueue<Pair> pq = new PriorityQueue<>();
pq.add(new Pair(0, 0));
int cost = 0;
 while(!pq.isEmpty()) {
 Pair curr = pq.remove();
       if(!vis[curr.v]) {
 vis[curr.v] = true;
 cost += curr.wt;
 for(int i=0; i<graph[curr.v].size(); i++) {
 Edge e = graph[curr.v].get(i);
 if(!vis[e.dest]) {
     pq.add(new Pair(e.dest, e.wt));
    }
}
}
}
System.out.println(cost);
}
public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter the number of vertices: ");
    int V = scanner.nextInt();
    ArrayList<Edge>[] graph = new ArrayList[V];

    createGraph(graph, V, scanner);

    int choice = scanner.nextInt();

   
    primAlgo(graph);

    scanner.close();
}
}