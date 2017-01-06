import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) {

		if (args.length < 1) {
			System.out.println("Add the name of the file representing the graph");
			return;
		}
		
		Graph graph = initializeGraphFromFile(args[0]);
		if (graph == null){
			System.out.println("Graph initialization failed");
			return;
		}
		buildBayesNetwork();
		//Simulator simulator = new Simulator(graph, 2); // 2 players
		
		Scanner sc = new Scanner(System.in);
		sc.close();		
	}

	private static void buildBayesNetwork() {

	}

	private static Graph initializeGraphFromFile(String fileName) {
		// read from file
		File file = null;
		Scanner sc = null;
		Graph graph = null;
		try {
			file = new File(fileName);
			sc = new Scanner(file);
			if (!sc.hasNextLine()) {
				sc.close();
				return null;
			}

			double pbrc = 0;
			int numberOfNodes = -1;
			int numberOfLocks = 0;
			int i=0;
			while (i < 3 && sc.hasNextLine()){
				StringTokenizer stk = new StringTokenizer(sc.nextLine());
				String nextToken = stk.nextToken();
				if (nextToken.equals("#PBRC")){
					pbrc = Double.parseDouble(stk.nextToken());
				}else if (nextToken.equals("#V")) {
					numberOfNodes = Integer.parseInt(stk.nextToken());
				}else if (nextToken.equals("#B")){
					numberOfLocks = Integer.parseInt(stk.nextToken());
				}
				i++;
			}
			graph = new Graph(numberOfNodes);
			graph.setPBRC(pbrc);
			graph.setNumOfLocks(numberOfLocks);
			//reading the rest of the file
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				parseLine(graph, line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (sc != null) {
			sc.close();
		}
		// print the graph
		if (graph != null) {
			graph.printGraph();
		}
		return graph;
	}
	
	private static void parseLine(Graph graph, String line) {
		if (line.length() == 0) {
			return;
		}
		StringTokenizer st = new StringTokenizer(line);
		while (st.hasMoreTokens()){
			String token = st.nextToken();
			if (token == null || token.charAt(0) == ';'){
				return;
			}
			if (token.equals("#E")){ // need to read "from", "to" and "weight"
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken().substring(1));
				Edge newEdge = new Edge(to, weight);
				graph.addEdge(from, newEdge);
			}else if (token.equals("#V")){ // need to read keys and locks
				int nodeId = Integer.parseInt(st.nextToken());
				Node node = graph.getNode(nodeId);
				while (st.hasMoreTokens()){
					String tk = st.nextToken();
					if (tk.charAt(0)==';') {
						break;
					}
					if (tk.equals("K")){
						int keyId = Integer.parseInt(st.nextToken());
						double keyProbability = Double.parseDouble(st.nextToken());
						node.addKey(keyId, keyProbability);
					}
				}
			}
		}
	}
}
