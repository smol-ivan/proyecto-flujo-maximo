// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.IOException;

// public class Lector {

//     public Graph leerGrafo(String nombreArchivo) throws IOException {
//         Graph graph = null;

//         try (BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo))) {
//             String linea;
//             int numeroVertices = 0;
//             // Leer la primera línea para obtener el número de vértices y aristas
//             if ((linea = lector.readLine()) != null) {
//                 String[] partes = linea.trim().split(" ");
//                 if (partes.length != 3) {
//                     throw new IllegalArgumentException("Formato de primera línea inválido en el archivo: " + linea);
//                 }

//                 numeroVertices = Integer.parseInt(partes[1]);
//                 graph = new Graph(numeroVertices);
//             } else {
//                 throw new IOException("Archivo vacío o primera línea inválida");
//             }

//             // Leer líneas subsiguientes y llenar la lista de adyacencia
//             while ((linea = lector.readLine()) != null) {
//                 String[] partes = linea.trim().split(" ");
//                 if (partes.length != 4) {
//                     throw new IllegalArgumentException("Formato de línea inválido en el archivo: " + linea);
//                 }

//                 String tipo = partes[0];
//                 int origen = Integer.parseInt(partes[1]);
//                 int destino = Integer.parseInt(partes[2]);
//                 int peso = Integer.parseInt(partes[3]);

//                 if (tipo.equals("a")) {
//                     // Solo procesar las líneas que indican conexión (c)
//                     graph.addEdge(origen, destino, peso);
//                 }
//             }
//         }

//         return graph;
//     }
// }

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Lector {

    public Graph leerGrafo(String nombreArchivo) throws IOException {
        Graph graph = null;

        try (BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            int numeroVertices = 0;
            int numeroAristas = 0;
            
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

            // Leer líneas subsiguientes y llenar la lista de adyacencia
            for (int i = 0; i < numeroAristas; i++) {
                if ((linea = lector.readLine()) != null) {
                    String[] partes = linea.trim().split(" ");
                    if (partes.length != 3) {
                        throw new IllegalArgumentException("Formato de línea inválido en el archivo: " + linea);
                    }

                    int origen = Integer.parseInt(partes[0]);
                    int destino = Integer.parseInt(partes[1]);
                    int peso = Integer.parseInt(partes[2]);

                    graph.addEdge(origen, destino, peso);
                } else {
                    throw new IOException("El archivo no contiene suficientes líneas");
                }
            }
        }

        return graph;
    }
}
