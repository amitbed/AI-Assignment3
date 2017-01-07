import java.util.HashMap;

/**
 * Created by Amit on 06/01/2017.
 */
public class BRCNode extends BayesNode {

    public BRCNode(double pbrc){
        probs = new HashMap<>();
        buildProbabilityTable(pbrc);
    }

    private void buildProbabilityTable(double pbrc) {
        probs.put("T",pbrc);
    }

    @Override
    public String toString() {
        return "BRC";
    }
}
