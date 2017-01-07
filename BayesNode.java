import java.util.Map;

/**
 * Created by Amit on 06/01/2017.
 */
public abstract class BayesNode {

    protected Map<String, Double> probs;

    public BayesNode(){
    }

    public abstract String toString();
}
