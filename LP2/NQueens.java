import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NQueens {

    public static boolean isSafe(List<Integer> board, int row, int col) {
        // Check if there is a queen in the same column or in the diagonal positions
        for (int i = 0; i < row; i++) {
            if (board.get(i) == col || board.get(i) == col - (row - i) || board.get(i) == col + (row - i)) {
                return false;
            }
        }
        return true;
    }

    public static void solveNQueens(List<Integer> board, int row, int n, int[] count) {
        if (row == n) {
            // All queens are successfully placed, print the solution
            count[0]++;
            System.out.println("Solution " + count[0] + ":");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (board.get(i) == j) {
                        System.out.print("Q ");
                    } else {
                        System.out.print(". ");
                    }
                }
                System.out.println();
            }
            System.out.println();
            return;
        }

        // Try placing the queen in each column of the current row
        for (int col = 0; col < n; col++) {
            // Check if the position is safe
            if (isSafe(board, row, col)) {
                // Place the queen in the current column
                board.set(row, col);
                // Recursively solve the remaining n - 1 queens
                solveNQueens(board, row + 1, n, count);
                // Backtracking
                board.set(row, -1);
            }
        }
    }

    public static void solveNQueens(int n) {
        List<Integer> board = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            board.add(-1);
        }

        int[] count = {0};
        solveNQueens(board, 0, n, count);

        if (count[0] == 0) {
            System.out.println("No solutions found.");
        } else {
            System.out.println("Total solutions: " + count[0]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of queens (N): ");
        int n = scanner.nextInt();

        solveNQueens(n);
    }
}
