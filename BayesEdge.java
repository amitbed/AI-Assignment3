/**
 * Created by Amit on 07/01/2017.
 */
public class BayesEdge {
    private BayesNode from;
    private BayesNode to;


    public BayesEdge(BayesNode from, BayesNode to) {
        this.from = from;
        this.to = to;
    }

    public BayesNode getFrom(){
        return from;
    }

    public BayesNode getTo(){
        return to;
    }

}
