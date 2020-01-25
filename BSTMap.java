public class BSTMap<K extends Comparable<K>, T> implements Map<K, T> {
	public BSTNode<K, T> root; // Do not change this
	
	public BSTMap() {
		root = null;
	}
	
	// Return the size of the map.
	public int size() {
		return size(root);
	}
	
	private int size( BSTNode<K, T> n) {
		if(n == null)
		return 0;
		return 1+size(n.left)+size(n.right);
	}

	// Return true if the map is full.
	public boolean full() {
		return false;
	}

	// Remove all elements from the map.
	public void clear() {
		root = null;
	}

	// Update the data of the key k if it exists and return true. If k does not exist, the method returns false.
	public boolean update(K k, T e) {
		
		BSTNode<K,T> p = root ;
		if(root == null)
			return false;
		boolean found = false;
		while(p != null) {
		
			if(k.compareTo(p.key) == 0){
				found = true;
				break;
				
			}else if(k.compareTo(p.key) > 0)
			p = p.right;
		else
			p = p.left;
		}
		
		if(!found)
			return false;
		//------------
		
		
		
		p.data = e;
		
		return true;
	}

	/*public boolean find(K key) {
		BSTNode<K,T> p = root , q = root;
		if(root == null)
			return false;
		
		while(p != null) {
			q=p;
			if(key.compareTo(p.key) == 0){
				current = p;
				return true;
				
			}else if(key.compareTo(p.key) < 0)
			p = p.left;
		else
			p = p.right;
		}
		current = q;
		return false;

	}*/
	// Search for element with key k and returns a pair containing true and its data if it exists. If k does not exist, the method returns false and null.
	public Pair<Boolean, T> retrieve(K k){
		
		BSTNode<K,T> p = root;
		if(root == null)
			return new Pair(false,null);
		
		boolean found = false;
		while(p != null) {
			
			if(k.compareTo(p.key) == 0){
				found = true;
				break;
				
			}else if(k.compareTo(p.key) > 0)
			p = p.right;
		else
			p = p.left;
		}
		if(!found)
			return new Pair(false,null);
		
		return new Pair(true , p.data);
	}

	// Insert a new element if does not exist and return true. If k already exists, return false.
	public boolean insert(K k, T e) {
		BSTNode<K,T> newNode = new BSTNode(k,e);
		BSTNode<K,T> p = root;
		BSTNode<K,T> q = p;
		
		if(root == null) {
			root = newNode;
			return true;
		}
		
		while(p != null) {
			q=p;
			if(k.compareTo(p.key) == 0)
				return false;
			
			else if(k.compareTo(p.key) > 0)
			p = p.right;
		else
			p = p.left;
		}	
			// q is pointing to parent of the new key
			if (k.compareTo(q.key) > 0)
				q.right = newNode;
			else
				q.left = newNode;

		
		return true;

	}

	// Remove the element with key k if it exists and return true. If the element does not exist return false.
	public boolean remove(K k) {
		boolean found = false;
		BSTNode<K,T> p = root;
		if(root == null)
			return false;
		
		while(p != null) {
			if(k.compareTo(p.key) == 0){
				found = true;
				break;
			}else if(k.compareTo(p.key) > 0)
			p = p.right;
		else
			p = p.left;
		}
		
		if(!found)
			return false;
		
		BSTNode<K,T> q = remove_aux(k, root, false);

		return true;
	}
	private BSTNode<K,T> remove_aux(K key, BSTNode<K,T> p, boolean flag) {
		BSTNode<K,T> q, child = null;
		if(p == null)
			return null;
		if(key.compareTo(p.key) > 0)
			p.right = remove_aux(key, p.right, flag); 

		else if(key.compareTo(p.key) < 0)
			p.left = remove_aux(key, p.left, flag);
		else {
			flag= true;
			if (p.right != null && p.left != null){ //two children
				q = find_min(p.right);
				p.data = q.data;
				p.key = q.key;
				p.right = remove_aux(q.key, p.right, flag);
			}
			
			else {
				if (p.left == null) //one child
					child = p.right;
				else if (p.right == null) //one child
					child = p.left;
				return child;
			}
		}
		return p;
	}

	private BSTNode<K,T> find_min(BSTNode<K,T> p){
		if(p == null)
			return null;
		
		while(p.left != null){
			p = p.left;
		}
		
		return p;
	}


	// Return the list of keys in increasing order.
	public List<K> getKeys(){
		List<K> keys = new LinkedList();
		if(root == null)
		return keys;
		
		getKeys(keys , root);
		return keys;
	}
	
	private void getKeys(List<K> keys , BSTNode<K,T> p) {
		if(p == null)
			return;
		if(p.left !=null) {
			getKeys(keys,p.left);
			
		}
		
		keys.insert(p.key);
		
		if(p.right !=null) {
			getKeys(keys,p.right);
			
		}
		
	}
}

