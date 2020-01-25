public class MGraph<K extends Comparable<K>> implements Graph<K> {
	public Map<K, List<K>> adj; // Do not change this
	public MGraph() {
		adj = new BSTMap();
	}
	
	// Add a node to the graph if it does not exist and return true. If the node already exists, return false.
	public boolean addNode(K i) {
			List<K> l =adj.getKeys();
			if(l.exists(i))
				return false;
			return adj.insert(i, new LinkedList());
		}

		// Check if i is a node
	public boolean isNode(K i) {
		if(adj.size()==0)
			return false;
		List<K> keys = adj.getKeys();
		
			return keys.exists(i);
		}

		// Add an edge to the graph if it does not exist and return true. If i or j do not exist or the edge (i, j) already exists, return false.
	public boolean addEdge(K i, K j) {
		if(adj.size()==0)
			return false;
		List<K> keys = adj.getKeys();
			if(!keys.exists(i) || !keys.exists(j))
			return false;
			
			List<K> l1 = adj.retrieve(i).second;
			List<K> l2 = adj.retrieve(j).second;
			if(l1.exists(j))
				return false;

			if(l2.exists(i))
					return false;

			l1.insert(j);
			l2.insert(i);
			return true;
		}

		// Check if (i, j) is an edge.
	public boolean isEdge(K i, K j) {
		if(!adj.retrieve(i).first || !adj.retrieve(j).first)
		return false;
		
		
		List<K> l1 = adj.retrieve(i).second;
		List<K> l2 = adj.retrieve(j).second;
		
		if(l1.exists(j) && l2.exists(i))
			return true;
		return false;
	    }

		// Return the set of neighbors of node i. If i does not exist, the method returns null.
	public List<K> neighb(K i){
		if(adj.size() == 0)
			return null;
		
		if(!adj.retrieve(i).first)
		return null;
		return adj.retrieve(i).second;
		}

		// Return the degree (the number of neighbors) of node i. If i does not exist, the method returns -1.
	public int deg(K i) {
		if(adj.size() == 0)
			return -1;
		if(!adj.retrieve(i).first)
		return -1;
		
		return adj.retrieve(i).second.size();
		}

		// Return a list containing the nodes in increasing order.
	public List<K> getNodes(){
		return adj.getKeys();
	}
	
}
