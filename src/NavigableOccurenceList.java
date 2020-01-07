import java.util.*;
public class NavigableOccurenceList
{
    /*
        Daniel Taufiq
        1/7/2019

        Class Description:
            uses a position in the index to navigate
            through the array of a location pair

        Instance Variables:
            private int currentPosition
                holds the current position in an index
            private OccurenceList list
                contains the OccurenceList object that
                holds an array of int values.

        Constructor:
            public NavigableOccurenceList(OccurenceList occurenceList)
                instantiate the instance variables

        Methods:
            private OccurenceList getList()
                returns the list instance variable
            private void setList(OccurenceList occurenceList)
                sets the OccurenceList object to a new list
            private int getCurrentPosition()
                returns the instance variable's current position
                in the index
            private void setCurrentPosition(int position)
                sets the instance variable's position
                to a specified index.
            private void incrementCurrentPosition()
                increments to the next index
            private void incrementCurrentPosition()
                decrements to the previous index
            private int get()
                returns the element at the current index
            public void add(int occurence)
                add element to the list
            public int first()
                returns the first element in the list
            public int last()
                returns the last element in the list
            public int previous()
                returns the previous element
            public int next()
                returns the next element
            public void remove()
                removes element at currentPosition
            public boolean hasNext()
                checks if there is an element in front
                of the currentPosition
            public boolean hasPrevious()
                checks if there is an element behind
                the currentPosition
            public void set(int value)
                sets a value at a specified index
            public int size()
                returns the size of the list
            public void reset()
                resets currentPosition to -1

     */
    private int             currentPosition;
    private OccurenceList   list;

    public NavigableOccurenceList(OccurenceList occurenceList)
    {
        if(occurenceList == null)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    " constructor: list cannot be null " + list);
        }
        this.list = occurenceList;
        reset();
    }

    private OccurenceList getList() { return this.list; }
    private void setList(OccurenceList occurenceList)
    {
        if(this.list == null)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    " setList method: list cannot be null " + this.list);
        }
        this.list = occurenceList;
    }

    private int getCurrentPosition()
    {
        return this.currentPosition;
    }

    private void setCurrentPosition(int position)
    {
        if(position < 0 || position > getList().size() - 1)
        {
            throw new RuntimeException(getClass().getName() +
                    " setCurrentPosition method: cannot be less than 0 or " +
                    "greater than the array list size " + position);
        }
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

    public void add(int occurence) { this.list.add(occurence); }

    public int first()
    {
        if(getList().size() < 0)
        {
            throw new NoSuchElementException(getClass().getName() +
                    " first method: element is out of bounds " + get());
        }
        this.currentPosition = 0;
        return get();
    }

    public int last()
    {
        if(getList().size() > getList().size() + 1)
        {
            throw new NoSuchElementException(getClass().getName() +
                    " last method: element is out of bounds " + get());
        }
        this.currentPosition = getList().size() - 1;
        return get();
    }

    public int previous()
    {
        if(!hasPrevious())
        {
            throw new NoSuchElementException(getClass().getName() +
                    " previous method: element is out of bounds " + get());
        }
        decrementCurrentPosition();
        return get();
    }

    public int next()
    {
        if(!hasNext())
        {
            throw new NoSuchElementException(getClass().getName() +
                    " next method: element is out of bounds " + get());
        }
        incrementCurrentPosition();
        return get();
    }

    public void remove()
    {
        int     j;
        int[]   hold;
        int[]   result;

        j = 0;
        hold = getList().getAll();
        result = new int[hold.length - 1];

        if(getCurrentPosition() < 0 || getCurrentPosition() > size() - 1)
        {
            throw new NoSuchElementException(getClass().getName() +
                    " remove method: index is out of bounds " + getCurrentPosition());
        }

        for(int i = 0; i < hold.length; i++)
        {
            if(i == getCurrentPosition()) { j--; }
            else { result[j] = hold[i]; }
            j++;
        }
        setList(new OccurenceList(result));
    }

    public boolean hasNext()
    {
        return getCurrentPosition() + 1 < size();
    }

    public boolean hasPrevious()
    {
        return getCurrentPosition() - 1 >= 0;
    }

    public void set(int value)
    {
        int[] hold;
        int[] result;

        if(getCurrentPosition() > size() - 1 || getCurrentPosition() < 0)
        {
            throw new NoSuchElementException(getClass().getName() +
                    " set method: element is out of bounds " + getCurrentPosition());
        }

        hold = getList().getAll();
        result = new int[size()];

        for(int i = 0; i < size(); i++)
        {
            if(i == getCurrentPosition()) { result[i] = value; }
            else { result[i] = hold[i]; }
        }
        setList(new OccurenceList(result));
    }

    public int size()
    {
        return getList().size();
    }

    public void reset()
    {
        this.currentPosition = -1;  // if next() called, will return first element the list
    }
}