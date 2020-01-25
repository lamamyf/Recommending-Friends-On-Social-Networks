import java.io.File;
import java.util.Scanner;

public class Recommender {

	// Return the top k recommended friends for user i using the popular nodes method. If i does not exist, return null. In case of a tie, users with the lowest id are selected.
	/*public static <K extends Comparable<K>> PQK<Double, K> recommendPop(Graph<K> g, K i, int k) {
		if(!g.isNode(i))
			return null;
		List<K> nodes = g.getNodes();
		List<K> neighb = g.neighb(i);
 		
		for(int j =0 ;j<neighb.size();j++) {//remove neighbres
			boolean found = false;
			nodes.findFirst();
			for(int z=0 ; z<nodes.size();z++) {
				if(neighb.retrieve()==nodes.retrieve() || nodes.retrieve()==i) {
					found = true;
					break;
				}
				nodes.findNext();
			}
			if(found)
				nodes.remove();
		}
		
		PQK<Double, K> rec = new PQKImp(k);
		
 		for(int j = 0 ; j<k && j<g.getNodes().size() ; j++) {
 			nodes.findFirst();
 			K highestDeg = nodes.retrieve();
 			for(int z = 0; z<nodes.size() ; z++){
 				if(g.deg(nodes.retrieve()) > g.deg(highestDeg))
 					highestDeg = nodes.retrieve();
 				nodes.findNext();
 			}
 			
 			double deg = g.deg(highestDeg);
 			rec.enqueue(deg , highestDeg);
 			
 			nodes.findFirst();
 			for(int z = 0; z<nodes.size() ; z++) {//remove the one we just enqueued
 				if(nodes.retrieve() == highestDeg) {
 					nodes.remove();
 					break;
 				}
 				nodes.findNext();
 			}
 			
 		}
		
		
		return rec;
	}*/
	
	public static <K extends Comparable<K>> PQK<Double, K> recommendPop(Graph<K> g, K i, int k) {
		if( g == null || !g.isNode(i) )
			return null;
		List<K> nodes = g.getNodes();
		List<K> neighb = g.neighb(i);
		PQK<Double, K> popularUsers  = new PQKImp(k);
		nodes.findFirst();
		for(int j = 0 ; j< nodes.size() ; j++ ){
			if(nodes.retrieve() !=i && !neighb.exists(nodes.retrieve())) 
				popularUsers.enqueue((double)g.deg(nodes.retrieve()),nodes.retrieve());
			nodes.findNext();
		}
		
		return popularUsers;
	}
	
	

	// Return the top k recommended friends for user i using common neighbors method. If i does not exist, return null. In case of a tie, users with the lowest id are selected.
	public static <K extends Comparable<K>> PQK<Double, K> recommendCN(Graph<K> g, K i, int k) {
		if( g == null || !g.isNode(i))
		return null;
		
		List<K> nodes = g.getNodes();
		List<K> neighb = g.neighb(i);
		PQK<Double, K> commonUsers  = new PQKImp(k);
		
			nodes.findFirst();
		for(int j = 0 ; j<nodes.size() ; j++) {
			
			if(nodes.retrieve() != i && !neighb.exists(nodes.retrieve()) ){
				double commonN = 0;
				List<K> n = g.neighb(nodes.retrieve());
				n.findFirst();
				for(int z = 0 ; z<n.size() ; z++) {
					if(g.isEdge(i,n.retrieve()))
						commonN++;
					n.findNext();
				}
				
				commonUsers.enqueue(commonN, nodes.retrieve());
			}//end if
			nodes.findNext();
		}
		
		return commonUsers;
		
	}

	// Read graph from file. The file is a text file where each line contains an edge. The end and start of the edge are separated by space(s) or tabs.
	public static Graph<Integer> read(String fileName) {

		try {
			Graph<Integer> g = new MGraph<Integer>();
			Scanner scanner = new Scanner(new File(fileName));
			while (scanner.hasNextInt()) {
				int i = scanner.nextInt();
				g.addNode(i);
				int j = scanner.nextInt();
				g.addNode(j);
				g.addEdge(i, j);
			}
			scanner.close();
			return g;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
