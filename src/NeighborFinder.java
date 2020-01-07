public class NeighborFinder implements Runnable
{
    /*
        Daniel Taufiq
        5/14/2019
        Dr. Jarvis

        Class Description:
            Finds the index location where the two words are found
            and the distance is valid and creates a LocationPair
            object.

        Instance Variables:
            int distance;
                stores the distance between the serach terms.
            private NavigableOccurenceList leftList;
                stores the search term as a OccurenceList and
                creates a NavigableOccuenceList object to locate
                word.
            private NavigableOccurenceList rightList;
                stores the search term as a OccurenceList and
                creates a NavigableOccuenceList object to locate
                word.

         Constructor:
            public NeighborFinder(OccurenceList leftList, OccurenceList rightList, int distance)
                initializes instance variables and creates a new
                NavigableOccurenceList object.

         Methods:
            private NavigableOccurenceList getLeftList()
                returns instance variable
            private NavigableOccurenceList getRightList()
                returns instance variable
             public int getDistance()
                returns instance variable
             public void run()
                overrides run method in Runnable, uses threads
                to find the location pairs of left and right list
                in the given distance.

     */

    int                             distance;
    private NavigableOccurenceList  leftList;
    private NavigableOccurenceList  rightList;

    public NeighborFinder(OccurenceList leftList, OccurenceList rightList, int distance)
    {
        if(distance < 0)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    " NeighborFinder constructor: distance must be greater than zero " + distance);
        }

        leftList.sortList();
        rightList.sortList();
        this.leftList = new NavigableOccurenceList(new OccurenceList(leftList.getAll()));
        this.rightList = new NavigableOccurenceList(new OccurenceList(rightList.getAll()));
        this.distance = distance;
    }

    private NavigableOccurenceList getLeftList() { return this.leftList; }
    private NavigableOccurenceList getRightList() { return this.rightList; }

    public int getDistance()
    {
        return this.distance;
    }

    @Override
    public void run()
    {
        int             left;
        LocationPair    obj;
        int             right;
        int             x;


        for(int i = 0; i < getLeftList().size(); i++)
        {
            left = getLeftList().next();
            for(int j = 0; j < getRightList().size(); j++)
            {
                right = getRightList().next();
                x = right - left;
                if(x > 0 && x <= getDistance())
                {
                    obj = new LocationPair(left, right);
                    NeighborLocations.getInstance().add(obj);
                }
            }
            getRightList().reset();
        }
    }
}
