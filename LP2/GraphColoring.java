import java.util.*;

public class GraphColoring {
    private int V;
    private List<List<Integer>> adj;

    public GraphColoring(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();
        GraphColoring graph = new GraphColoring(V);

        // Input the names of the vertices
        Map<Integer, String> vertexNames = new HashMap<>();
        for (int i = 0; i < V; i++) {
            System.out.print("Enter the name of vertex " + (i+1) + ": ");
            vertexNames.put(i, scanner.next());
        }

        // Input the neighbors of each vertex
        for (int i = 0; i < V; i++) {
            System.out.print("How many neighbors does " + vertexNames.get(i) + " have? ");
            int numNeighbors = scanner.nextInt();
            for (int j = 0; j < numNeighbors; j++) {
                System.out.print("Enter the name of neighbor " + (j+1) + " of " + vertexNames.get(i) + ": ");
                String neighborName = scanner.next();
                int neighborIndex = getKey(vertexNames, neighborName);
                graph.addEdge(i, neighborIndex);
            }
        }

        // Perform graph coloring
        String[] colorNames = {"red", "green", "blue", "yellow", "orange", "purple", "pink"};
        int[] color = new int[V];
        Arrays.fill(color, -1);
        boolean[] usedColors = new boolean[V];

        for (int i = 0; i < V; i++) {
            Arrays.fill(usedColors, false);
            for (int neighbor : graph.adj.get(i)) {
                if (color[neighbor] != -1) {
                    usedColors[color[neighbor]] = true;
                }
            }
            int c;
            for (c = 0; c < V; c++) {
                if (!usedColors[c]) {
                    break;
                }
            }
            color[i] = c;
        }

        // Output the colors of the vertices
        for (int i = 0; i < V; i++) {
            System.out.println(vertexNames.get(i) + " is colored " + colorNames[color[i]]);
        }
    }

    // Helper method to get the key for a given value in a map
    private static int getKey(Map<Integer, String> map, String value) {
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return -1;
    }
}
