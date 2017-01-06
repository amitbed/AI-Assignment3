import java.util.List;
import java.util.Vector;

public class Node {
	private int id;
	private Vector<Integer> keys;
	private Vector<Double> keyProbs;
	private Vector<Integer> locks;
	
	
	public Node(int id){
		this.id = id;
		keys = new Vector<Integer>();
		keyProbs = new Vector<Double>();
		locks = new Vector<Integer>();
	}
	
	public void addKey(int keyId, double keyProb){
		keys.add(keyId);
		keyProbs.add(keyProb);
	}
	
	public void addLock(int lockId){
		locks.add(lockId);
	}

	public int getId(){
		return id;
	}

	public List<Integer> getKeys() {
		return keys;
	}

	public List<Double> getKeyProbs() {
		return keyProbs;
	}

	public List<Integer> getLocks() {
		return locks;
	}

	public int getSmallestKey() {
		int smallest = keys.get(0);
		for (int key : keys){
			if (key < smallest){
				smallest = key;
			}
		}
		return smallest;
	}
}
