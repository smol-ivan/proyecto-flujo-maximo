public class Edge {
    
    int from, to, capacity, flow;

    Edge(int from, int to, int capacity) {
        this.from = from;
        this.to = to;
        this.capacity = capacity;
        this.flow = 0;
    }
}