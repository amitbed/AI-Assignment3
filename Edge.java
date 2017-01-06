

public class Edge {
	
	private int to;
	private int weight;
	
	public Edge(int to, int weight){
		this.to = to;
		this.weight = weight;
	}
	
	public int getTo(){
		return this.to;
	}
	
	public int getWeight(){
		return this.weight;
	}
}
