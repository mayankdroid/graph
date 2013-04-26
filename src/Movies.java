import java.io.*;
import java.util.*;


public class Movies {
	
	public static void main(String[] args) throws FileNotFoundException
	{
		ListGraph graph = new ListGraph();
		
		String fileName = "/users/bestja/documents/E - Data Structures/movies.txt";
		File text = new File(fileName);
		Scanner in = new Scanner(text);
		
		while (in.hasNext())
		{
			String current = in.nextLine();
			while (current.length() > 0)
			{
				String item = "";
				boolean found = false;
				while (!found && current.length() > 0)
				{
					if (current.charAt(0) == '/')
						found = true;
					else
						item += current.charAt(0);
					current = current.substring(1);
				}
				graph.insertVertex(item);
			}
				
		}
		
		graph.sortVertices();
		in = new Scanner(text);
		
		while (in.hasNextLine())
		{
			String current = in.nextLine();
			
			String movie = "";
			boolean movieFound = false;
			while (!movieFound)
			{
				if (current.charAt(0) == '/')
					movieFound = true;
				else
					movie += current.charAt(0);
				current = current.substring(1);
			}
			
			while (current.length() > 0)
			{
				String actor = "";
				boolean actorFound = false;
				while (!actorFound && current.length() > 0)
				{
					if (current.charAt(0) == '/')
						actorFound = true;
					else
						actor += current.charAt(0);
					current = current.substring(1);
				}
				graph.insertEdge(movie, actor);
			}
		}
		
		try
		{ 
			File file = new File("/users/bestja/desktop/output.txt");
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(graph.toString());
			bw.close();
			System.out.print("This graph has " + graph.numberOfVertices() + " vertices and " + graph.numberOfEdges() + " edges.");
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

}
