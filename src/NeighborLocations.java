import java.util.*;
public class NeighborLocations
{
    /*
        Daniel Taufiq
        1/7/2019

        Class Description:
            A singleton class that allows other objects to use
            the location of the pairs founded.

        Instance Variable
            private static NeighborLocations instance = null;
                waits to initializr instance variable until first being
                called
            private int distance;
                stores the distance between the two search terms.
            private ArrayList<LocationPair> locations;
                finds the locations in the Word[] where they are in the
                appropriate distance.
            Word searchTermOne;
                Word to be searching for
            Word searchTermTwo;
                Word to be searching for

         Constructor:
            private NeighborLocations()
                initializes the instance variable for locations.

         Methods:
            public static NeighborLocations getInstance()
                only initializes one object
            public static NeighborLocations newInstance()
                calls the getInstance method
            public synchronized void add(LocationPair locationPair)
                adds a new llocation pair to the instance variable
            public int numberOfLocations()
                returns the size of the instance variable
            public LocationPair[] toArray()
                returns an object array of the location pairs
            public void clear()
                clears all elements in the instance variable
            public int getDistance()
                returns the instance variable distance
            public Word getSearchTermOne()
                returns the first search term
            public Word getSearchTermTwo()
                returns the second search term
            public void setDistance(int distance)
                sets the distance for the between words of the
                two search terms.
            public void setSearchTermOne(Word searchTerm)
                sets the first search term
            public void setSearchTermOne(Word searchTerm)
                sets the second search term
            private ArrayList<LocationPair> getLocations()
                returns the locations in the instance variable
            public LocationPair getPair(int i)
                returns only one location pair object at given index


     */

    private int                         distance;
    private static NeighborLocations    instance = null;
    private ArrayList<LocationPair>     locations;
    Word                                searchTermOne;
    Word                                searchTermTwo;

    private NeighborLocations()
    {
        locations = new ArrayList<LocationPair>();
    }

    public static NeighborLocations getInstance()
    {
        if(NeighborLocations.instance == null)
        {
            NeighborLocations.instance = new NeighborLocations();
        }
        return NeighborLocations.instance;
    }

    public static NeighborLocations newInstance()
    {
        return getInstance();
    }

    public synchronized void add(LocationPair locationPair)
    {
        this.locations.add(locationPair);
    }

    public int numberOfLocations() { return this.locations.size(); }

    public LocationPair[] toArray()
    {
        return getLocations().toArray(new LocationPair[0]);
    }

    public void clear()
    {
        this.locations.clear();
    }

    public int getDistance() { return this.distance; }

    public Word getSearchTermOne() { return this.searchTermOne; }
    public Word getSearchTermTwo() { return this.searchTermTwo; }

    public void setDistance(int distance)
    {
        if(distance < 0)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    " setDistance method: distance cannot be less than zero" + distance);
        }
        this.distance = distance;
    }
    public void setSearchTermOne(Word searchTerm)
    {
        if(searchTerm == null)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    " setSearchTermOne method: Word object cannot be null " + searchTerm);
        }
        this.searchTermOne = searchTerm;
    }
    public void setSearchTermTwo(Word searchTerm)
    {
        if(searchTerm == null)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    " setSearchTermTwo method: Word object cannot be null " + searchTerm);
        }
        this.searchTermTwo = searchTerm;
    }

    private ArrayList<LocationPair> getLocations() { return this.locations; }

    public LocationPair getPair(int i)
    {
        if(i < 0)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    " getPair method: cannot be less than 0 " + i);
        }
        return locations.get(i);
    }
}
