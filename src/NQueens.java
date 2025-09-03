
public class NQueens {
   static int N = 8;
   static int[] board = new int [8];
   static int tablero = 1;
    /**
     * Imprime Tablero
     */

    static void printBoard(){
        System.out.println("Tablero: " + tablero);
        tablero++;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i] == j){
                    System.out.print("[Q] ");
                } else {
                    System.out.print("[ ] ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Resuelve la determinacion para la reina en la fila
     * @param row
     */
   public static void Solve(int row){
       if (row == N){
          printBoard();
       }
       for (int col = 0; col < N; col++){
            if (isSafe(row,col)){
                board[row] = col;
                Solve(row + 1);
            }
       }
   }

    /**
     * verifica si no colisiona con otra reina
     * @param row row of board
     * @param col column of board
     * @return true si no colisiona
     */
   static boolean isSafe(int row, int col){
        for (int i = 0; i< row; i++){
            if(board[i] == col || Math.abs(board[i] - col) == row - i){
                return false;
            }
        }
       return true;
   }

   public static void main(String[] args){
       Solve(0);
   }
}