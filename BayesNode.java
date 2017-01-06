import java.util.Map;

/**
 * Created by Amit on 06/01/2017.
 */
public class BayesNode {
    private int nodeId;
    private int resourceId;
    private int type; // {BRC, Resource, Blockage}
    private Map<String, Double> probs;

    public BayesNode(){}

    public double findProbability(boolean brc, boolean key){
        StringBuffer keyRep = null;
        if (brc){
            keyRep.append("T");
        }else{
            keyRep.append("F");
        }
        if (key){
            keyRep.append("T");
        }else{
            keyRep.append("F");
        }
        return probs.get(keyRep);
    }
}
