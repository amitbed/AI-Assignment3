import java.util.HashMap;

/**
 * Created by Amit on 06/01/2017.
 */
public class ResourceNode extends BayesNode{


    public ResourceNode(int nodeIdInGraph, int resourceId, double resourceProbability){
        makeNodeId(nodeIdInGraph, resourceId);
        cpt = new HashMap<>();
        buildProbabilityTable(resourceProbability);
    }

    private void buildProbabilityTable(double resourceProbability) {
        cpt.put("T",resourceProbability);
    }

    @Override
    public String toString() {
        return ("R" + id);
    }
}
