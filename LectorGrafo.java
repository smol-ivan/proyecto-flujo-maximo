import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LectorGrafo {

    public Graph leerGrafo(String nombreArchivo) throws IOException {
        Graph graph = null;

        try (BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            int numeroVertices, numeroAristas;

            // Leer la primera línea para obtener el número de vértices y aristas
            if ((linea = lector.readLine()) != null) {
                String[] partes = linea.trim().split(" ");
                if (partes.length != 2) {
                    throw new IllegalArgumentException("Formato de primera línea inválido en el archivo: " + linea);
                }

                numeroVertices = Integer.parseInt(partes[0]);
                numeroAristas = Integer.parseInt(partes[1]);

                graph = new Graph(numeroVertices);
            } else {
                throw new IOException("Archivo vacío o primera línea inválida");
            }

            // Leer líneas subsiguientes y añadir aristas al grafo
            for (int i = 0; i < numeroAristas; i++) {
                if ((linea = lector.readLine()) != null) {
                    String[] partes = linea.trim().split(" ");
                    if (partes.length != 3) {
                        throw new IllegalArgumentException("Formato de línea inválido en el archivo: " + linea);
                    }

                    int origen = Integer.parseInt(partes[0]); 
                    int destino = Integer.parseInt(partes[1]);
                    int capacidad = Integer.parseInt(partes[2]);

                    graph.addEdge(origen-1, destino-1, capacidad);
                } else {
                    throw new IOException("Archivo terminado antes de leer todas las aristas");
                }
            }
        }

        return graph;
    }
}
