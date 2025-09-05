import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class Sudoku {

    static int empty = 0;
    static int stepCounter = 0;
    static Random random = new Random();

    /**
     * Resuelve el sudoku recorriendo el tablero y verifica si es un movimiento válido
     * Muestra cada paso del proceso de resolución
     * @param board el tablero del juego
     * @return true si se resuelve, false si no hay solución
     */
    public static boolean Solve(int[][] board){
       for (int row = 0; row < 9; row++)
        for (int col = 0; col < 9; col++){
            if (board[row][col] == empty){
                for (int num = 1; num <= 9; num++){
                    if (validMove(board, row, col, num)){
                        board[row][col] = num;
                        stepCounter++;
                        
                        // Mostrar el paso actual
                        System.out.println("\n=== PASO " + stepCounter + " ===");
                        System.out.println("Colocando " + num + " en posicion [" + (row+1) + "," + (col+1) + "]");
                        printBoard(board);
                        
                        // Pausa para poder ver cada paso
                        try {
                            Thread.sleep(500); // Pausa de 500ms
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        
                        if (Solve(board)){
                            return true;
                        }
                        
                        // Si llegamos aquí, necesitamos retroceder
                        System.out.println("\n--- RETROCESO ---");
                        System.out.println("Quitando " + num + " de posicion [" + (row+1) + "," + (col+1) + "]");
                        board[row][col] = empty;
                        printBoard(board);
                        
                        // Pausa para mostrar el retroceso
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
                return false;
            }
        }
       return true;
    }

    /**
     * Genera un tablero completo de Sudoku valido
     */
    public static int[][] generateFullBoard() {
        int[][] board = new int[9][9];
        fillBoard(board);
        return board;
    }

    /**
     * Llena el tablero de manera recursiva con numeros validos aleatorios
     */
    private static boolean fillBoard(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == empty) {
                    ArrayList<Integer> numbers = new ArrayList<>();
                    for (int i = 1; i <= 9; i++) {
                        numbers.add(i);
                    }
                    Collections.shuffle(numbers);

                    for (int num : numbers) {
                        if (validMove(board, row, col, num)) {
                            board[row][col] = num;
                            if (fillBoard(board)) {
                                return true;
                            }
                            board[row][col] = empty;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Genera un puzzle removiendo numeros del tablero completo
     */
    public static int[][] generatePuzzle(int difficulty) {
        int[][] fullBoard = generateFullBoard();
        int[][] puzzle = new int[9][9];
        
        // Copiar el tablero completo
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                puzzle[i][j] = fullBoard[i][j];
            }
        }
        
        // Determinar cuantos numeros remover segun dificultad
        int cellsToRemove;
        switch (difficulty) {
            case 1: cellsToRemove = 35; break; // Facil
            case 2: cellsToRemove = 45; break; // Medio
            case 3: cellsToRemove = 55; break; // Dificil
            default: cellsToRemove = 40; break;
        }
        
        // Remover numeros aleatoriamente
        ArrayList<int[]> positions = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                positions.add(new int[]{i, j});
            }
        }
        Collections.shuffle(positions);
        
        for (int i = 0; i < cellsToRemove && i < positions.size(); i++) {
            int[] pos = positions.get(i);
            puzzle[pos[0]][pos[1]] = empty;
        }
        
        return puzzle;
    }

    /**
     * verifica si es un movimiento valido usando las demas funciones
     */
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
        System.out.println("┌───────┬───────┬───────┐");
        for (int row = 0; row < 9; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("├───────┼───────┼───────┤");
            }

            System.out.print("│ ");
            for (int col = 0; col < 9; col++) {
                if (col % 3 == 0 && col != 0) {
                    System.out.print("│ ");
                }

                if (board[row][col] == empty) {
                    System.out.print("· ");
                } else {
                    System.out.print(board[row][col] + " ");
                }
            }
            System.out.println("│");
        }
        System.out.println("└───────┴───────┴───────┘");
    }

    public static boolean isValidSolution(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int temp = board[i][j];
                board[i][j] = empty;

                if (!validMove(board, i, j, temp)) {
                    board[i][j] = temp;
                    return false;
                }

                board[i][j] = temp;
            }
        }
        return true;
    }

    public static void main(String[] args){
        System.out.println("SUDOKU SOLVER - VISUALIZACION PASO A PASO");
        
        int[][] board = generatePuzzle(2); // Dificultad media
        
        System.out.println("\nTablero Original:");
        printBoard(board);
        
        System.out.println("\nComenzando resolucion...");
        
        stepCounter = 0;
        long startTime = System.currentTimeMillis();

        if (Solve(board)) {
            long endTime = System.currentTimeMillis();
            System.out.println("\nSUDOKU RESUELTO!");
            System.out.println("Estadisticas:");
            System.out.println("   Pasos totales: " + stepCounter);
            System.out.println("   Tiempo: " + (endTime - startTime) + "ms");
            System.out.println("\nSolucion Final:");
            printBoard(board);
        } else {
            System.out.println("No se encontro solucion para este tablero.");
        }
    }
}