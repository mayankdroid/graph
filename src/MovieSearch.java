import java.io.*;
import java.util.*;


public class MovieSearch {
	
	public static void main(String[] args) throws FileNotFoundException, InterruptedException
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
		
		in = new Scanner(System.in);
		
		while (true)
		{
			System.out.println("Name the first actor:");
			String actor1 = in.nextLine();
			System.out.println("Name the second actor:");
			String actor2 = in.nextLine();
			System.out.println("\n" + graph.findConnection2(actor1, actor2) + "\n\n\n\n\n\n");
			Thread.sleep(3000);
		}
	}

}
