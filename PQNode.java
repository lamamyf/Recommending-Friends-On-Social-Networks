
public class PQNode<P extends Comparable<P>, T> {
	P priority;
	T data;
	PQNode<P,T> next;
	public PQNode(P priority , T data) {
		this.priority = priority;
		this.data = data;
		next = null;
	}
}
