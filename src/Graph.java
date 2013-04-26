
public interface Graph {
	
	public void insertVertex(Object newVertex);
	public void removeVertex(Object oldVertex);
	public void insertEdge(Object object1, Object object2);
	public void removeEdge(Object object1, Object object2);
	public int numberOfVertices();
	public int numberOfEdges();
	public String toString();

}
