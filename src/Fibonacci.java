public class Fibonacci {

        /**
         * Calcula el n-ésimo número de Fibonacci de forma recursiva
         * @param n la posición en la secuencia (empezando desde 0)
         * @return el valor de Fibonacci en la posición n
         */
        public static long fibonacci(int n) {
            // Casos base
            if (n <= 1) {
                return n;
            }

            // Llamada recursiva: F(n) = F(n-1) + F(n-2)
            return fibonacci(n - 1) + fibonacci(n - 2);
        }

    public static void main(String[] args) {
        int n = 16; // Número de términos a mostrar

        System.out.println("Secuencia de Fibonacci (primeros " + n + " términos):");

        // Imprime la secuencia
        for (int i = 0; i < n; i++) {
            System.out.print(fibonacci(i));
            if (i < n - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();

        // Ejemplo adicional: calcular un término específico
        int termino = 15;
        System.out.println("\nEl término " + termino + " de Fibonacci es: " + fibonacci(termino));
        }
    }