import java.util.Random;
import java.util.Scanner;

public class Metodos {
    public static void main(String[] args) {
        Metodos metodos = new Metodos();
        metodos.menu();
    }

    int[] arr10, arr100, arr1000, arr5000, arr10000, arr30000;

    public void menu() {
        boolean estado = true;

        while (estado) {
            System.out.println("Seleccione la opcion");
            System.out.println("1. Generacion Arreglos aleatorios con diferente tamaño");
            System.out.println("2. Ordenamiento por tres métodos: Inserción, Selección, Burbuja");
            System.out.println("3. Búsqueda en posiciones específicas");
            System.out.println("4. Salir");

            Scanner teclado = new Scanner(System.in);
            int numero = teclado.nextInt();

            switch (numero) {
                case 1 -> generarArreglos();
                case 2 -> ordenarArreglos();
                case 3 -> Busquedas();
                case 4 -> {
                    System.out.println("Saliendo del programa...");
                    estado = false;
                }
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    public void generarArreglos() {
        int[] arr = new int[30000];
        Random rd = new Random();
        for (int i = 0; i < arr.length; i++) {
            //Generacion de valores de 0 a 29999
            arr[i] = rd.nextInt(30000); 

        }
    
    
        arr10 = java.util.Arrays.copyOf(arr, 10);
        arr100 = java.util.Arrays.copyOf(arr, 100);
        arr1000 = java.util.Arrays.copyOf(arr, 1000);
        arr5000 = java.util.Arrays.copyOf(arr, 5000);
        arr10000 = java.util.Arrays.copyOf(arr, 10000);
        arr30000 = java.util.Arrays.copyOf(arr, 30000);
    
        System.out.println("Arreglos generados con éxito:");
    
        //Imprime los primeros elementos de los arreglos
        System.out.print("Arreglo Generado de 10 Valores: ");
        printArray(arr10, 10);
        System.out.print("Arreglo Generado de 100 Valores: ");
        printArray(arr100, 100);
        System.out.print("Arreglo Generado de 1000 Valores: ");
        printArray(arr1000, 1000);
        System.out.print("Arreglo Generado de 5000 Valores: ");
        printArray(arr5000, 5000);
        System.out.print("Arreglo Generado de 10000 Valores: ");
        printArray(arr10000, 10000);
        System.out.print("Arreglo Generado de 30000 Valores: ");
        printArray(arr30000, 30000);
    }
    
    // Método para imprimir un número limitado de elementos del arreglo
    public void printArray(int[] array, int limit) {
        for (int i = 0; i < limit && i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
    //Metodo que ordena los arreglos
    public void ordenarArreglos() {
        if (arr10 == null) {
            System.out.println("Debe generar los arreglos primero (opción 1).");
            return;
        }

        System.out.println("\nOrdenando arreglos con los tres métodos de ordenamiento...");

        // Ordenamiento por Método Burbuja
        System.out.println("\nMétodo Burbuja");
        medirTiempoOrdenamiento("Burbuja", arr10, arr100, arr1000, arr5000, arr10000, arr30000);

        // Ordenamiento por Método Selección
        System.out.println("\nMétodo Selección");
        medirTiempoOrdenamiento("Seleccion", arr10, arr100, arr1000, arr5000, arr10000, arr30000);

        // Ordenamiento por Método Inserción
        System.out.println("\nMétodo Inserción");
        medirTiempoOrdenamiento("Insercion", arr10, arr100, arr1000, arr5000, arr10000, arr30000);
    }


    public void medirTiempoOrdenamiento(String metodo, int[]... arreglos) {
        for (int[] arr : arreglos) {
            int[] copia = arr.clone();
            long startTime = System.nanoTime();
            switch (metodo) {
                case "Burbuja" -> ordenarBurbuja(copia);
                case "Seleccion" -> ordenarSeleccion(copia);
                case "Insercion" -> ordenarInsercion(copia);
            }
            long endTime = System.nanoTime();
            double tiempoSegundos = (endTime - startTime) / 1_000_000_000.0;
    
            // Formatear el tiempo para evitar notación científica
            String tiempoFormateado = String.format("%.6f", tiempoSegundos);
    
            System.out.println("Con " + copia.length + " valores el tiempo es de " + tiempoFormateado + " seg.");
        }
    }
    
    // Metodo que realizara las busqueda 
    public void Busquedas() {
        if (arr10 == null) {
            System.out.println("Debe generar y ordenar los arreglos primero (opción 1 y luego opción 2).");
            return;
        }

        realizarBusqueda(arr10, 9);
        realizarBusqueda(arr100, 98);
        realizarBusqueda(arr1000, 957);
        realizarBusqueda(arr5000, 4000);
        realizarBusqueda(arr10000, 9876);
        realizarBusqueda(arr30000, 29475);
    }
    // Metodo que busca los valores especificados
    public void realizarBusqueda(int[] arr, int posicion) {
        if (arr == null || posicion >= arr.length) {
            System.out.println("La posición " + posicion + " excede el tamaño del arreglo o el arreglo no está inicializado.");
            return;
        }
    
        // Capturamos el valor que corresponde a la posición en el arreglo original
        int target = arr[posicion];
        System.out.println("\nBuscando el valor en posición " + posicion + " (valor: " + target + ") en el arreglo de tamaño " + arr.length + ":");
    
        // Ordenar el arreglo antes de la búsqueda binaria
        ordenarInsercion(arr); // Usa cualquier método de ordenamiento
        System.out.println("Arreglo ordenado para la búsqueda binaria.");
    
        // Medir tiempo para la Búsqueda binaria normal
        long startNormal = System.nanoTime();
        int resultadoNormal = BinarySearch(arr, target);
        long endNormal = System.nanoTime();
        double tiempoNormal = (endNormal - startNormal) / 1_000_000_000.0;
    
        if (resultadoNormal >= 0) {
            System.out.println("Resultado Búsqueda Normal: Encontrado en índice " + resultadoNormal + " (valor: " + arr[resultadoNormal] + ")");
        } else {
            System.out.println("Resultado Búsqueda Normal: No encontrado");
        }
        System.out.println("Tiempo de ejecución Búsqueda Normal: " + String.format("%.6f", tiempoNormal) + " seg.");
    
        // Medir tiempo para la Búsqueda binaria recursiva
        long startRecursiva = System.nanoTime();
        int resultadoRecursiva = BinarySearchRecursive(arr, target, 0, arr.length - 1);
        long endRecursiva = System.nanoTime();
        double tiempoRecursiva = (endRecursiva - startRecursiva) / 1_000_000_000.0;
    
        if (resultadoRecursiva >= 0) {
            System.out.println("Resultado Búsqueda Recursiva: Encontrado en índice " + resultadoRecursiva + " (valor: " + arr[resultadoRecursiva] + ")");
        } else {
            System.out.println("Resultado Búsqueda Recursiva: No encontrado");
        }
        System.out.println("Tiempo de ejecución Búsqueda Recursiva: " + String.format("%.6f", tiempoRecursiva) + " seg.");
    }
    
    
    
    //Metodo de Ordenamiento Burbuja
    public void ordenarBurbuja(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    //Metodo de Ordenamiento Seleccion 
    public void ordenarSeleccion(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
    }
    //Metodo de Ordenamiento Insercion 
    public void ordenarInsercion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
    //Metodo de Busqueda Binaria Normal
    public int BinarySearch(int[] arr, int valorB) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == valorB) {
                return mid;
            }
            if (arr[mid] < valorB) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
    //Metodo de Busqueda Binaria Recursiva 
    public int BinarySearchRecursive(int[] arr, int valorB, int left, int right) {
        if (left > right) return -1;
        int mid = left + (right - left) / 2;
        if (arr[mid] == valorB) return mid;
        if (arr[mid] > valorB) return BinarySearchRecursive(arr, valorB, left, mid - 1);
        return BinarySearchRecursive(arr, valorB, mid + 1, right);
    }
}

