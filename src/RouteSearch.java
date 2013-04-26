import java.io.*;
import java.util.*;


public class RouteSearch {
	
	public static void main(String[] args) throws FileNotFoundException
	{
		ListGraph graph = new ListGraph();
		
		String fileName = "/users/bestja/documents/E - Data Structures/routes.txt";
		File text = new File(fileName);
		Scanner in = new Scanner(text);
		
		while (in.hasNext())
			graph.insertVertex(in.next());
		
		graph.sortVertices();
		in = new Scanner(text);
		
		while (in.hasNextLine())
		{
			String first = in.next();
			String second = in.next();
			graph.insertEdge(first, second);
		}
		
		System.out.print(graph.findConnection2("ATL", "LAX"));
	}

}
