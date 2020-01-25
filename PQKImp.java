public class PQKImp<P extends Comparable<P>, T> implements PQK<P, T> {
	int k;
	PQNode<P,T> head;
	
	int size;
	public PQKImp(int k) {
		this.k = k;
		head = null;
		size = 0;
	}
	
	// Return the length of the queue
		public int length(){
			return size;
		}

		// Enqueue a new element. The queue keeps the k elements with the highest priority. In case of a tie apply FIFO.
		public void enqueue(P pr, T e) {
			if(k == 0 )
				return;
			if(size == k) {
				
				PQNode<P,T> t = head;
				PQNode<P,T> p = head;
				
				while(t.next !=null) { 
					p=t;
					t=t.next;
				}
				
				if(pr.compareTo(t.priority)<= 0)
					return;
				
				p.next = null;
				
				size--;
			}
			
			PQNode<P,T> newNode = new PQNode<P,T>(pr,e);
			if((size == 0) || (pr.compareTo(head.priority) > 0 )) {
				newNode.next = head;
				head = newNode;
			}else {
				PQNode<P,T> p = head;
				PQNode<P,T> q = null;
				while((p != null) && (pr.compareTo(p.priority) <= 0)) {
					q = p;
					p = p.next;
				}
				newNode.next = p;
				q.next = newNode;
			}
			
			size++;			

		}

		// Serve the element with the highest priority. In case of a tie apply FIFO.
		public Pair<P, T> serve(){
			if(size == 0)
			return new Pair(null , null);
			
			PQNode temp = head;
			Pair p = new Pair(temp.priority,temp.data);
			
			head = head.next;
			//if(head == null)
				//tail = null;
			size--;
			
			return p;
		}
	
}
