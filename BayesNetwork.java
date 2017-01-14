import java.util.List;
import java.util.Vector;

/**
 * Created by Amit on 06/01/2017.
 */
public class BayesNetwork {

    private final double pBRC;
    private List<BayesNode> nodes;

    public BayesNetwork(Graph graph){
        nodes = new Vector<>();
        pBRC = graph.getPBRC();
    }

    public void addBRCNode() {
        nodes.add(new ResourceNode(0,0,pBRC)); //BRC is represented as resource number 0
    }

    public void addResourceNode(int nodeId, int key, double keyProb) {
        nodes.add(new ResourceNode(nodeId, key, keyProb));
    }

    public void addBlockageNode(int nodeId, int blockageId, List<Integer> neighbors) {
        BlockageNode blockageNode = new BlockageNode(nodeId, blockageId);
        nodes.add(blockageNode);
        blockageNode.addParent(0);// BRC node
        for (int neighbor : neighbors){
            int bayesNodeId = BayesNode.makeNodeId(neighbor, blockageId);
            if (nodes.isExistResourceNode(bayesNodeId)){
                blockageNode.addParent(bayesNodeId);
            }
        }
    }
}
