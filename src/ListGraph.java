import java.util.*;


public class ListGraph implements Graph {
	
	private static final int MAXDEPTH = 4;
	
	private Vertex[] vertices;
	private HashSet<Object> unsorted;
	private ArrayList<LinkedList<Vertex>> adj;
	
	public ListGraph()
	{
		vertices = null;
		unsorted = new HashSet<Object>();
		adj = new ArrayList<LinkedList<Vertex>>();
	}
	
	public void insertVertex(Object newObject)
	{
		unsorted.add(newObject);
		adj.add(new LinkedList<Vertex>());
	}
	
	public void removeVertex(Object oldObject)
	{
		unsorted.remove(oldObject);
		adj.remove(adj.size()-1);
	}
	
	public void sortVertices()
	{
		Object[] temp = unsorted.toArray();
		temp = mergeSort(temp);
		vertices = new Vertex[temp.length];
		for (int i = 0; i < vertices.length; i++)
			vertices[i] = new Vertex(temp[i], i);
	}
	
	public void insertEdge(Object object1, Object object2)
	{
		int index1 = findIndex(object1);
		int index2 = findIndex(object2);
		LinkedList<Vertex> firstList = adj.get(index1);
		LinkedList<Vertex> secondList = adj.get(index2);
		firstList.add(vertices[index2]);
		secondList.add(vertices[index1]);
	}
	
	public void removeEdge(Object object1, Object object2)
	{
		int index1 = findIndex(object1);
		int index2 = findIndex(object2);
		LinkedList<Vertex> firstList = adj.get(index1);
		LinkedList<Vertex> secondList = adj.get(index2);
		
		ListIterator<Vertex> iter = firstList.listIterator();
		int i = 0;
		boolean done = false;
		while (iter.hasNext() && !done)
		{
			if (iter.next().getObject().equals(object2))
			{
				firstList.remove(i);
				done = true;
			}
			i++;
		}
		
		iter = secondList.listIterator();
		i = 0;
		done = false;
		while (iter.hasNext() && !done)
		{
			if (iter.next().getObject().equals(object1))
			{
				secondList.remove(i);
				done = true;
			}
			i++;
		}
	}
	
	public int numberOfVertices()
	{
		return unsorted.size();
	}
	
	public int numberOfEdges()
	{
		int count = 0;
		for (int i = 0; i < adj.size(); i++)
			count += adj.get(i).size();
		return count/2;
	}
	
	public String toString()
	{
		StringBuilder output = new StringBuilder();
		for (int i = 0; i < vertices.length; i++)
		{
			output.append(vertices[i].getObject().toString());
			output.append(": ");
			for (int n = 0; n < adj.get(i).size(); n++)
			{
				output.append(adj.get(i).get(n).getObject().toString());
				output.append(", ");
			}
			output.delete(output.length()-2, output.length());
			output.append("\n");
		}
		return output.toString();
	}
	
	public int findConnection1(Object object1, Object object2)
	{
		return depthFirstSearch(object1, object2, new int[0], (byte) 0);
	}
	
	public String findConnection2(Object object1, Object object2)
	{
		return breadthFirstSearch(object1, object2);
	}
	
	private int depthFirstSearch(Object object1, Object object2, int[] visited, byte count)
	{
		if (count > MAXDEPTH)
			return 1000000;
		int index1 = findIndex(object1);
		int index2 = findIndex(object2);
		int[] newVisited = new int[visited.length+1];
		for (int i = 0; i < visited.length; i++)
			newVisited[i] = visited[i];
		newVisited[visited.length] = index1;
		if (index1 == index2)
			return 0;
		
		LinkedList<Vertex> list = adj.get(index1);
		ListIterator<Vertex> iter = list.listIterator();
		int min = 1000000;
		while (iter.hasNext())
		{
			int current = 1000000;
			Vertex nextVertex = iter.next();
			boolean alreadyVisited = false;
			for (int i = 0; i < visited.length; i++)
				if (visited[i] == nextVertex.getIndex())
					alreadyVisited = true;
			if (!alreadyVisited)
				current = depthFirstSearch(nextVertex.getObject(), object2, newVisited, (byte) (count+1))+1;
			if (current < min)
				min = current;
		}
		return min;
	}
	
