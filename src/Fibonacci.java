public class Fibonacci {

        /**
         * Calcula el número de Fibonacci de forma recursiva
         * @param n la posición en la secuencia (empezando desde 0)
         * @return el valor de Fibonacci en la posición n
         */
        public static long fibonacciRecursive(int n) {
            // Casos base
            if (n <= 1) {
                return n;
            }

            // Llamada recursiva: F(n) = F(n-1) + F(n-2)
            return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
        }

    public static void main(String[] args) {
        int n = 16; // Número de términos a mostrar

        System.out.println("Secuencia de Fibonacci Recursiva (primeros " + n + " términos):");

        // Imprime la secuencia
        for (int i = 0; i < n; i++) {
            System.out.print(fibonacciRecursive(i));
            if (i < n - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
        long anterior = 0;
        long actual = 1;
// Eficiente O(n) tiempo, O(1) espacio
        System.out.println("\nSecuencia de Fibonacci base (primeros 16 terminos)");
        System.out.print(anterior + ", ");
        for (int i = 2; i <= n; i++) {
            long siguiente = anterior + actual;
            System.out.print(actual + ", " );
            anterior = actual;
            actual = siguiente;
        }
        // Ejemplo adicional: calcular un término específico
        int termino = 15;
        System.out.println("\nEl término " + termino + " de Fibonacci es: " + fibonacciRecursive(termino));
        }
    }