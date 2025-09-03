public class Sudoku {
    static int[][] board = {
            {0},{4},{0}, {0},{0},{0}, {0},{0},{7},
            {0},{8},{0}, {0},{1},{0}, {0},{0},{0},
            {0},{0},{0}, {0},{0},{0}, {0},{6},{4},

            {2},{1},{0}, {0},{5},{0}, {6},{3},{9},
            {0},{0},{0}, {7},{0},{0}, {0},{8},{0},
            {8},{0},{4}, {1},{0},{0}, {0},{0},{0},

            {0},{0},{0}, {0},{2},{6}, {0},{0},{4},
            {0},{2},{0}, {0},{0},{0}, {0},{0},{0},
            {7},{0},{0}, {3},{0},{1}, {5},{9},{0},
    };
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
                for (int num = 0; num <= 9; num++){
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

        for (int i = boxRow; i < boxRow; i++){
            for (int j = boxCol; j < boxCol; j++){
                if (board[i][j] == num){
                    return true;
                }
            }
        }
        return false;
    }

    public static void printBoard(){
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

    public static void main(String[] args){

    }
}
