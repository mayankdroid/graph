
public class ArrayGraph implements Graph {
	
	private Vertex[] vertices;
	private boolean[][] edges;
	
	public ArrayGraph(Object[] initialVertices)
	{
		vertices = new Vertex[initialVertices.length];
		for (int i = 0; i < initialVertices.length; i++)
			vertices[i] = new Vertex(initialVertices[i], i);
		edges = new boolean[initialVertices.length][];
		for (int i = 0; i < initialVertices.length; i++)
			edges[i] = new boolean[initialVertices.length];
	}
	
	public void insertVertex(Object newVertex)
	{
		Vertex[] newVertices = new Vertex[vertices.length+1];
		for (int i = 0; i < vertices.length; i++)
			newVertices[i] = vertices[i];
		newVertices[vertices.length] = new Vertex(newVertex, vertices.length);
		
		boolean[][] newEdges = new boolean[edges.length+1][];
		for (int i = 0; i < edges.length; i++)
		{
			boolean[] newEdgeSet = new boolean[edges[i].length+1];
			for (int n = 0; n < edges[i].length; n++)
				newEdgeSet[n] = edges[i][n];
			newEdges[i] = newEdgeSet;
		}
		newEdges[edges.length] = new boolean[edges.length+1];
		
		vertices = newVertices;
		edges = newEdges;
	}
	
	public void removeVertex(Object oldVertex)
	{
		Vertex[] newVertices = new Vertex[vertices.length-1];
		boolean found = false;
		for (int i = 0; i < vertices.length; i++)
		{
			if (vertices[i].getObject().equals(oldVertex))
				found = true;
			else if (found)
				newVertices[i-1] = new Vertex(vertices[i].getObject(), i-1);
			else
				newVertices[i] = vertices[i];
		}
		
		int index = -1;
		for (int i = 0; i < vertices.length; i++)
			if (vertices[i].getObject().equals(oldVertex))
				index = i;
		boolean[][] newEdges = new boolean[edges.length-1][];
		for (int i = 0; i < edges.length; i++)
		{
			if (i < index)
			{
				boolean[] newEdgeSet = new boolean[edges[i].length-1];
				for (int n = 0; n < edges[i].length; n++)
				{
					if (n < index)
						newEdgeSet[n] = edges[i][n];
					else if (n > index)
						newEdgeSet[n-1] = edges[i][n];
				}
				newEdges[i] = newEdgeSet;
			}
			
			else if (i > index)
			{
				boolean[] newEdgeSet = new boolean[edges[i].length-1];
				for (int n = 0; n < edges[i].length; n++)
				{
					if (n < index)
						newEdgeSet[n] = edges[i][n];
					else if (n > index)
						newEdgeSet[n-1] = edges[i][n];
				}
				newEdges[i-1] = newEdgeSet;
			}
		}
		
		vertices = newVertices;
		edges = newEdges;
	}
	
	public void insertEdge(Object object1, Object object2)
	{
		int index1 = -1;
		int index2 = -1;
		for (int i = 0; i < vertices.length; i++)
		{
			if (vertices[i].getObject().equals(object1))
				index1 = i;
			if (vertices[i].getObject().equals(object2))
				index2 = i;
		}
		
		edges[index1][index2] = true;
		edges[index2][index1] = true;
	}
	
	public void removeEdge(Object object1, Object object2)
	{
		int index1 = -1;
		int index2 = -1;
		for (int i = 0; i < vertices.length; i++)
		{
			if (vertices[i].getObject().equals(object1))
				index1 = i;
			if (vertices[i].getObject().equals(object2))
				index2 = i;
		}
		
		edges[index1][index2] = false;
		edges[index2][index1] = false;
	}
	
	public int numberOfVertices()
	{
		return vertices.length;
	}
	
	public int numberOfEdges()
	{
		int count = 0;
		for (int i = 0; i < edges.length; i++)
			for (int n = i; n < edges[i].length; n++)
				if (edges[i][n])
					count++;
		return count;
	}
	
	public String toString()
	{
		String output = "";
		for (int i = 0; i < edges.length; i++)
		{
			output += vertices[i].getObject().toString() + ": ";
			for (int n = 0; n < edges[i].length; n++)
				if (edges[i][n])
					output += vertices[n].getObject().toString() + ", ";
			output = output.substring(0, output.length()-2) + "\n";
		}
		return output;
	}
	
	class Vertex {
		
		private Object object;
		private int index;
		
		public Vertex(Object initialObject, int initialIndex)
		{
			object = initialObject;
			index = initialIndex;
		}
		
		public boolean equals(Vertex other)
		{
			return this.object.equals(other.object);
		}
		
		public void changeIndex(int newIndex)
		{
			index = newIndex;
		}
		
		public Object getObject()
		{
			return object;
		}
		
		public int getIndex()
		{
			return index;
		}
		
	}

}
