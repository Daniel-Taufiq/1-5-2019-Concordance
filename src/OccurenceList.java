import java.util.*;
public class OccurenceList implements Comparator<Integer>
{
    /*
        Daniel Taufiq
        4/14/2019
        Dr. Jarvis

        Class Description:
            will be used by other classes to add the occurences of
            words to an ArrayList

        Instance Variable:
            private ArrayList<Integer> list
                stores the occurences in ArrayList

        Constructor:
            public OccurenceList()
                initializes the instance variable
            public OccurenceList(int[] occurences)
                calls constructor andadds each occurence to the
                instance variable.

        Methods
            private ArrayList<integer> getList()
                returns the instance variable
            public void add(int occurence)
                updates the instance variable by adding an occurence
                to the ArrayList
            public int get(int i)
                returns an occurence at a specified index
            public int[] getAll()
                returns an array of all the occurences
            public int size()
                returns the size of the instance variables
            public int compare(Integer a, Integer b)
                compares Integer objects in order to sort them
            public void sortList()
                calls the overloaded method passing a new OccurenceList object
            public void sortList(Comparator<Integer> comparator)
                calls the compare method to compare the instance variable
                list and store in a new OccurenceList object
     */

	private ArrayList<Integer> list;

	public OccurenceList() { this.list = new ArrayList<Integer>(); }

	public OccurenceList(int[] occurences)
	{
		this();
		for(int i = 0; i < occurences.length; i++)
		{
			this.list.add(occurences[i]);
		}
	}

	private ArrayList<Integer> getList() { return this.list; }

	public void add(int occurence) { this.list.add(occurence); }

	public int get(int i)
	{
		if(i < 0 || i >= size())
		{
			throw new RuntimeException(getClass().getName() + " invalid index " + i);
		}
		return getList().get(i);
	}

	public int[] getAll()
	{
		int[] result;

		result = new int[getList().size()];

		for(int i = 0; i < getList().size(); i++) { result[i] = list.get(i); }

		return result;
	}

	public int size() { return this.list.size(); }

	public int compare(Integer a, Integer b)
	{
		int result;
		result = 0;

		if(a > b) { result = a - b; }
		else if(a == b) { result = 0; }
		else if(a < b) { result = -1; }

		return result;
	}

	public void sortList() { this.sortList(new OccurenceList()); }

	public void sortList(Comparator<Integer> comparator)
	{
		if(comparator == null)
		{
			throw new IllegalArgumentException(getClass().getName() +
					"sortList method: comparator cannot be null " + comparator);
		}
		Collections.sort(this.list, new OccurenceList());
	}
}