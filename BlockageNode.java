import java.util.HashMap;
import java.util.List;

/**
 * Created by Amit on 06/01/2017.
 */
public class BlockageNode extends BayesNode {
    private int nodeIdInGraph;
    private int blockageId;


    public BlockageNode(int nodeIdInGraph, int blockageId){
        this.nodeIdInGraph = nodeIdInGraph;
        this.blockageId = blockageId;
        probs = new HashMap<>();
    }

    /*private void buildProbabilityTable(int numOfNeighborsWithSameResources) {
        StringBuilder key = null;
        boolean isBRC;
        for (int brc=0; brc < 2; brc++){
            int resCounter = 0;
            if (brc == 0) {
                key.append("T");
                isBRC = true;
            } else {
                key.append("F");
                isBRC = false;
            }
            for (int resource=0; resource < resources.length; resource++) {
                if (resources[resource]) {
                    key.append("T");
                    resCounter++;
                } else {
                    key.append("F");
                }
            }
            probs.put(key,getProbability(resCounter, isBRC));
            key = null;
            }
        }
    }*/

    private double getProbability(int numberOfNeighboringResources, boolean isBRC) {
        if (isBRC){
            if (numberOfNeighboringResources == 0){
                return 0.001;
            }else if (numberOfNeighboringResources == 1){
                return 0.1;
            }
        }else {
            if (numberOfNeighboringResources == 0) {
                return 0.01;
            } else if (numberOfNeighboringResources == 1) {
                return 0.2;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return ("R" + nodeIdInGraph+ "," + blockageId);
    }

    /*public double findProbability(boolean brc, boolean[] resources){
        StringBuffer keyRep = null;
        if (brc){
            keyRep.append("T");
        }else{
            keyRep.append("F");
        }
        for (int i=0; i < resources.length; i++) {
            if (resources[i]) {
                keyRep.append("T");
            } else {
                keyRep.append("F");
            }
        }
        return probs.get(keyRep);
    }*/
}
