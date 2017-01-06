import java.util.Map;

/**
 * Created by Amit on 06/01/2017.
 */
public abstract class BayesNode {
    public static int BRC = 0;
    public static int RESOURCE = 0;
    public static int BLOCKAGE = 0;

    protected int type; // {BRC, Resource, Blockage}
    protected Map<String, Double> probs;

    public BayesNode(int type){
        this.type = type;
    }


}
