import java.util.*;

class Node implements Comparable<Node> {
    int x, y; // coordinates of the node
    int g, h, f; // costs for path, heuristic, and total cost
    Node parent; // parent node

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.g = 0;
        this.h = 0;
        this.f = 0;
        this.parent = null;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.f, other.f);
    }
}

public class AStarAlgorithm {
    private static final int[][] DIRECTIONS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}}; // up, down, left, right

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get grid size from the user
        System.out.print("Enter the number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Enter the number of columns: ");
        int columns = scanner.nextInt();

        // Create the grid
        int[][] grid = new int[rows][columns];

        // Get grid elements from the user
        System.out.println("Enter the grid elements (0 for passable, 1 for impassable):");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }

        // Get start position from the user
        System.out.print("Enter the starting row: ");
        int startX = scanner.nextInt();
        System.out.print("Enter the starting column: ");
        int startY = scanner.nextInt();

        // Get goal position from the user
        System.out.print("Enter the goal row: ");
        int goalX = scanner.nextInt();
        System.out.print("Enter the goal column: ");
        int goalY = scanner.nextInt();

        List<Node> path = findPath(grid, startX, startY, goalX, goalY);
        if (path != null) {
            System.out.println("Path found:");
            for (Node node : path) {
                System.out.println("(" + node.x + ", " + node.y + ")");
            }
        } else {
            System.out.println("Path not found.");
        }

        scanner.close();
    }

    public static List<Node> findPath(int[][] grid, int startX, int startY, int goalX, int goalY) {
        int rows = grid.length;
        int columns = grid[0].length;

        // Check if the start and goal positions are valid
        if (!isValidPosition(startX, startY, rows, columns) || !isValidPosition(goalX, goalY, rows, columns)) {
            return null;
        }

        // Create the start and goal nodes
        Node startNode = new Node(startX, startY);
        Node goalNode = new Node(goalX, goalY);

        // Initialize the open and closed sets
        Set<Node> openSet = new HashSet<>();
        Set<Node> closedSet = new HashSet<>();

        // Add the start node to the open set
        openSet.add(startNode);

        // Create a map to store the costs of nodes
        Map<Node, Integer> gScore = new HashMap<>();
        gScore.put(startNode, 0);

        // Create a map to store the parent nodes
        Map<Node, Node> parentMap = new HashMap<>();

        while (!openSet.isEmpty()) {
            // Find the node with the lowest f score in the open set
            Node current = Collections.min(openSet);

            // If the goal node is reached, construct and return the path
            if (current.x == goalNode.x && current.y == goalNode.y) {
                return constructPath(parentMap, current);
            }

            // Remove the current node from the open set and add it to the closed set
            openSet.remove(current);
            closedSet.add(current);

            // Explore the neighbors of the current node
            for (int[] direction : DIRECTIONS) {
                int newX = current.x + direction[0];
                int newY = current.y + direction[1];

                // Check if the neighbor is a valid position
                if (!isValidPosition(newX, newY, rows, columns)) {
                    continue;
                }

                // Create the neighbor node
                Node neighbor = new Node(newX, newY);

                // Skip impassable nodes
                if (grid[newX][newY] == 1) {
                    continue;
                }

                // Calculate the g score for the neighbor
                int newG = gScore.get(current) + 1;

                // Check if the neighbor has already been evaluated
                if (closedSet.contains(neighbor) && newG >= gScore.get(neighbor)) {
                    continue;
                }

                // Check if the neighbor is not in the open set or has a better g score
                if (!openSet.contains(neighbor) || newG < gScore.get(neighbor)) {
                    // Update the g score, h score, and f score of the neighbor
                    gScore.put(neighbor, newG);
                    neighbor.h = calculateHeuristic(neighbor, goalNode);
                    neighbor.f = newG + neighbor.h;

                    // Set the parent of the neighbor to the current node
                    parentMap.put(neighbor, current);

                    // Add the neighbor to the open set
                    openSet.add(neighbor);
                }
            }
        }

        // No path found
        return null;
    }

    private static boolean isValidPosition(int x, int y, int rows, int columns) {
        return x >= 0 && x < rows && y >= 0 && y < columns;
    }

    private static int calculateHeuristic(Node node, Node goalNode) {
        // Manhattan distance heuristic
        return Math.abs(node.x - goalNode.x) + Math.abs(node.y - goalNode.y);
    }

    private static List<Node> constructPath(Map<Node, Node> parentMap, Node currentNode) {
        List<Node> path = new ArrayList<>();
        path.add(currentNode);

        while (parentMap.containsKey(currentNode)) {
            currentNode = parentMap.get(currentNode);
            path.add(0, currentNode);
        }

        return path;
    }
}
