import java.util.*;

public class Camion {

    public static void dijkstra(int[][] matriz, int inicio, int destino, int numNodos) {
        int[] distancia = new int[numNodos];
        boolean[] visitado = new boolean[numNodos];
        int[] previo = new int[numNodos];
        Arrays.fill(distancia, Integer.MAX_VALUE);
        Arrays.fill(previo, -1);
        distancia[inicio] = 0;

        for (int i = 0; i < numNodos - 1; i++) {
            int u = minDistancia(distancia, visitado, numNodos);
            visitado[u] = true;

            for (int v = 0; v < numNodos; v++) {
                if (!visitado[v] && matriz[u][v] != 0 &&
                    distancia[u] != Integer.MAX_VALUE &&
                    distancia[u] + matriz[u][v] < distancia[v]) {

                    distancia[v] = distancia[u] + matriz[u][v];
                    previo[v] = u;
                }
            }
        }

        if (distancia[destino] == Integer.MAX_VALUE) {
            System.out.println("No hay ruta disponible entre los puntos.");
        } else {
            System.out.println("Distancia mínima del camión: " + distancia[destino]);
            System.out.print("Ruta más corta: ");
            imprimirRuta(previo, destino);
            System.out.println((char) (destino + 'A'));
        }
    }

    public static int minDistancia(int[] distancia, boolean[] visitado, int n) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int v = 0; v < n; v++) {
            if (!visitado[v] && distancia[v] <= min) {
                min = distancia[v];
                minIndex = v;
            }
        }
        return minIndex;
    }
     public static void imprimirRuta(int[] previo, int j) {
        if (previo[j] == -1) {
            System.out.print((char) (j + 'A') + " -> ");
            return;
        }
        imprimirRuta(previo, previo[j]);
        System.out.print((char) (j + 'A') + " -> ");
    }
      public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Número de puntos (nodos): ");
        int numNodos = scanner.nextInt();
        int[][] matriz = new int[numNodos][numNodos];

        System.out.println("Ingresa la matriz de adyacencia (peso entre nodos, 0 si no hay conexión):");
        for (int i = 0; i < numNodos; i++) {
            for (int j = 0; j < numNodos; j++) {
                matriz[i][j] = scanner.nextInt();
            }
        }

        scanner.nextLine();

        System.out.print("Punto de partida del camión (letra): ");
        char inicioChar = scanner.nextLine().toUpperCase().charAt(0);

        System.out.print("Punto de entrega del paquete (letra): ");
        char destinoChar = scanner.nextLine().toUpperCase().charAt(0);

        int inicio = inicioChar - 'A';
        int destino = destinoChar - 'A';

        dijkstra(matriz, inicio, destino, numNodos);
    }
}
