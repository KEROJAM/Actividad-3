public class Sudoku {

    static int empty = 0;

    /**
     * Resuelve el comando recorriendo el tablero del sudoku y verifica si es un movimiento valido
      * @param board el tablero del juego
     * @return
     */
    public static boolean Solve(int[][] board){
       for (int row = 0; row < 9; row++)
        for (int col = 0; col < 9; col++){
            if (board[row][col] == empty){
                for (int num = 1; num <= 9; num++){
                    if (validMove(board, row,col,num)){
                        board[row][col] = num;
                        if (Solve(board)){
                            return true;
                        }

                        board[row][col] = empty;
                    }
                }
                return false;
            }
        }
       return true;
    }

    static boolean validMove(int[][] board, int row, int col, int num){
        return !isInRow(board, row, num) && !isInColumn(board, col, num) && !isInBox(board, row, col, num);
    }

    static boolean isInRow(int[][] board, int row, int num){
        for (int col = 0; col < 9; col++){
            if (board[row][col] == num){
                return true;
            }
        }
        return false;
    }

    static boolean isInColumn(int[][] board, int col, int num){
        for (int row = 0; row < 9; row++){
            if (board[row][col] == num){
                return true;
            }
        }
        return false;
    }

    static boolean isInBox(int[][] board, int row, int col, int num){
        int boxRow = row - row % 3;
        int boxCol = col - col % 3;

        for (int i = boxRow; i < boxRow + 3; i++){
            for (int j = boxCol; j < boxCol + 3; j++){
                if (board[i][j] == num){
                    return true;
                }
            }
        }
        return false;
    }

    public static void printBoard(int[][] board){
        for (int row = 0; row < 9; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("------+-------+------");
            }

            for (int col = 0; col < 9; col++) {
                if (col % 3 == 0 && col != 0) {
                    System.out.print("| ");
                }

                if (board[row][col] == empty) {
                    System.out.print(". ");
                } else {
                    System.out.print(board[row][col] + " ");
                }
            }
            System.out.println();
        }
    }
    public static boolean isValidSolution(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int temp = board[i][j];
                board[i][j] = empty; // Temporarily remove number

                if (!validMove(board, i, j, temp)) {
                    board[i][j] = temp; // Restore number
                    return false;
                }

                board[i][j] = temp; // Restore number
            }
        }
        return true;
    }

    public static void main(String[] args){
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

        System.out.println("Original Puzzle:");
        printBoard(board);

        if (Solve(board)) {
            System.out.println("\nSolved Puzzle:");
            printBoard(board);
        } else {
            System.out.println("No solution exists for this board.");
        }
    }
}
