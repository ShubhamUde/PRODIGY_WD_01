public class SudokuSolver {
    private static final int SIZE = 9;

    public boolean solveSudoku(int[][] board) {
        int row = -1;
        int col = -1;
        boolean isEmpty = true;

        // Find first empty cell
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) {
                    row = i;
                    col = j;
                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }

        // No empty cell found, puzzle solved
        if (isEmpty) {
            return true;
        }

        // Try filling the empty cell with possible options
        for (int num = 1; num <= SIZE; num++) {
            if (isValid(board, row, col, num)) {
                board[row][col] = num;

                // Recursively solve the puzzle
                if (solveSudoku(board)) {
                    return true;
                }

                // If no solution found, backtrack
                board[row][col] = 0;
            }
        }

        // No solution found for this cell
        return false;
    }

    private boolean isValid(int[][] board, int row, int col, int num) {
        // Check if num is not already in the row, col, and the 3x3 subgrid
        return !usedInRow(board, row, num) &&
               !usedInCol(board, col, num) &&
               !usedInBox(board, row - row % 3, col - col % 3, num);
    }

    private boolean usedInRow(int[][] board, int row, int num) {
        for (int col = 0; col < SIZE; col++) {
            if (board[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private boolean usedInCol(int[][] board, int col, int num) {
        for (int row = 0; row < SIZE; row++) {
            if (board[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private boolean usedInBox(int[][] board, int boxStartRow, int boxStartCol, int num) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row + boxStartRow][col + boxStartCol] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    public void printBoard(int[][] board) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] board = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        SudokuSolver solver = new SudokuSolver();
        if (solver.solveSudoku(board)) {
            System.out.println("Sudoku solved successfully:");
            solver.printBoard(board);
        } else {
            System.out.println("No solution exists.");
        }
    }
}
