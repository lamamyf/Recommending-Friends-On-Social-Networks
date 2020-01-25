
public class LinkedList<T> implements List<T> {

	  private Node<T>head;
	  private Node<T> current;
	    
	    
		public LinkedList() {
			head = null;
			current= null;
		}
		
		
	    public boolean empty() {
			return head == null;
		}
	    
		public boolean full() {
			return false;
		}
		public void findFirst() {
			current = head;
		}
		public void findNext() {
			current = current.next;
		}
		public boolean last() {
			
			return current.next == null;
		}
		public T retrieve() {
			return current.data;
		}
		public void update(T e) {
			current.data = e;
		}
		
		public void insert(T e) {
			Node<T> n = new Node<T>(e);
			
			if(empty())
				head = current = n;
			else {
				n.next = current.next;
				current.next = n;
				current = current.next;
			}
			
				
		}
		
		public void remove () {
			if (current == head) {
				head = head.next;
			}else {
				Node<T> tmp = head;

				while (tmp.next != current)
					tmp = tmp.next;

				tmp.next = current.next;
			}

			if (current.next == null)
				current = head;
			else
				current = current.next;
		}



	@Override
	public int size() {
		if(empty())
		return 0;
		
		int c = 1;
		Node<T> temp = head;
		while(temp.next != null) {
			c++;
			temp = temp.next;
		}
		
		return c;
	}

	@Override
	public boolean exists(T e) {
		if(empty())
		return false;
	
		Node<T> temp = head;
		while(temp != null) {
			if(temp.data.equals(e))
				return true;
			temp = temp.next;
		}
		
		return false;

	}

}
