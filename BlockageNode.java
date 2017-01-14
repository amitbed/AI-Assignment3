import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/**
 * Created by Amit on 06/01/2017.
 */
public class BlockageNode extends BayesNode {
    private List<Integer> parents;


    public BlockageNode(int nodeIdInGraph, int blockageId){
        makeNodeId(nodeIdInGraph, blockageId);
        parents = new Vector<>();
        parents.add(0); // BTC represented as 00
        //add parents
        cpt = new HashMap<>();
    }

    @Override
    public String toString() {
        return ("B" + id);
    }

    public void addParent(int bayesNodeId) {
        parents.add(bayesNodeId);
    }


    //--------------------------------MAYBE FOR FUTURE USE----------------------------------------------

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
            cpt.put(key,getProbability(resCounter, isBRC));
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
        return cpt.get(keyRep);
    }*/
}
