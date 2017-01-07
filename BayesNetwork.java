import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by Amit on 06/01/2017.
 */
public class BayesNetwork {

    private List<BayesNode> nodes;
    //private List<List<Edge>> edges;
    private List<BayesEdge> edges;
    private double pbrc;

    public BayesNetwork(Graph graph){
        nodes = new Vector<>();
        edges = new Vector<>();
      //  edges = new ArrayList<List<Edge>>(n+1);
        edges.add(null); // first element is empty assuming that the graph contains nodes from 1 - N
        pbrc = graph.getPBRC();
    }

    public void addBRCNode() {
        nodes.add(new BRCNode(pbrc));
    }

    public void addResourceNode(int nodeId, int key, double keyProb) {
        nodes.add(new ResourceNode(nodeId, key, keyProb));
    }

    public void addBlockageNode(int nodeId, int blockageId) {
        nodes.add(new BlockageNode(nodeId, blockageId));
    }

    public void addEdges() {
        for (BayesNode node : nodes) {
            if (node instanceof BlockageNode) {
                createEdgeFromResourcesToBlockages(node);
            } else if (node instanceof BRCNode) {
                createEdgeToAllBlockageNodes(node);
            }
        }
    }

    private void createEdgeFromResourcesToBlockages(BayesNode node) {

    }

    private void createEdgeToAllBlockageNodes(BayesNode node) {
        for (BayesNode bNode: nodes){
            if (bNode instanceof BlockageNode){
                addEdge(node, bNode);
            }
        }
    }

    public void addEdge(BayesNode from,BayesNode to){
        edges.add(new BayesEdge(from, to));
    }
}