	private String breadthFirstSearch(Object object1, Object object2)
	{
		HashSet<Integer> visited1 = new HashSet<Integer>();
		HashSet<Integer> visited2 = new HashSet<Integer>();
		HashSet<Integer> visited3 = new HashSet<Integer>();
		HashSet<Integer> visited4 = new HashSet<Integer>();
		HashSet<Integer> visited5 = new HashSet<Integer>();
		HashSet<Integer> visited6 = new HashSet<Integer>();
		HashSet<Integer> visited7 = new HashSet<Integer>();
		HashSet<Integer> visited8 = new HashSet<Integer>();
		HashSet<Integer> visited9 = new HashSet<Integer>();
		HashSet<Integer> visited10 = new HashSet<Integer>();
		HashSet<Integer> visited11 = new HashSet<Integer>();
		HashSet<Integer> visited12 = new HashSet<Integer>();
		
		
		
		int initial = findIndex(object1);
		int target = findIndex(object2);
		if (initial == -1)
			return "The first entry is not valid.";
		if (target == -1)
			return "The second entry is not valid.";
		visited1.add(initial);
		if (initial == target)
			return object1.toString();
		
		
		
		LinkedList<Vertex> list1 = adj.get(initial);
		ListIterator<Vertex> iter1 = list1.listIterator();
		while (iter1.hasNext())
		{
			Vertex nextVertex1 = iter1.next();
			int nextIndex1 = nextVertex1.getIndex();
			boolean alreadyVisited1 = visited1.contains(nextIndex1);
			if (!alreadyVisited1)
			{
				visited2.add(nextIndex1);
				if (nextIndex1 == target)
					return object1.toString() + " --> " + nextVertex1.getObject().toString();
			}
		}
		
		
		
		iter1 = list1.listIterator();
		while (iter1.hasNext())
		{
			Vertex nextVertex1 = iter1.next();
			int nextIndex1 = nextVertex1.getIndex();
			boolean alreadyVisited1 = visited1.contains(nextIndex1);
			if (!alreadyVisited1)
			{
				LinkedList<Vertex> list2 = adj.get(nextIndex1);
				ListIterator<Vertex> iter2 = list2.listIterator();
				while (iter2.hasNext())
				{
					Vertex nextVertex2 = iter2.next();
					int nextIndex2 = nextVertex2.getIndex();
					boolean alreadyVisited2 = visited1.contains(nextIndex2) || visited2.contains(nextIndex2);
					if (!alreadyVisited2)
					{
						visited3.add(nextIndex2);
						if (nextIndex2 == target)
							return object1.toString() + " --> " + nextVertex1.getObject().toString() + " --> " + nextVertex2.getObject().toString();
					}
				}
			}
		}
		
		
		
		iter1 = list1.listIterator();
		while (iter1.hasNext())
		{
			Vertex nextVertex1 = iter1.next();
			int nextIndex1 = nextVertex1.getIndex();
			boolean alreadyVisited1 = visited1.contains(nextIndex1);
			if (!alreadyVisited1)
			{
				LinkedList<Vertex> list2 = adj.get(nextIndex1);
				ListIterator<Vertex> iter2 = list2.listIterator();
				while (iter2.hasNext())
				{
					Vertex nextVertex2 = iter2.next();
					int nextIndex2 = nextVertex2.getIndex();
					boolean alreadyVisited2 = visited1.contains(nextIndex2) || visited2.contains(nextIndex2);
					if (!alreadyVisited2)
					{
						LinkedList<Vertex> list3 = adj.get(nextIndex2);
						ListIterator<Vertex> iter3 = list3.listIterator();
						while (iter3.hasNext())
						{
							Vertex nextVertex3 = iter3.next();
							int nextIndex3 = nextVertex3.getIndex();
							boolean alreadyVisited3 = visited1.contains(nextIndex3) || visited2.contains(nextIndex3) || visited3.contains(nextIndex3);
							if (!alreadyVisited3)
							{
								visited4.add(nextIndex3);
								if (nextIndex3 == target)
									return object1.toString() + " --> " + nextVertex1.getObject().toString() + " --> " + nextVertex2.getObject().toString() + " --> " + nextVertex3.getObject().toString();
							}
						}
					}
				}
			}
		}
		
		
		
		iter1 = list1.listIterator();
		while (iter1.hasNext())
		{
			Vertex nextVertex1 = iter1.next();
			int nextIndex1 = nextVertex1.getIndex();
			boolean alreadyVisited1 = visited1.contains(nextIndex1);
			if (!alreadyVisited1)
			{
				LinkedList<Vertex> list2 = adj.get(nextIndex1);
				ListIterator<Vertex> iter2 = list2.listIterator();
				while (iter2.hasNext())
				{
					Vertex nextVertex2 = iter2.next();
					int nextIndex2 = nextVertex2.getIndex();
					boolean alreadyVisited2 = visited1.contains(nextIndex2) || visited2.contains(nextIndex2);
					if (!alreadyVisited2)
					{
						LinkedList<Vertex> list3 = adj.get(nextIndex2);
						ListIterator<Vertex> iter3 = list3.listIterator();
						while (iter3.hasNext())
						{
							Vertex nextVertex3 = iter3.next();
							int nextIndex3 = nextVertex3.getIndex();
							boolean alreadyVisited3 = visited1.contains(nextIndex3) || visited2.contains(nextIndex3) || visited3.contains(nextIndex3);
							if (!alreadyVisited3)
							{
								LinkedList<Vertex> list4 = adj.get(nextIndex3);
								ListIterator<Vertex> iter4 = list4.listIterator();
								while (iter4.hasNext())
								{
									Vertex nextVertex4 = iter4.next();
									int nextIndex4 = nextVertex4.getIndex();
									boolean alreadyVisited4 = visited1.contains(nextIndex4) || visited2.contains(nextIndex4) || visited3.contains(nextIndex4) || visited4.contains(nextIndex4);
									if (!alreadyVisited4)
									{
										visited5.add(nextIndex4);
										if (nextIndex4 == target)
											return object1.toString() + " --> " + nextVertex1.getObject().toString() + " --> " + nextVertex2.getObject().toString() + " --> " + nextVertex3.getObject().toString() + " --> " + nextVertex4.getObject().toString();
									}
								}
							}
						}
					}
				}
			}
		}
		
		
		
		iter1 = list1.listIterator();
		while (iter1.hasNext())
		{
			Vertex nextVertex1 = iter1.next();
			int nextIndex1 = nextVertex1.getIndex();
			boolean alreadyVisited1 = visited1.contains(nextIndex1);
			if (!alreadyVisited1)
			{
				LinkedList<Vertex> list2 = adj.get(nextIndex1);
				ListIterator<Vertex> iter2 = list2.listIterator();
				while (iter2.hasNext())
				{
					Vertex nextVertex2 = iter2.next();
					int nextIndex2 = nextVertex2.getIndex();
					boolean alreadyVisited2 = visited1.contains(nextIndex2) || visited2.contains(nextIndex2);
					if (!alreadyVisited2)
					{
						LinkedList<Vertex> list3 = adj.get(nextIndex2);
						ListIterator<Vertex> iter3 = list3.listIterator();
						while (iter3.hasNext())
						{
							Vertex nextVertex3 = iter3.next();
							int nextIndex3 = nextVertex3.getIndex();
							boolean alreadyVisited3 = visited1.contains(nextIndex3) || visited2.contains(nextIndex3) || visited3.contains(nextIndex3);
							if (!alreadyVisited3)
							{
								LinkedList<Vertex> list4 = adj.get(nextIndex3);
								ListIterator<Vertex> iter4 = list4.listIterator();
								while (iter4.hasNext())
								{
									Vertex nextVertex4 = iter4.next();
									int nextIndex4 = nextVertex4.getIndex();
									boolean alreadyVisited4 = visited1.contains(nextIndex4) || visited2.contains(nextIndex4) || visited3.contains(nextIndex4) || visited4.contains(nextIndex4);
									if (!alreadyVisited4)
									{
										LinkedList<Vertex> list5 = adj.get(nextIndex4);
										ListIterator<Vertex> iter5 = list5.listIterator();
										while (iter5.hasNext())
										{
											Vertex nextVertex5 = iter5.next();
											int nextIndex5 = nextVertex5.getIndex();
											boolean alreadyVisited5 = visited1.contains(nextIndex5) || visited2.contains(nextIndex5) || visited3.contains(nextIndex5) || visited4.contains(nextIndex5) || visited5.contains(nextIndex5);
											if (!alreadyVisited5)
											{
												visited6.add(nextIndex5);
												if (nextIndex5 == target)
													return object1.toString() + " --> " + nextVertex1.getObject().toString() + " --> " + nextVertex2.getObject().toString() + " --> " + nextVertex3.getObject().toString() + " --> " + nextVertex4.getObject().toString() + " --> " + nextVertex5.getObject().toString();
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		
		
		iter1 = list1.listIterator();
		while (iter1.hasNext())
		{
			Vertex nextVertex1 = iter1.next();
			int nextIndex1 = nextVertex1.getIndex();
			boolean alreadyVisited1 = visited1.contains(nextIndex1);
			if (!alreadyVisited1)
			{
				LinkedList<Vertex> list2 = adj.get(nextIndex1);
				ListIterator<Vertex> iter2 = list2.listIterator();
				while (iter2.hasNext())
				{
					Vertex nextVertex2 = iter2.next();
					int nextIndex2 = nextVertex2.getIndex();
					boolean alreadyVisited2 = visited1.contains(nextIndex2) || visited2.contains(nextIndex2);
					if (!alreadyVisited2)
					{
						LinkedList<Vertex> list3 = adj.get(nextIndex2);
						ListIterator<Vertex> iter3 = list3.listIterator();
						while (iter3.hasNext())
						{
							Vertex nextVertex3 = iter3.next();
							int nextIndex3 = nextVertex3.getIndex();
							boolean alreadyVisited3 = visited1.contains(nextIndex3) || visited2.contains(nextIndex3) || visited3.contains(nextIndex3);
							if (!alreadyVisited3)
							{
								LinkedList<Vertex> list4 = adj.get(nextIndex3);
								ListIterator<Vertex> iter4 = list4.listIterator();
								while (iter4.hasNext())
								{
									Vertex nextVertex4 = iter4.next();
									int nextIndex4 = nextVertex4.getIndex();
									boolean alreadyVisited4 = visited1.contains(nextIndex4) || visited2.contains(nextIndex4) || visited3.contains(nextIndex4) || visited4.contains(nextIndex4);
									if (!alreadyVisited4)
									{
										LinkedList<Vertex> list5 = adj.get(nextIndex4);
										ListIterator<Vertex> iter5 = list5.listIterator();
										while (iter5.hasNext())
										{
											Vertex nextVertex5 = iter5.next();
											int nextIndex5 = nextVertex5.getIndex();
											boolean alreadyVisited5 = visited1.contains(nextIndex5) || visited2.contains(nextIndex5) || visited3.contains(nextIndex5) || visited4.contains(nextIndex5) || visited5.contains(nextIndex5);
											if (!alreadyVisited5)
											{
												LinkedList<Vertex> list6 = adj.get(nextIndex5);
												ListIterator<Vertex> iter6 = list6.listIterator();
												while (iter6.hasNext())
												{
													Vertex nextVertex6 = iter6.next();
													int nextIndex6 = nextVertex6.getIndex();
													boolean alreadyVisited6 = visited1.contains(nextIndex6) || visited2.contains(nextIndex6) || visited3.contains(nextIndex6) || visited4.contains(nextIndex6) || visited5.contains(nextIndex6) || visited6.contains(nextIndex6);
													if (!alreadyVisited6)
													{
														visited7.add(nextIndex6);
														if (nextIndex6 == target)
															return object1.toString() + " --> " + nextVertex1.getObject().toString() + " --> " + nextVertex2.getObject().toString() + " --> " + nextVertex3.getObject().toString() + " --> " + nextVertex4.getObject().toString() + " --> " + nextVertex5.getObject().toString() + " --> " + nextVertex6.getObject().toString();
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		
		
		iter1 = list1.listIterator();
		while (iter1.hasNext())
		{
			Vertex nextVertex1 = iter1.next();
			int nextIndex1 = nextVertex1.getIndex();
			boolean alreadyVisited1 = visited1.contains(nextIndex1);
			if (!alreadyVisited1)
			{
				LinkedList<Vertex> list2 = adj.get(nextIndex1);
				ListIterator<Vertex> iter2 = list2.listIterator();
				while (iter2.hasNext())
				{
					Vertex nextVertex2 = iter2.next();
					int nextIndex2 = nextVertex2.getIndex();
					boolean alreadyVisited2 = visited1.contains(nextIndex2) || visited2.contains(nextIndex2);
					if (!alreadyVisited2)
					{
						LinkedList<Vertex> list3 = adj.get(nextIndex2);
						ListIterator<Vertex> iter3 = list3.listIterator();
						while (iter3.hasNext())
						{
							Vertex nextVertex3 = iter3.next();
							int nextIndex3 = nextVertex3.getIndex();
							boolean alreadyVisited3 = visited1.contains(nextIndex3) || visited2.contains(nextIndex3) || visited3.contains(nextIndex3);
							if (!alreadyVisited3)
							{
								LinkedList<Vertex> list4 = adj.get(nextIndex3);
								ListIterator<Vertex> iter4 = list4.listIterator();
								while (iter4.hasNext())
								{
									Vertex nextVertex4 = iter4.next();
									int nextIndex4 = nextVertex4.getIndex();
									boolean alreadyVisited4 = visited1.contains(nextIndex4) || visited2.contains(nextIndex4) || visited3.contains(nextIndex4) || visited4.contains(nextIndex4);
									if (!alreadyVisited4)
									{
										LinkedList<Vertex> list5 = adj.get(nextIndex4);
										ListIterator<Vertex> iter5 = list5.listIterator();
										while (iter5.hasNext())
										{
											Vertex nextVertex5 = iter5.next();
											int nextIndex5 = nextVertex5.getIndex();
											boolean alreadyVisited5 = visited1.contains(nextIndex5) || visited2.contains(nextIndex5) || visited3.contains(nextIndex5) || visited4.contains(nextIndex5) || visited5.contains(nextIndex5);
											if (!alreadyVisited5)
											{
												LinkedList<Vertex> list6 = adj.get(nextIndex5);
												ListIterator<Vertex> iter6 = list6.listIterator();
												while (iter6.hasNext())
												{
													Vertex nextVertex6 = iter6.next();
													int nextIndex6 = nextVertex6.getIndex();
													boolean alreadyVisited6 = visited1.contains(nextIndex6) || visited2.contains(nextIndex6) || visited3.contains(nextIndex6) || visited4.contains(nextIndex6) || visited5.contains(nextIndex6) || visited6.contains(nextIndex6);
													if (!alreadyVisited6)
													{
														LinkedList<Vertex> list7 = adj.get(nextIndex6);
														ListIterator<Vertex> iter7 = list7.listIterator();
														while (iter7.hasNext())
														{
															Vertex nextVertex7 = iter7.next();
															int nextIndex7 = nextVertex7.getIndex();
															boolean alreadyVisited7 = visited1.contains(nextIndex7) || visited2.contains(nextIndex7) || visited3.contains(nextIndex7) || visited4.contains(nextIndex7) || visited5.contains(nextIndex7) || visited6.contains(nextIndex7) || visited7.contains(nextIndex7);
															if (!alreadyVisited7)
															{
																visited8.add(nextIndex7);
																if (nextIndex7 == target)
																	return object1.toString() + " --> " + nextVertex1.getObject().toString() + " --> " + nextVertex2.getObject().toString() + " --> " + nextVertex3.getObject().toString() + " --> " + nextVertex4.getObject().toString() + " --> " + nextVertex5.getObject().toString() + " --> " + nextVertex6.getObject().toString() + " --> " + nextVertex7.getObject().toString();
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		
		
		iter1 = list1.listIterator();
		while (iter1.hasNext())
		{
			Vertex nextVertex1 = iter1.next();
			int nextIndex1 = nextVertex1.getIndex();
			boolean alreadyVisited1 = visited1.contains(nextIndex1);
			if (!alreadyVisited1)
			{
				LinkedList<Vertex> list2 = adj.get(nextIndex1);
				ListIterator<Vertex> iter2 = list2.listIterator();
				while (iter2.hasNext())
				{
					Vertex nextVertex2 = iter2.next();
					int nextIndex2 = nextVertex2.getIndex();
					boolean alreadyVisited2 = visited1.contains(nextIndex2) || visited2.contains(nextIndex2);
					if (!alreadyVisited2)
					{
						LinkedList<Vertex> list3 = adj.get(nextIndex2);
						ListIterator<Vertex> iter3 = list3.listIterator();
						while (iter3.hasNext())
						{
							Vertex nextVertex3 = iter3.next();
							int nextIndex3 = nextVertex3.getIndex();
							boolean alreadyVisited3 = visited1.contains(nextIndex3) || visited2.contains(nextIndex3) || visited3.contains(nextIndex3);
							if (!alreadyVisited3)
							{
								LinkedList<Vertex> list4 = adj.get(nextIndex3);
								ListIterator<Vertex> iter4 = list4.listIterator();
								while (iter4.hasNext())
								{
									Vertex nextVertex4 = iter4.next();
									int nextIndex4 = nextVertex4.getIndex();
									boolean alreadyVisited4 = visited1.contains(nextIndex4) || visited2.contains(nextIndex4) || visited3.contains(nextIndex4) || visited4.contains(nextIndex4);
									if (!alreadyVisited4)
									{
										LinkedList<Vertex> list5 = adj.get(nextIndex4);
										ListIterator<Vertex> iter5 = list5.listIterator();
										while (iter5.hasNext())
										{
											Vertex nextVertex5 = iter5.next();
											int nextIndex5 = nextVertex5.getIndex();
											boolean alreadyVisited5 = visited1.contains(nextIndex5) || visited2.contains(nextIndex5) || visited3.contains(nextIndex5) || visited4.contains(nextIndex5) || visited5.contains(nextIndex5);
											if (!alreadyVisited5)
											{
												LinkedList<Vertex> list6 = adj.get(nextIndex5);
												ListIterator<Vertex> iter6 = list6.listIterator();
												while (iter6.hasNext())
												{
													Vertex nextVertex6 = iter6.next();
													int nextIndex6 = nextVertex6.getIndex();
													boolean alreadyVisited6 = visited1.contains(nextIndex6) || visited2.contains(nextIndex6) || visited3.contains(nextIndex6) || visited4.contains(nextIndex6) || visited5.contains(nextIndex6) || visited6.contains(nextIndex6);
													if (!alreadyVisited6)
													{
														LinkedList<Vertex> list7 = adj.get(nextIndex6);
														ListIterator<Vertex> iter7 = list7.listIterator();
														while (iter7.hasNext())
														{
															Vertex nextVertex7 = iter7.next();
															int nextIndex7 = nextVertex7.getIndex();
															boolean alreadyVisited7 = visited1.contains(nextIndex7) || visited2.contains(nextIndex7) || visited3.contains(nextIndex7) || visited4.contains(nextIndex7) || visited5.contains(nextIndex7) || visited6.contains(nextIndex7) || visited7.contains(nextIndex7);
															if (!alreadyVisited7)
															{
																LinkedList<Vertex> list8 = adj.get(nextIndex7);
																ListIterator<Vertex> iter8 = list8.listIterator();
																while (iter8.hasNext())
																{
																	Vertex nextVertex8 = iter8.next();
																	int nextIndex8 = nextVertex8.getIndex();
																	boolean alreadyVisited8 = visited1.contains(nextIndex8) || visited2.contains(nextIndex8) || visited3.contains(nextIndex8) || visited4.contains(nextIndex8) || visited5.contains(nextIndex8) || visited6.contains(nextIndex8) || visited7.contains(nextIndex8) || visited8.contains(nextIndex8);
																	if (!alreadyVisited8)
																	{
																		visited9.add(nextIndex8);
																		if (nextIndex8 == target)
																			return object1.toString() + " --> " + nextVertex1.getObject().toString() + " --> " + nextVertex2.getObject().toString() + " --> " + nextVertex3.getObject().toString() + " --> " + nextVertex4.getObject().toString() + " --> " + nextVertex5.getObject().toString() + " --> " + nextVertex6.getObject().toString() + " --> " + nextVertex7.getObject().toString() + " --> " + nextVertex8.getObject().toString();
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		
		
		iter1 = list1.listIterator();
		while (iter1.hasNext())
		{
			Vertex nextVertex1 = iter1.next();
			int nextIndex1 = nextVertex1.getIndex();
			boolean alreadyVisited1 = visited1.contains(nextIndex1);
			if (!alreadyVisited1)
			{
				LinkedList<Vertex> list2 = adj.get(nextIndex1);
				ListIterator<Vertex> iter2 = list2.listIterator();
				while (iter2.hasNext())
				{
					Vertex nextVertex2 = iter2.next();
					int nextIndex2 = nextVertex2.getIndex();
					boolean alreadyVisited2 = visited1.contains(nextIndex2) || visited2.contains(nextIndex2);
					if (!alreadyVisited2)
					{
						LinkedList<Vertex> list3 = adj.get(nextIndex2);
						ListIterator<Vertex> iter3 = list3.listIterator();
						while (iter3.hasNext())
						{
							Vertex nextVertex3 = iter3.next();
							int nextIndex3 = nextVertex3.getIndex();
							boolean alreadyVisited3 = visited1.contains(nextIndex3) || visited2.contains(nextIndex3) || visited3.contains(nextIndex3);
							if (!alreadyVisited3)
							{
								LinkedList<Vertex> list4 = adj.get(nextIndex3);
								ListIterator<Vertex> iter4 = list4.listIterator();
								while (iter4.hasNext())
								{
									Vertex nextVertex4 = iter4.next();
									int nextIndex4 = nextVertex4.getIndex();
									boolean alreadyVisited4 = visited1.contains(nextIndex4) || visited2.contains(nextIndex4) || visited3.contains(nextIndex4) || visited4.contains(nextIndex4);
									if (!alreadyVisited4)
									{
										LinkedList<Vertex> list5 = adj.get(nextIndex4);
										ListIterator<Vertex> iter5 = list5.listIterator();
										while (iter5.hasNext())
										{
											Vertex nextVertex5 = iter5.next();
											int nextIndex5 = nextVertex5.getIndex();
											boolean alreadyVisited5 = visited1.contains(nextIndex5) || visited2.contains(nextIndex5) || visited3.contains(nextIndex5) || visited4.contains(nextIndex5) || visited5.contains(nextIndex5);
											if (!alreadyVisited5)
											{
												LinkedList<Vertex> list6 = adj.get(nextIndex5);
												ListIterator<Vertex> iter6 = list6.listIterator();
												while (iter6.hasNext())
												{
													Vertex nextVertex6 = iter6.next();
													int nextIndex6 = nextVertex6.getIndex();
													boolean alreadyVisited6 = visited1.contains(nextIndex6) || visited2.contains(nextIndex6) || visited3.contains(nextIndex6) || visited4.contains(nextIndex6) || visited5.contains(nextIndex6) || visited6.contains(nextIndex6);
													if (!alreadyVisited6)
													{
														LinkedList<Vertex> list7 = adj.get(nextIndex6);
														ListIterator<Vertex> iter7 = list7.listIterator();
														while (iter7.hasNext())
														{
															Vertex nextVertex7 = iter7.next();
															int nextIndex7 = nextVertex7.getIndex();
															boolean alreadyVisited7 = visited1.contains(nextIndex7) || visited2.contains(nextIndex7) || visited3.contains(nextIndex7) || visited4.contains(nextIndex7) || visited5.contains(nextIndex7) || visited6.contains(nextIndex7) || visited7.contains(nextIndex7);
															if (!alreadyVisited7)
															{
																LinkedList<Vertex> list8 = adj.get(nextIndex7);
																ListIterator<Vertex> iter8 = list8.listIterator();
																while (iter8.hasNext())
																{
																	Vertex nextVertex8 = iter8.next();
																	int nextIndex8 = nextVertex8.getIndex();
																	boolean alreadyVisited8 = visited1.contains(nextIndex8) || visited2.contains(nextIndex8) || visited3.contains(nextIndex8) || visited4.contains(nextIndex8) || visited5.contains(nextIndex8) || visited6.contains(nextIndex8) || visited7.contains(nextIndex8) || visited8.contains(nextIndex8);
																	if (!alreadyVisited8)
																	{
																		LinkedList<Vertex> list9 = adj.get(nextIndex8);
																		ListIterator<Vertex> iter9 = list9.listIterator();
																		while (iter9.hasNext())
																		{
																			Vertex nextVertex9 = iter9.next();
																			int nextIndex9 = nextVertex9.getIndex();
																			boolean alreadyVisited9 = visited1.contains(nextIndex9) || visited2.contains(nextIndex9) || visited3.contains(nextIndex9) || visited4.contains(nextIndex9) || visited5.contains(nextIndex9) || visited6.contains(nextIndex9) || visited7.contains(nextIndex9) || visited8.contains(nextIndex9) || visited9.contains(nextIndex9);
																			if (!alreadyVisited9)
																			{
																				visited10.add(nextIndex9);
																				if (nextIndex9 == target)
																					return object1.toString() + " --> " + nextVertex1.getObject().toString() + " --> " + nextVertex2.getObject().toString() + " --> " + nextVertex3.getObject().toString() + " --> " + nextVertex4.getObject().toString() + " --> " + nextVertex5.getObject().toString() + " --> " + nextVertex6.getObject().toString() + " --> " + nextVertex7.getObject().toString() + " --> " + nextVertex8.getObject().toString() + " --> " + nextVertex9.getObject().toString();
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		
		
		iter1 = list1.listIterator();
		while (iter1.hasNext())
		{
			Vertex nextVertex1 = iter1.next();
			int nextIndex1 = nextVertex1.getIndex();
			boolean alreadyVisited1 = visited1.contains(nextIndex1);
			if (!alreadyVisited1)
			{
				LinkedList<Vertex> list2 = adj.get(nextIndex1);
				ListIterator<Vertex> iter2 = list2.listIterator();
				while (iter2.hasNext())
				{
					Vertex nextVertex2 = iter2.next();
					int nextIndex2 = nextVertex2.getIndex();
					boolean alreadyVisited2 = visited1.contains(nextIndex2) || visited2.contains(nextIndex2);
					if (!alreadyVisited2)
					{
						LinkedList<Vertex> list3 = adj.get(nextIndex2);
						ListIterator<Vertex> iter3 = list3.listIterator();
						while (iter3.hasNext())
						{
							Vertex nextVertex3 = iter3.next();
							int nextIndex3 = nextVertex3.getIndex();
							boolean alreadyVisited3 = visited1.contains(nextIndex3) || visited2.contains(nextIndex3) || visited3.contains(nextIndex3);
							if (!alreadyVisited3)
							{
								LinkedList<Vertex> list4 = adj.get(nextIndex3);
								ListIterator<Vertex> iter4 = list4.listIterator();
								while (iter4.hasNext())
								{
									Vertex nextVertex4 = iter4.next();
									int nextIndex4 = nextVertex4.getIndex();
									boolean alreadyVisited4 = visited1.contains(nextIndex4) || visited2.contains(nextIndex4) || visited3.contains(nextIndex4) || visited4.contains(nextIndex4);
									if (!alreadyVisited4)
									{
										LinkedList<Vertex> list5 = adj.get(nextIndex4);
										ListIterator<Vertex> iter5 = list5.listIterator();
										while (iter5.hasNext())
										{
											Vertex nextVertex5 = iter5.next();
											int nextIndex5 = nextVertex5.getIndex();
											boolean alreadyVisited5 = visited1.contains(nextIndex5) || visited2.contains(nextIndex5) || visited3.contains(nextIndex5) || visited4.contains(nextIndex5) || visited5.contains(nextIndex5);
											if (!alreadyVisited5)
											{
												LinkedList<Vertex> list6 = adj.get(nextIndex5);
												ListIterator<Vertex> iter6 = list6.listIterator();
												while (iter6.hasNext())
												{
													Vertex nextVertex6 = iter6.next();
													int nextIndex6 = nextVertex6.getIndex();
													boolean alreadyVisited6 = visited1.contains(nextIndex6) || visited2.contains(nextIndex6) || visited3.contains(nextIndex6) || visited4.contains(nextIndex6) || visited5.contains(nextIndex6) || visited6.contains(nextIndex6);
													if (!alreadyVisited6)
													{
														LinkedList<Vertex> list7 = adj.get(nextIndex6);
														ListIterator<Vertex> iter7 = list7.listIterator();
														while (iter7.hasNext())
														{
															Vertex nextVertex7 = iter7.next();
															int nextIndex7 = nextVertex7.getIndex();
															boolean alreadyVisited7 = visited1.contains(nextIndex7) || visited2.contains(nextIndex7) || visited3.contains(nextIndex7) || visited4.contains(nextIndex7) || visited5.contains(nextIndex7) || visited6.contains(nextIndex7) || visited7.contains(nextIndex7);
															if (!alreadyVisited7)
															{
																LinkedList<Vertex> list8 = adj.get(nextIndex7);
																ListIterator<Vertex> iter8 = list8.listIterator();
																while (iter8.hasNext())
																{
																	Vertex nextVertex8 = iter8.next();
																	int nextIndex8 = nextVertex8.getIndex();
																	boolean alreadyVisited8 = visited1.contains(nextIndex8) || visited2.contains(nextIndex8) || visited3.contains(nextIndex8) || visited4.contains(nextIndex8) || visited5.contains(nextIndex8) || visited6.contains(nextIndex8) || visited7.contains(nextIndex8) || visited8.contains(nextIndex8);
																	if (!alreadyVisited8)
																	{
																		LinkedList<Vertex> list9 = adj.get(nextIndex8);
																		ListIterator<Vertex> iter9 = list9.listIterator();
																		while (iter9.hasNext())
																		{
																			Vertex nextVertex9 = iter9.next();
																			int nextIndex9 = nextVertex9.getIndex();
																			boolean alreadyVisited9 = visited1.contains(nextIndex9) || visited2.contains(nextIndex9) || visited3.contains(nextIndex9) || visited4.contains(nextIndex9) || visited5.contains(nextIndex9) || visited6.contains(nextIndex9) || visited7.contains(nextIndex9) || visited8.contains(nextIndex9) || visited9.contains(nextIndex9);
																			if (!alreadyVisited9)
																			{
																				LinkedList<Vertex> list10 = adj.get(nextIndex9);
																				ListIterator<Vertex> iter10 = list10.listIterator();
																				while (iter10.hasNext())
																				{
																					Vertex nextVertex10 = iter10.next();
																					int nextIndex10 = nextVertex10.getIndex();
																					boolean alreadyVisited10 = visited1.contains(nextIndex10) || visited2.contains(nextIndex10) || visited3.contains(nextIndex10) || visited4.contains(nextIndex10) || visited5.contains(nextIndex10) || visited6.contains(nextIndex10) || visited7.contains(nextIndex10) || visited8.contains(nextIndex10) || visited9.contains(nextIndex10) || visited10.contains(nextIndex10);
																					if (!alreadyVisited10)
																					{
																						visited11.add(nextIndex10);
																						if (nextIndex10 == target)
																							return object1.toString() + " --> " + nextVertex1.getObject().toString() + " --> " + nextVertex2.getObject().toString() + " --> " + nextVertex3.getObject().toString() + " --> " + nextVertex4.getObject().toString() + " --> " + nextVertex5.getObject().toString() + " --> " + nextVertex6.getObject().toString() + " --> " + nextVertex7.getObject().toString() + " --> " + nextVertex8.getObject().toString() + " --> " + nextVertex9.getObject().toString() + " --> " + nextVertex10.getObject().toString();
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		
		
		iter1 = list1.listIterator();
		while (iter1.hasNext())
		{
			Vertex nextVertex1 = iter1.next();
			int nextIndex1 = nextVertex1.getIndex();
			boolean alreadyVisited1 = visited1.contains(nextIndex1);
			if (!alreadyVisited1)
			{
				LinkedList<Vertex> list2 = adj.get(nextIndex1);
				ListIterator<Vertex> iter2 = list2.listIterator();
				while (iter2.hasNext())
				{
					Vertex nextVertex2 = iter2.next();
					int nextIndex2 = nextVertex2.getIndex();
					boolean alreadyVisited2 = visited1.contains(nextIndex2) || visited2.contains(nextIndex2);
					if (!alreadyVisited2)
					{
						LinkedList<Vertex> list3 = adj.get(nextIndex2);
						ListIterator<Vertex> iter3 = list3.listIterator();
						while (iter3.hasNext())
						{
							Vertex nextVertex3 = iter3.next();
							int nextIndex3 = nextVertex3.getIndex();
							boolean alreadyVisited3 = visited1.contains(nextIndex3) || visited2.contains(nextIndex3) || visited3.contains(nextIndex3);
							if (!alreadyVisited3)
							{
								LinkedList<Vertex> list4 = adj.get(nextIndex3);
								ListIterator<Vertex> iter4 = list4.listIterator();
								while (iter4.hasNext())
								{
									Vertex nextVertex4 = iter4.next();
									int nextIndex4 = nextVertex4.getIndex();
									boolean alreadyVisited4 = visited1.contains(nextIndex4) || visited2.contains(nextIndex4) || visited3.contains(nextIndex4) || visited4.contains(nextIndex4);
									if (!alreadyVisited4)
									{
										LinkedList<Vertex> list5 = adj.get(nextIndex4);
										ListIterator<Vertex> iter5 = list5.listIterator();
										while (iter5.hasNext())
										{
											Vertex nextVertex5 = iter5.next();
											int nextIndex5 = nextVertex5.getIndex();
											boolean alreadyVisited5 = visited1.contains(nextIndex5) || visited2.contains(nextIndex5) || visited3.contains(nextIndex5) || visited4.contains(nextIndex5) || visited5.contains(nextIndex5);
											if (!alreadyVisited5)
											{
												LinkedList<Vertex> list6 = adj.get(nextIndex5);
												ListIterator<Vertex> iter6 = list6.listIterator();
												while (iter6.hasNext())
												{
													Vertex nextVertex6 = iter6.next();
													int nextIndex6 = nextVertex6.getIndex();
													boolean alreadyVisited6 = visited1.contains(nextIndex6) || visited2.contains(nextIndex6) || visited3.contains(nextIndex6) || visited4.contains(nextIndex6) || visited5.contains(nextIndex6) || visited6.contains(nextIndex6);
													if (!alreadyVisited6)
													{
														LinkedList<Vertex> list7 = adj.get(nextIndex6);
														ListIterator<Vertex> iter7 = list7.listIterator();
														while (iter7.hasNext())
														{
															Vertex nextVertex7 = iter7.next();
															int nextIndex7 = nextVertex7.getIndex();
															boolean alreadyVisited7 = visited1.contains(nextIndex7) || visited2.contains(nextIndex7) || visited3.contains(nextIndex7) || visited4.contains(nextIndex7) || visited5.contains(nextIndex7) || visited6.contains(nextIndex7) || visited7.contains(nextIndex7);
															if (!alreadyVisited7)
															{
																LinkedList<Vertex> list8 = adj.get(nextIndex7);
																ListIterator<Vertex> iter8 = list8.listIterator();
																while (iter8.hasNext())
																{
																	Vertex nextVertex8 = iter8.next();
																	int nextIndex8 = nextVertex8.getIndex();
																	boolean alreadyVisited8 = visited1.contains(nextIndex8) || visited2.contains(nextIndex8) || visited3.contains(nextIndex8) || visited4.contains(nextIndex8) || visited5.contains(nextIndex8) || visited6.contains(nextIndex8) || visited7.contains(nextIndex8) || visited8.contains(nextIndex8);
																	if (!alreadyVisited8)
																	{
																		LinkedList<Vertex> list9 = adj.get(nextIndex8);
																		ListIterator<Vertex> iter9 = list9.listIterator();
																		while (iter9.hasNext())
																		{
																			Vertex nextVertex9 = iter9.next();
																			int nextIndex9 = nextVertex9.getIndex();
																			boolean alreadyVisited9 = visited1.contains(nextIndex9) || visited2.contains(nextIndex9) || visited3.contains(nextIndex9) || visited4.contains(nextIndex9) || visited5.contains(nextIndex9) || visited6.contains(nextIndex9) || visited7.contains(nextIndex9) || visited8.contains(nextIndex9) || visited9.contains(nextIndex9);
																			if (!alreadyVisited9)
																			{
																				LinkedList<Vertex> list10 = adj.get(nextIndex9);
																				ListIterator<Vertex> iter10 = list10.listIterator();
																				while (iter10.hasNext())
																				{
																					Vertex nextVertex10 = iter10.next();
																					int nextIndex10 = nextVertex10.getIndex();
																					boolean alreadyVisited10 = visited1.contains(nextIndex10) || visited2.contains(nextIndex10) || visited3.contains(nextIndex10) || visited4.contains(nextIndex10) || visited5.contains(nextIndex10) || visited6.contains(nextIndex10) || visited7.contains(nextIndex10) || visited8.contains(nextIndex10) || visited9.contains(nextIndex10) || visited10.contains(nextIndex10);
																					if (!alreadyVisited10)
																					{
																						LinkedList<Vertex> list11 = adj.get(nextIndex10);
																						ListIterator<Vertex> iter11 = list11.listIterator();
																						while (iter11.hasNext())
																						{
																							Vertex nextVertex11 = iter11.next();
																							int nextIndex11 = nextVertex11.getIndex();
																							boolean alreadyVisited11 = visited1.contains(nextIndex11) || visited2.contains(nextIndex11) || visited3.contains(nextIndex11) || visited4.contains(nextIndex11) || visited5.contains(nextIndex11) || visited6.contains(nextIndex11) || visited7.contains(nextIndex11) || visited8.contains(nextIndex11) || visited9.contains(nextIndex11) || visited10.contains(nextIndex11) || visited11.contains(nextIndex11);
																							if (!alreadyVisited11)
																							{
																								visited12.add(nextIndex11);
																								if (nextIndex11 == target)
																									return object1.toString() + " --> " + nextVertex1.getObject().toString() + " --> " + nextVertex2.getObject().toString() + " --> " + nextVertex3.getObject().toString() + " --> " + nextVertex4.getObject().toString() + " --> " + nextVertex5.getObject().toString() + " --> " + nextVertex6.getObject().toString() + " --> " + nextVertex7.getObject().toString() + " --> " + nextVertex8.getObject().toString() + " --> " + nextVertex9.getObject().toString() + " --> " + nextVertex10.getObject().toString() + " --> " + nextVertex11.getObject().toString();
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		
		
		iter1 = list1.listIterator();
		while (iter1.hasNext())
		{
			Vertex nextVertex1 = iter1.next();
			int nextIndex1 = nextVertex1.getIndex();
			boolean alreadyVisited1 = visited1.contains(nextIndex1);
			if (!alreadyVisited1)
			{
				LinkedList<Vertex> list2 = adj.get(nextIndex1);
				ListIterator<Vertex> iter2 = list2.listIterator();
				while (iter2.hasNext())
				{
					Vertex nextVertex2 = iter2.next();
					int nextIndex2 = nextVertex2.getIndex();
					boolean alreadyVisited2 = visited1.contains(nextIndex2) || visited2.contains(nextIndex2);
					if (!alreadyVisited2)
					{
						LinkedList<Vertex> list3 = adj.get(nextIndex2);
						ListIterator<Vertex> iter3 = list3.listIterator();
						while (iter3.hasNext())
						{
							Vertex nextVertex3 = iter3.next();
							int nextIndex3 = nextVertex3.getIndex();
							boolean alreadyVisited3 = visited1.contains(nextIndex3) || visited2.contains(nextIndex3) || visited3.contains(nextIndex3);
							if (!alreadyVisited3)
							{
								LinkedList<Vertex> list4 = adj.get(nextIndex3);
								ListIterator<Vertex> iter4 = list4.listIterator();
								while (iter4.hasNext())
								{
									Vertex nextVertex4 = iter4.next();
									int nextIndex4 = nextVertex4.getIndex();
									boolean alreadyVisited4 = visited1.contains(nextIndex4) || visited2.contains(nextIndex4) || visited3.contains(nextIndex4) || visited4.contains(nextIndex4);
									if (!alreadyVisited4)
									{
										LinkedList<Vertex> list5 = adj.get(nextIndex4);
										ListIterator<Vertex> iter5 = list5.listIterator();
										while (iter5.hasNext())
										{
											Vertex nextVertex5 = iter5.next();
											int nextIndex5 = nextVertex5.getIndex();
											boolean alreadyVisited5 = visited1.contains(nextIndex5) || visited2.contains(nextIndex5) || visited3.contains(nextIndex5) || visited4.contains(nextIndex5) || visited5.contains(nextIndex5);
											if (!alreadyVisited5)
											{
												LinkedList<Vertex> list6 = adj.get(nextIndex5);
												ListIterator<Vertex> iter6 = list6.listIterator();
												while (iter6.hasNext())
												{
													Vertex nextVertex6 = iter6.next();
													int nextIndex6 = nextVertex6.getIndex();
													boolean alreadyVisited6 = visited1.contains(nextIndex6) || visited2.contains(nextIndex6) || visited3.contains(nextIndex6) || visited4.contains(nextIndex6) || visited5.contains(nextIndex6) || visited6.contains(nextIndex6);
													if (!alreadyVisited6)
													{
														LinkedList<Vertex> list7 = adj.get(nextIndex6);
														ListIterator<Vertex> iter7 = list7.listIterator();
														while (iter7.hasNext())
														{
															Vertex nextVertex7 = iter7.next();
															int nextIndex7 = nextVertex7.getIndex();
															boolean alreadyVisited7 = visited1.contains(nextIndex7) || visited2.contains(nextIndex7) || visited3.contains(nextIndex7) || visited4.contains(nextIndex7) || visited5.contains(nextIndex7) || visited6.contains(nextIndex7) || visited7.contains(nextIndex7);
															if (!alreadyVisited7)
															{
																LinkedList<Vertex> list8 = adj.get(nextIndex7);
																ListIterator<Vertex> iter8 = list8.listIterator();
																while (iter8.hasNext())
																{
																	Vertex nextVertex8 = iter8.next();
																	int nextIndex8 = nextVertex8.getIndex();
																	boolean alreadyVisited8 = visited1.contains(nextIndex8) || visited2.contains(nextIndex8) || visited3.contains(nextIndex8) || visited4.contains(nextIndex8) || visited5.contains(nextIndex8) || visited6.contains(nextIndex8) || visited7.contains(nextIndex8) || visited8.contains(nextIndex8);
																	if (!alreadyVisited8)
																	{
																		LinkedList<Vertex> list9 = adj.get(nextIndex8);
																		ListIterator<Vertex> iter9 = list9.listIterator();
																		while (iter9.hasNext())
																		{
																			Vertex nextVertex9 = iter9.next();
																			int nextIndex9 = nextVertex9.getIndex();
																			boolean alreadyVisited9 = visited1.contains(nextIndex9) || visited2.contains(nextIndex9) || visited3.contains(nextIndex9) || visited4.contains(nextIndex9) || visited5.contains(nextIndex9) || visited6.contains(nextIndex9) || visited7.contains(nextIndex9) || visited8.contains(nextIndex9) || visited9.contains(nextIndex9);
																			if (!alreadyVisited9)
																			{
																				LinkedList<Vertex> list10 = adj.get(nextIndex9);
																				ListIterator<Vertex> iter10 = list10.listIterator();
																				while (iter10.hasNext())
																				{
																					Vertex nextVertex10 = iter10.next();
																					int nextIndex10 = nextVertex10.getIndex();
																					boolean alreadyVisited10 = visited1.contains(nextIndex10) || visited2.contains(nextIndex10) || visited3.contains(nextIndex10) || visited4.contains(nextIndex10) || visited5.contains(nextIndex10) || visited6.contains(nextIndex10) || visited7.contains(nextIndex10) || visited8.contains(nextIndex10) || visited9.contains(nextIndex10) || visited10.contains(nextIndex10);
																					if (!alreadyVisited10)
																					{
																						LinkedList<Vertex> list11 = adj.get(nextIndex10);
																						ListIterator<Vertex> iter11 = list11.listIterator();
																						while (iter11.hasNext())
																						{
																							Vertex nextVertex11 = iter11.next();
																							int nextIndex11 = nextVertex11.getIndex();
																							boolean alreadyVisited11 = visited1.contains(nextIndex11) || visited2.contains(nextIndex11) || visited3.contains(nextIndex11) || visited4.contains(nextIndex11) || visited5.contains(nextIndex11) || visited6.contains(nextIndex11) || visited7.contains(nextIndex11) || visited8.contains(nextIndex11) || visited9.contains(nextIndex11) || visited10.contains(nextIndex11) || visited11.contains(nextIndex11);
																							if (!alreadyVisited11)
																							{
																								LinkedList<Vertex> list12 = adj.get(nextIndex11);
																								ListIterator<Vertex> iter12 = list12.listIterator();
																								while (iter12.hasNext())
																								{
																									Vertex nextVertex12 = iter12.next();
																									int nextIndex12 = nextVertex12.getIndex();
																									boolean alreadyVisited12 = visited1.contains(nextIndex12) || visited2.contains(nextIndex12) || visited3.contains(nextIndex12) || visited4.contains(nextIndex12) || visited5.contains(nextIndex12) || visited6.contains(nextIndex12) || visited7.contains(nextIndex12) || visited8.contains(nextIndex12) || visited9.contains(nextIndex12) || visited10.contains(nextIndex12) || visited11.contains(nextIndex12) || visited12.contains(nextIndex12);
																									if (!alreadyVisited12)
																									{
																										if (nextIndex12 == target)
																											return object1.toString() + " --> " + nextVertex1.getObject().toString() + " --> " + nextVertex2.getObject().toString() + " --> " + nextVertex3.getObject().toString() + " --> " + nextVertex4.getObject().toString() + " --> " + nextVertex5.getObject().toString() + " --> " + nextVertex6.getObject().toString() + " --> " + nextVertex7.getObject().toString() + " --> " + nextVertex8.getObject().toString() + " --> " + nextVertex9.getObject().toString() + " --> " + nextVertex10.getObject().toString() + " --> " + nextVertex11.getObject().toString() + " --> " + nextVertex12.getObject().toString();
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		
		
		return "No path could be found between " + object1.toString() + " and " + object2.toString() + ".";
	}
	
	private int findIndex(Object target)
	{
		int mid;
        int low = 0;
        int high = vertices.length-1;
        while (low <= high) 
        {
        	mid = (low+high) / 2;
            if (vertices[mid].getObject().equals(target))
            	return vertices[mid].getIndex();
            else if (vertices[mid].getObject().toString().compareTo(target.toString()) < 0)
            	low = mid+1;
            else
            	high = mid-1;
        }
        return -1;
	}
	
	private Object[] mergeSort(Object[] array)
	{
		if (array.length > 1)
		{
			int elementsInA1 = array.length/2;
			int elementsInA2 = elementsInA1;
			if((array.length % 2) == 1)
				elementsInA2++;
			Object arr1[] = new Object[elementsInA1];
			Object arr2[] = new Object[elementsInA2];
			for(int i = 0; i < elementsInA1; i++)
				arr1[i] = array[i];
			for(int i = elementsInA1; i < elementsInA1 + elementsInA2; i++)
				arr2[i - elementsInA1] = array[i];
			arr1 = mergeSort(arr1);
			arr2 = mergeSort(arr2);
			int i = 0, j = 0, k = 0;
			while (arr1.length != j && arr2.length != k)
			{
				if (arr1[j].toString().compareTo(arr2[k].toString()) < 0)
				{
					array[i] = arr1[j];
					i++;
					j++;
				}
				else
				{
					array[i] = arr2[k];
					i++;
					k++;
				}
			}
			while (arr1.length != j)
			{
				array[i] = arr1[j];
				i++;
				j++;
			}
			while (arr2.length != k)
			{
				array[i] = arr2[k];
				i++;
				k++;
			}
		}
		return array;
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
