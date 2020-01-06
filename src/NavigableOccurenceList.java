import java.util.*;
public class NavigableOccurenceList
{
    private int currentPosition;
    private OccurenceList list;

    public NavigableOccurenceList(OccurenceList occurenceList)
    {
        if(list == null)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    "constructor: list cannot be null " + list);
        }

        this.list = occurenceList;
        this.currentPosition = 0;
    }

    private OccurenceList getList() { return this.list; }
    private void setList(OccurenceList occurenceList)
    {
        if(list == null)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    "setList method: list cannot be null " + list);
        }

        this.list = occurenceList;
    }

    private int getCurrentPosition()
    {
        return this.currentPosition;
    }

    private void setCurrentPosition(int position)
    {
        this.currentPosition = position;
    }

    private void incrementCurrentPosition()
    {
        setCurrentPosition(getCurrentPosition() + 1);
    }

    private void decrementCurrentPosition()
    {
        setCurrentPosition(getCurrentPosition() - 1);
    }

    private int get()
    {
        return getList().get(getCurrentPosition());
    }

    public void add(int occurence)
    {
        this.list.add(occurence);
    }

    public int first()
    {
        //if(getList().size() < 1)
        //{
        //	throw new NoSuchElementException(getClass().getName());
        //}
        this.currentPosition = 0;
        return get();
    }

    public int last()
    {
        //if(getList().size()1)
        //{
        //	throw new NoSuchElementException(getClass().getName());
        //}
        this.currentPosition = getList().size();
        return get();
    }

    public int previous()
    {
        //if(!hasPrevious)
        //{
        //	throw new NoSuchElementException
        //}
        decrementCurrentPosition();
        return get();
    }

    public int next()
    {
        //if(!hasNext())
        //{
        //	throw new NoSuchElementException
        //}
        incrementCurrentPosition();
        return get();
    }
    public void main()
    {
        NavigableOccurenceList list;
        list = new NavigableOccurenceList(new OccurenceList(new int[] {1, 2, 3, 4}));
        list.add(1);
        print(list.getList().getAll());
    }

    public void print(int[] intArr)
    {
        for(int i = 0; i < intArr.length; i++)
        {
            System.out.println(intArr[i]);
        }
    }

    public void remove()
    {
        ArrayList<Integer> change;
        int[] result;
        int[] value;
        value = getList().getAll();
        result = new int[value.length - 1];

        //if(getCurrentPosition() < 0 || getCurrentPosition() > getList.size())
        //{
        //	throw new NoSuchElementException
        //}

        //ArrayList<Integer> result;
        //result = new ArrayList<Integer>();
        change = new ArrayList<Integer>
        for(int i = 0; i < size(); i++)
        {
            result[i] = value[i];
            if(i == getCurrentPosition())
            {
                i = i + 1;
            }
        }
        change = remove(getCurrentPosition());
        result =
                setList(new OccurenceList(result));
    }

    public boolean hasNext()
    {
        return getCurrentPosition() + 1 < size();
    }

    public boolean hasPrevious()
    {
        // return true if currenPosition - 1 is greater than or equal to 0
        return getCurrentPosition() - 1 >= 0;
    }

    public void set(int value)
    {
        if(value < getList().size())
        {
            throw new IllegalArgumentException(getClass().getName() +
                    "set method: value cannot be less than list size " + value);
        }
        int[] result;
        result = getList().getAll();
        setList(getCurrentPosition() = value);
        setList(new OccurenceList(result));

    }

    public int size()
    {
        return getList.size();
    }


}