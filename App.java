public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        // Lector lector = new Lector();
        // Graph graph = lector.leerGrafo("macaco.txt");
        LectorGrafo lector = new LectorGrafo();
        Graph graph = lector.leerGrafo("macaco.txt");
        // System.out.println("He leido el grafo");
        // FordFulkerson fordFulkerson = new FordFulkerson(graph, 1, 5);
        // System.out.println("He creado el objeto FordFulkerson");
        // int maxFlow = fordFulkerson.fordFulkersonAndGenerateDOTFile();
        // System.out.println("He calculado el flujo máximo");
        // System.out.println("Max flow: " + maxFlow);
        int maxflow = graph.fordFulkerson(5, 1);
        System.out.println("He calculado el flujo máximo");
        System.out.println("Max flow: " + maxflow);
    }
}
