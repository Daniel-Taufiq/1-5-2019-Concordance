public class LocationPair
{
    /*
		Daniel Taufiq
		5/12/2019
		Dr. Jarvis

		Class Description:
			Locates the two indexes of left and right where the distance is valid

		Instance Variables:
			private int left
				holds the right value
    		private int right
    			holds the left value

    	Constructor:
    		public LocationPair(int left, int right)
    			initializes the two location pairs.

    	Methods:
    		public int getLeft()
    			returns the instance variable
    		public int getRight()
    			returns the instance variable
	*/

    private int left;
    private int right;

    public LocationPair(int left, int right)
    {
        if(left < 0)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    " LocationPair constructor: left must be greater than or equal to zero" + left);
        }
        if(right < 0)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    " LocationPair constructor: right must be greater than or equal to zero" + right);
        }
        if(left > right)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    " LocationPair constructor: left must be less than right " + left + " " + right);
        }
        this.left = left;
        this.right = right;
    }

    public int getLeft() { return this.left; }
    public int getRight() { return this.right; }
}
