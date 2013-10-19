import java.util.ArrayList;
import java.util.Comparator;
import java.io.FileOutputStream;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.io.Writer;
import java.io.File;
import java.io.OutputStreamWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import java.util.Collections;

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
			if (ValidateGraph() && CalculateCriticalPath()) {
			   GenerateDotFile(args[1]);
			}else{
				System.out.println("Invalid input file");
			}
		}
	}

	
	private static List<Node> getParents( Node node ){
		List<Node> parents = new ArrayList<Node>();
		
		for (Edge edge:edges){
			Node from = edge.getFromNode(); 
			Node to = edge.getToNode();
			
			if (to == node){
				parents.add(from);
			}
		}
		
		return parents;		
	}
	
	private static List<Node> getChildren( Node node ){
		ArrayList<Node> children = new java.util.ArrayList<Node>();
		
		for (Edge edge:edges){
			Node from = edge.getFromNode(); 
			Node to = edge.getToNode();
			
			if (from == node){
				children.add(to);
			}
		}
		return children;		
	}
	
	
	private static Boolean CalculateCriticalPath( ) {
		HashMap<Node,Integer> inedges = new HashMap<Node,Integer>();
		
		for (Node node:nodes.values()){
			inedges.put(node, getParents(node).size());
		}
		
		
		ArrayList<Node> order = new ArrayList<Node>(); 
		
		while (inedges.keySet().size() > 0){
			int prevlen = inedges.size();
			for (Node node: inedges.keySet()){
				if (inedges.get(node) == 0){
					order.add(node);
					for (Node child: getChildren(node)){
						int i = inedges.get(child);
						inedges.put(child, i - 1 );
					}
					inedges.remove(node);
					break;
				}
				
			}
			if (inedges.size() == prevlen){
				//there is a cycle
				return false;
			}
		}

		
		for (Node node:order){
			if (getParents(node).size() == 0){
				node.setEFT(node.getDuration());
				node.setEST(0);
			}else{
				node.setEST(Collections.max(getParents(node), new Comparator<Node>(){
					@Override
					public int compare(Node arg0, Node arg1) {
						return arg0.getEFT() - arg1.getEFT(); 
					}
				}).getEFT());
				
				node.setEFT(node.getEST() + node.getDuration());
				// max([(i.EFT) for i in getParents(node)])
			}
		}
		
		int PFT = Collections.max(nodes.values(), new Comparator<Node>(){
			@Override
			public int compare(Node arg0, Node arg1) {
				return arg0.getEFT() - arg1.getEFT(); 
			}
		}).getEFT();
		
		Collections.reverse(order);
		for (Node node: order){
			if (getChildren(node).size() == 0){
				node.setLFT(PFT);
				node.setLST(node.getLFT() - node.getDuration());
			}else{
				node.setLFT(Collections.min(getChildren(node), new Comparator<Node>(){
					@Override
					public int compare(Node arg0, Node arg1) {
						return arg0.getLST() - arg1.getLST(); 
					}
				}).getLST());
				node.setLST(node.getLFT() - node.getDuration());
			}
			
		}
		
		for (Node node: order){
			node.setSlack(node.getLST() - node.getEST());
			if (node.getSlack() == 0){
				node.setOn_critical_path(true);
			}
		}
		return true;
		
		
		
		
	}

	private static boolean ValidateGraph( ) {
		// TODO Auto-generated method stub
		// single start node
		// single end node
<<<<<<< HEAD
		// not asyclic
		Boolean foundStart = false;
		Boolean foundEnd = false;
		for (String key:nodes.keySet()) {
			Node node = nodes.get(key);
			Boolean skipStartCheck = false;
			Boolean skipEndCheck = false;
			for (Edge edge:edges){
				if (edge.getToNode() == node) skipStartCheck = true;			
				if (edge.getFromNode() == node) skipEndCheck = true;			
			}
			if (!skipStartCheck) {
				if (foundStart == true) return false; // second start node
				foundStart = true;
			}
			if (!skipEndCheck) {
				if (foundEnd == true) return false; // second end node
				foundEnd = true;
			}
		}
=======
		// not acyclic
>>>>>>> d021074e7e32c922cc6f87142a72294c0da07f72
		
		return foundStart && foundEnd;
	}

	private static void GenerateDotFile( String filename ) {
		try {
			 File statText = new File(filename);
		        FileOutputStream is = new FileOutputStream(statText);
		        OutputStreamWriter osw = new OutputStreamWriter(is);    
		        Writer w = new BufferedWriter(osw);
		        w.write("digraph G  {\n");
				for (String key:nodes.keySet()) {
					Node node = nodes.get(key);
					w.write(node.toString()+"\n");
				}
				for (Edge edge:edges){
					w.write(edge.toString()+"\n");			
				}
				w.write("}\n");

		        w.close();
			} catch ( IOException ioe ) { ioe.printStackTrace(); }
		
		
		
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
