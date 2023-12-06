package GraphUtil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Graph {
    // nodes are numbered by their index in a vector, each of them keeps track of outgoing edges
    Edge[][] links;

    public Graph(Edge[][] l){
        links = l;
    }

    public Graph(String file){
        this.read(file);
    }

    /**
     * number of nodes in graph
     * @return
     */
    public int getNumberNodes(){
        return this.links.length;
    }

    /**
     * get neighbors of a node with index node
     * @param node
     * @return
     */
    public Edge[] getNeighbors(int node){
        return this.links[node];
    }

    /**
     * returns weight of edge between node0 and node1
     * if edge does not exist, Integer.MAX_VALUE is returned
     * @param node0
     * @param node1
     * @return
     */
    public int getWeight(int node0, int node1){
        Edge[] neigh = this.getNeighbors(node0);
        for (int i = 0; i < neigh.length; i++){
            Edge e = neigh[i];
            if (e.getNeighbor() == node1){
                return e.getWeight();
            }
        }
        return Integer.MAX_VALUE;
    }


    /**
     * takes file where each line is a list of neighbor,latency pairs;
     *       each pair is separated by a semicolon
     * @param file
     */
    public void read(String file){
        Vector<Edge[]> edges = new  Vector<Edge[]>();
        try (BufferedReader bf = new BufferedReader(new FileReader(file)))
        {
            String line;
            while (!(line=bf.readLine()).isEmpty()){
                String[] pairs = line.split(";");
                Edge[] neighs = new Edge[pairs.length];
                for (int j = 0; j <  pairs.length; j++){
                    String[] el = pairs[j].split(",",2);
                    neighs[j] = new Edge(Integer.parseInt(el[0]), Integer.parseInt(el[1]));
                }
                edges.add(neighs);
            }
            this.links = new Edge[edges.size()][];
            for (int i = 0; i < this.links.length; i++){
                this.links[i] = edges.get(i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
