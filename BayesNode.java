import java.util.Map;

/**
 * Created by Amit on 06/01/2017.
 */
public abstract class BayesNode {

    protected int id;
    protected Map<String, Double> cpt;

    public BayesNode(){
    }

    public abstract String toString();

    public static int makeNodeId(int nodeIdInGraph, int id){
        StringBuilder idStr = null;
        idStr.append(nodeIdInGraph);
        idStr.append(id);
        return Integer.parseInt(idStr.toString());
    }
}
