package GraphUtil;

public class Edge {
    //edges consist of a neighbor represented by an index and a weight representing the latency in ms (note: source node is not included as known in implementation
    private int neighbor;
    private int weight;

    public Edge(int n, int w){
        this.neighbor = n;
        this.weight = w;
    }

    public int getNeighbor() {
        return neighbor;
    }

    public void setNeighbor(int neighbor) {
        this.neighbor = neighbor;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }



}
