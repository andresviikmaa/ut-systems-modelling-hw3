import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Pert2Dot
{
	

private static java.util.List<Edge> edges;
	
	
	private static java.util.Map<String,Node> nodes;
	
	public static void main( String[] args )
	{
		nodes = new  java.util.HashMap<String,Node>();
		edges = new java.util.ArrayList<Edge>();
	/* Load input file and parse*/
		if (args.length > 1) {
			LoadPertFile(args[0]);
			GenerateDotFile(args[1]);
		}
	}

	private static void GenerateDotFile( String filename ) {
		// TODO Auto-generated method stub
		for (String node:nodes.keySet()) {
			System.out.println(nodes.get(node).getName());
		}
	}

	private static void LoadPertFile( String filename ) {
		// TODO Auto-generated method stub
		
		 try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			    try {
			        String line = br.readLine();

			        while (line != null) {
			        	String[] parts = line.split(",");
			        	if (parts.length > 1) {
			        		nodes.put(parts[0], new Node(parts[0], Integer.parseInt(parts[1])));
			        		for (int i=2;i<parts.length;i++) {
			        			edges.add(new Edge(nodes.get(parts[i]), nodes.get(parts[0])));
			        			
			        		}
			        	}
			        	line = br.readLine();
			        }

			    } finally {
			        br.close();
			    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
