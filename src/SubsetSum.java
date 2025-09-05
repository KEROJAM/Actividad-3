import java.util.*;

public class SubsetSum {

    public static void main(String[] args) {
        // Ejemplo 1: conjunto básico
        int[] conjunto1 = {4, 3, 4, 12, 6, 5, 1};
        int suma1 = 9;

        System.out.println("Conjunto: " + Arrays.toString(conjunto1));
        System.out.println("Suma objetivo: " + suma1);

        List<Integer> solucion1 = new ArrayList<>();
        if (existeSumaSubconjunto(conjunto1, conjunto1.length, suma1, solucion1)) {
            System.out.println("Sí existe un subconjunto con suma " + suma1);
            System.out.println("Subconjunto encontrado: " + solucion1);
        } else {
            System.out.println("No existe un subconjunto con suma " + suma1);
        }

        System.out.println("\n" + "-*".repeat(25));

        // Ejemplo 2: encontrar todos los subconjuntos posibles
        int[] conjunto2 = {1, 2, 3, 4, 5};
        int suma2 = 5;

        System.out.println("\nConjunto: " + Arrays.toString(conjunto2));
        System.out.println("Suma objetivo: " + suma2);
        System.out.println("Todos los subconjuntos que suman " + suma2 + ":");

        List<List<Integer>> todasLasSoluciones = new ArrayList<>();
        encontrarTodosLosSubconjuntos(conjunto2, conjunto2.length, suma2,
                new ArrayList<>(), todasLasSoluciones);

        if (todasLasSoluciones.isEmpty()) {
            System.out.println("No se encontraron subconjuntos");
        } else {
            for (int i = 0; i < todasLasSoluciones.size(); i++) {
                System.out.println("Solución " + (i + 1) + ": " + todasLasSoluciones.get(i));
            }
        }

        // Ejemplo 3: caso más complejo
        System.out.println("\n" + "-*".repeat(25));
        int[] conjunto3 = {10, 6, 2, 13, 11, 20, 10};
        int suma3 = 35;

        System.out.println("\nConjunto: " + Arrays.toString(conjunto3));
        System.out.println("Suma objetivo: " + suma3);

        List<Integer> solucion3 = new ArrayList<>();
        if (existeSumaSubconjunto(conjunto3, conjunto3.length, suma3, solucion3)) {
            System.out.println("Sí existe un subconjunto con suma " + suma3);
            System.out.println("Subconjunto encontrado: " + solucion3);
        } else {
            System.out.println("No existe un subconjunto con suma " + suma3);
        }
    }

    /**
     * Verifica si existe un subconjunto que sume exactamente el valor objetivo
     * usando algoritmo recursivo con backtracking
     *
     * @param conjunto array de números enteros
     * @param n tamaño del conjunto
     * @param suma suma objetivo
     * @param solucion lista para almacenar el subconjunto encontrado
     * @return true si existe el subconjunto, false en caso contrario
     */
    public static boolean existeSumaSubconjunto(int[] conjunto, int n, int suma, List<Integer> solucion) {
        // Caso base: si la suma es 0, hemos encontrado una solución
        if (suma == 0) {
            return true;
        }

        // Caso base: si no hay más elementos o la suma es negativa
        if (n == 0 || suma < 0) {
            return false;
        }

        // Caso 1: Incluir el elemento actual en el subconjunto
        solucion.add(conjunto[n - 1]);
        if (existeSumaSubconjunto(conjunto, n - 1, suma - conjunto[n - 1], solucion)) {
            return true;
        }

        // Backtrack: quitar el elemento si no lleva a una solución
        solucion.remove(solucion.size() - 1);

        // Caso 2: No incluir el elemento actual en el subconjunto
        return existeSumaSubconjunto(conjunto, n - 1, suma, solucion);
    }

    /**
     * Encuentra todos los subconjuntos posibles que suman el valor objetivo
     *
     * @param conjunto array de números enteros
     * @param n tamaño del conjunto
     * @param suma suma objetivo
     * @param subconjuntoActual subconjunto que se está construyendo
     * @param todasLasSoluciones lista de todas las soluciones encontradas
     */
    public static void encontrarTodosLosSubconjuntos(int[] conjunto, int n, int suma, List<Integer> subconjuntoActual, List<List<Integer>> todasLasSoluciones) {
        // Caso base: si la suma es 0, hemos encontrado una solución
        if (suma == 0) {
            todasLasSoluciones.add(new ArrayList<>(subconjuntoActual));
            return;
        }

        // Caso base: si no hay más elementos o la suma es negativa
        if (n == 0 || suma < 0) {
            return;
        }

        // Caso 1: Incluir el elemento actual
        subconjuntoActual.add(conjunto[n - 1]);
        encontrarTodosLosSubconjuntos(conjunto, n - 1, suma - conjunto[n - 1],subconjuntoActual, todasLasSoluciones);

        // Backtrack: quitar el elemento
        subconjuntoActual.remove(subconjuntoActual.size() - 1);

        // Caso 2: No incluir el elemento actual
        encontrarTodosLosSubconjuntos(conjunto, n - 1, suma, subconjuntoActual, todasLasSoluciones);
    }

}
