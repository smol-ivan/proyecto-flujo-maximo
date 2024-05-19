// import java.io.BufferedWriter;
// import java.io.FileWriter;
// import java.io.IOException;
// import java.util.LinkedList;
// import java.util.Queue;

// public class FordFulkerson {
//     private Graph graph;
//     private int source, sink;
//     private int[] parent;

//     public FordFulkerson(Graph graph, int source, int sink) {
//         this.graph = graph;
//         this.source = source;
//         this.sink = sink;
//         this.parent = new int[graph.n];
//     }

//     // Breadth-First Search to find the path with available capacity
//     // private boolean bfs() 
//         boolean[] visited = new boolean[graph.n];
//         Queue<Integer> queue = new LinkedList<>();
//         queue.add(source);
//         visited[source] = true;

//         while (!queue.isEmpty()) {
//             int u = queue.poll();

//             for (Edge edge : graph.adj[u]) {
//                 int v = edge.to;
//                 if (!visited[v] && graph.capacity[u][v] > edge.flow) {
//                     queue.add(v);
//                     parent[v] = u;
//                     visited[v] = true;
//                     if (v == sink) {
//                         return true;
//                     }
//                 }
//             }
//         }
//         return false;
//     }

//     // Ford-Fulkerson algorithm modified to generate a DOT file for the maximum flow path
//     public int fordFulkersonAndGenerateDOTFile() {
//         int maxFlow = 0;

//         // Augment the flow while there is a path from source to sink
//         while (bfs()) {
//             int pathFlow = Integer.MAX_VALUE;

//             // Find the maximum flow through the path found.
//             for (int v = sink; v != source; v = parent[v]) {
//                 int u = parent[v];
//                 for (Edge edge : graph.adj[u]) {
//                     if (edge.to == v) {
//                         pathFlow = Math.min(pathFlow, edge.capacity - edge.flow);
//                     }
//                 }
//             }

//             // update residual capacities of the edges and reverse edges along the path
//             for (int v = sink; v != source; v = parent[v]) {
//                 int u = parent[v];
//                 for (Edge edge : graph.adj[u]) {
//                     if (edge.to == v) {
//                         edge.flow += pathFlow;
//                     }
//                 }
//                 for (Edge edge : graph.adj[v]) {
//                     if (edge.to == u) {
//                         edge.flow -= pathFlow;
//                     }
//                 }
//             }
//             maxFlow += pathFlow;
//         }

//         generateDOTFile();
//         return maxFlow;
//     }

//     // Method to generate a DOT file representing the maximum flow path
//     private void generateDOTFile() {
//         try {
//             BufferedWriter writer = new BufferedWriter(new FileWriter("max_flow_path.dot"));
//             writer.write("digraph G {\n");

//             // Traverse the path from sink to source and write edges to the DOT file
//             for (int v = sink; v != source; v = parent[v]) {
//                 int u = parent[v];
//                 writer.write(u + " -> " + v + ";\n");
//             }

//             writer.write("}");
//             writer.close();
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }
// }
