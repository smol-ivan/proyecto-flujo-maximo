// import java.util.ArrayList;
// import java.util.List;

// public class Graph {
//     int n; // Número de nodos
//     List<Edge>[] adj; // Lista de adyacencia
//     int[][] capacity; // Matriz de capacidades

//     @SuppressWarnings("unchecked")
//     public Graph(int n) {
//         this.n = n;
//         adj = new ArrayList[n];
//         for (int i = 0; i < n; i++) {
//             adj[i] = new ArrayList<>();
//         }
//         capacity = new int[n][n];
//     }

//     public void addEdge(int from, int to, int cap) {
//         Edge edge = new Edge(from, to, cap);
//         adj[from].add(edge);
//         adj[to].add(new Edge(to, from, 0));
//         capacity[from][to] = cap;
//     }

// }


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {
    int n; // Número de nodos
    List<Edge>[] adj; // Lista de adyacencia
    int[][] capacity; // Matriz de capacidades

    @SuppressWarnings("unchecked")
    public Graph(int n) {
        this.n = n;
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        capacity = new int[n][n];
    }

    public void addEdge(int from, int to, int cap) {
        Edge edge = new Edge(from, to, cap);
        adj[from].add(edge);
        adj[to].add(new Edge(to, from, 0));
        capacity[from][to] = cap;
    }

    // Implementación del algoritmo Ford-Fulkerson
    public int fordFulkerson(int source, int sink) {
        int maxFlow = 0;
        int[][] residualCapacity = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                residualCapacity[i][j] = capacity[i][j];
            }
        }

        int[] parent = new int[n];
        while (bfs(source, sink, parent, residualCapacity)) {
            int pathFlow = Integer.MAX_VALUE;
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, residualCapacity[u][v]);
            }

            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                residualCapacity[u][v] -= pathFlow;
                residualCapacity[v][u] += pathFlow;
            }

            maxFlow += pathFlow;
        }

        try {
            generateDotFile("maxflow.dot", residualCapacity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return maxFlow;
    }

    private boolean bfs(int source, int sink, int[] parent, int[][] residualCapacity) {
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;
        parent[source] = -1;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (Edge edge : adj[u]) {
                int v = edge.to;
                if (!visited[v] && residualCapacity[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        return visited[sink];
    }

    // Generar archivo DOT para visualizar el camino del flujo máximo
    public void generateDotFile(String filePath, int[][] residualCapacity) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write("digraph G {\n");
        for (int i = 0; i < n; i++) {
            for (Edge edge : adj[i]) {
                if (residualCapacity[i][edge.to] < capacity[i][edge.to]) {
                    writer.write(String.format("  %d -> %d [label=\"%d/%d\"];\n", i, edge.to,
                            capacity[i][edge.to] - residualCapacity[i][edge.to], capacity[i][edge.to]));
                }
            }
        }
        writer.write("}");
        writer.close();
    }

    // Clase interna para representar una arista
    class Edge {
        int from, to, capacity;

        public Edge(int from, int to, int capacity) {
            this.from = from;
            this.to = to;
            this.capacity = capacity;
        }
    }
}

