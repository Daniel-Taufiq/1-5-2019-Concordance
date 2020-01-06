import java.util.*;
public class OccurenceList implements Comparator<Integer>
{
	private ArrayList<Integer> list;

	public OccurenceList()
	{
		this.list = new ArrayList<Integer>();
	}

	public OccurenceList(int[] occurences)
	{
		//list = new ArrayList<Integer>();
		this();
		for(int i = 0; i < list.size(); i++)
		{
			this.list.add(occurences[i]);
		}
	}

	private ArrayList<Integer> getList() { return this.list; }

	public void add(int occurence)
	{
		this.list.add(occurence);
	}

	public int get(int i)
	{
		if(i < 0 || i >= size())
		{
			throw new RuntimeException(getClass().getName() + " invalid spot " + i);
		}
		return getList().get(i);
	}

	public int[] getAll()
	{
		int[] result;

		result = new int[getList().size()];
		for(int i = 0; i < getList().size(); i++)
		{
			result[i] = get(i);
		}
		return result;
	}

	public int size() { return this.list.size(); }

	@Override
	public int compare(Integer a, Integer b)
	{
		int result;
		if(a > b) { result = 1; }
		if(a == b) { result = 0; }
		if(a < b) { result = -1; }
	}

	public void sortList()
	{
		this.sortList(new OccurenceList());
	}

	public void sortList(Comparator<Integer> comparator)
	{
		Arrays.sort(comparator());
	}
}
