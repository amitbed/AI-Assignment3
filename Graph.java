import java.util.*;

public class Graph {
	
	private List<List<Edge>> graph;
	private Vector<Node> nodes;
	private double pbrc;

	//for bfs algorithm
	private boolean[] visited;
	private int[] prev;
	private int[] distance;
	private Queue<Integer> queue;
	private int numOfLocks;

	/**
	 * Constructor
	 * @param n - Indicates the number of nodes
	 */
	public Graph(int n){
		if (n < 0){
			System.out.println("file is not in the correct format");
			return;
		}
		pbrc = 0;
		graph = new ArrayList<List<Edge>>(n+1);
		graph.add(null); // first element is empty assuming that the graph contains nodes from 1 - N
		for (int i = 0; i < n; i++){
			graph.add(new ArrayList<Edge>());
		}
		nodes = new Vector<Node>(n);
		for (int i = 0; i < n; i++){
			addNode(new Node(i+1));
		}
	}
	
	public int getNumberOfNodes(){
		return nodes.size();
	}
	public Graph clone(){
		Graph clone = new Graph(this.nodes.size());
		for (Node n : this.nodes){
			clone.getNode(n.getId()).getKeys().addAll(n.getKeys()); // copy all keys
			clone.getNode(n.getId()).getLocks().addAll(n.getLocks()); // copy all locks
		}
		for (List<Edge> edges : graph){ // clone edges
			if (edges == null){
				//do nothing
			}else{
				for (Edge e: edges){
					Edge newEdge = new Edge(e.getTo(), e.getWeight());
					clone.addEdgeForClone(graph.indexOf(edges), newEdge);
				}
			}
		}
		return clone;
	}
	
	public void addEdge(int from, Edge e){
		graph.get(from).add(e);
		Edge opositeEdge = new Edge(from, e.getWeight()); // because its an undirected graph
		graph.get(e.getTo()).add(opositeEdge);
	}
	
	public void addEdgeForClone(int from, Edge e){
		graph.get(from).add(e);
	}
	
	private void addNode(Node n){
		nodes.add(n);
	}
	
	public Edge getEdge (int from, int to){
		List<Edge> edges = graph.get(from);
		for (Edge e : edges){
			if(e.getTo() == to){
				return e;
			}
		}
		return null;
	}
	public Node getNode(int nodeId){
		for (Node n : nodes){
			if (nodeId == n.getId()){
				return n;
			}
		}
		return null;
	}
	
	public void printGraph(){
		System.out.println("nodes:");
		for(Node n : nodes){
			System.out.println("node " + n.getId() + " keys: " + n.getKeys().toString() + " probs: " + n.getKeyProbs().toString() + " locks: " + n.getLocks().toString());
		}
	
		/*System.out.println("edges:");
		
		for (int i = 1; i < graph.size(); i++){
			for (Edge e: graph.get(i)){
				System.out.println("edge from " + (i) + " to " + e.getTo() + " weight: " + e.getWeight());
			}
		}*/
	}
	
	public List<Node> getNeighbors(int nodeId){
		List<Node> neighbors = new ArrayList<>();
		for (Edge e : graph.get(nodeId)){
			neighbors.add(getNode(e.getTo()));
		}
		return neighbors;
	}
	
	/*public List<Node> getAvailableNeighbors(Agent agent ,int nodeId){
		List<Node> neighbors = new ArrayList<>();
		for (Edge e : graph.get(nodeId)){
			Node neighborNode = getNode(e.getTo());
			boolean isPermitted = true;
			for (Integer lock : neighborNode.getLocks()){
				if (!agent.hasKey(lock)){
					isPermitted = false;
					break;
				}
			}
			if (isPermitted){
				neighbors.add(neighborNode);
			}
		}
		return neighbors;
	}
	
	public List<Integer> collectKeysFromNode(int currentNode, int capacity) {
		Node node = getNode(currentNode);
		List<Integer> nodeKeys = node.getKeys();
		if (nodeKeys.isEmpty()){
			return null;
		}
		List<Integer> keys = new Vector<Integer>();
		if (Simulator.getResourceLimit() == (-1)){
			for (Integer nodeKey : nodeKeys){
				keys.add(nodeKey);
			}
			nodeKeys.clear();
			return keys;
		}
		for(int i= 0; i < capacity; i++){
			Integer smallestKey = node.getSmallestKey();
			keys.add(smallestKey);
			nodeKeys.remove(smallestKey);
		}
		return keys;
	}*/
	
	public List<Integer> removeLocksFromNode(int nodeId){
		List<Integer> lockIds = new Vector<Integer>();
		lockIds.addAll(getNode(nodeId).getLocks());
		getNode(nodeId).getLocks().clear();
		return lockIds;
	}

	public void removeNodeAndAttachedEdges(int nodeId) {
		List<Edge> edges = graph.get(nodeId);
		for (Edge e : edges){
			graph.get(e.getTo()).remove(getEdge(e.getTo(), nodeId)); //remove all edges to nodeId
		}
		graph.get(nodeId).clear(); // remove all edges from nodeId
		nodes.remove(getNode(nodeId)); // remove node
	}
	
	public List<Node> getCloneOfNodes(){
		Vector<Node> cloned = (Vector<Node>) nodes.clone();
		return cloned;
	}

	public int getDistance(int nodeA, int nodeB){
		bfs(nodeA);
		return distance[nodeB];
	}
	private void initializeBfs(){
		queue = new LinkedList<>();
		visited = new boolean[getNumberOfNodes()+1];
		prev = new int[getNumberOfNodes()+1];
		distance = new int[getNumberOfNodes()+1];
		for (int i=1; i <= getNumberOfNodes(); i++){
			visited[i] = false;
			prev[i] = -1;
			distance[i] = Integer.MAX_VALUE;
		}
	}

	private void bfs(int rootNodeId){
		initializeBfs();
		int d = 0;
		queue.add(rootNodeId);
		visited[rootNodeId] = true;
		distance[rootNodeId] = d;

		while (!queue.isEmpty()){
			int nodeId = queue.remove();
			List<Node> neighbors = getNeighbors(nodeId);
			Comparator<Node> c = new Comparator<Node>() {

				@Override
				public int compare(Node n1, Node n2) {
					return n1.getId() - n2.getId(); // so that the sort would be from smallest to largest
				}

			};
			Collections.sort(neighbors, c); // by sorting the nodes, the shortest path in cases of ties, will be with the smallest node
			for (Node neighbor : neighbors){
				if (!visited[neighbor.getId()]){
					queue.add(neighbor.getId());
					visited[neighbor.getId()] = true;
					prev[neighbor.getId()] = nodeId;
					distance[neighbor.getId()] = distance[nodeId]+1;
				}
			}
		}
	}

	public void setPBRC(double pbrc) {
		this.pbrc = pbrc;
	}

	public void setNumOfLocks(int numOfLocks) {
		this.numOfLocks = numOfLocks;
	}
}
