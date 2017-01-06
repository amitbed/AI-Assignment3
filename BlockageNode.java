import java.util.List;

/**
 * Created by Amit on 06/01/2017.
 */
public class BlockageNode extends BayesNode {
    private int nodeId;

    public BlockageNode(int nodeId, int resourceId){
        type = BLOCKAGE;
        this.nodeId = nodeId;
    }

    public double findProbability(boolean brc, boolean[] keys){
        StringBuffer keyRep = null;
        if (brc){
            keyRep.append("T");
        }else{
            keyRep.append("F");
        }
        for (int i=0; i < keys.length; i++) {
            if (keys[i]) {
                keyRep.append("T");
            } else {
                keyRep.append("F");
            }
        }
        return probs.get(keyRep);
    }
}
