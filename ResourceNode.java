import java.util.HashMap;

/**
 * Created by Amit on 06/01/2017.
 */
public class ResourceNode extends BayesNode{
    private int nodeIdInGraph;
    private int resourceId;
    private double resourceProbability;

    public ResourceNode(int nodeIdInGraph, int resourceId, double resourceProbability){
        this.nodeIdInGraph = nodeIdInGraph;
        this.resourceId = resourceId;
        this.resourceProbability = resourceProbability;
        probs = new HashMap<>();
        buildProbabilityTable(resourceProbability);
    }

    private void buildProbabilityTable(double resourceProbability) {
        probs.put("T",resourceProbability);
    }

    @Override
    public String toString() {
        return ("R" + nodeIdInGraph+ "," + resourceId);
    }
}
