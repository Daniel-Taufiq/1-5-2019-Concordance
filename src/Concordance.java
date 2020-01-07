import java.util.*;
public class Concordance
{
    /*
        Daniel Taufiq
        1/7/2019

        Instance Variables:
            private Book book
                holds a book object containing words and tokens
            private HashMap<Integer, Integer> locationMap
                a HashMap that searches through the index of
                Book.Word[] and maps where it finds it in
                Book.TokenList.
            private HashMap<Word, OccurenceList> occurenceMap
                maps the occurences of each Word object in the
                Word[] and is incaseSensitive

        Constructor:
            public Concordance(Book book)
                instantiates the instance variables

        Methods
            private void populateOccurenceMap()
                maps the occurences of each Word object in the
                Word[] and is incaseSensitive
            private void populateLocationMap()
                a HashMap that searches through the index of
                Book.Word[] and maps where it finds it in
                Book.TokenList.
            private Book getBook()
                returns the book instance variable
            private HashMap<Word, OccurenceList> getOccurenceMap()
                returns the occurenceMap instance variable
            public HashMap<Integer, Integer> getLocationMap()
                returns the locationMap instance variable
            public void getContext(Word wordA, int distance, Word wordB)
                populates the HashMaps as well as the finding
                the neighbor locations for the two search terms.
            public void display(int numberOfPrefixWords, int numberOfSuffixWords)
                displays the results to the browser and also
                allows user to choose suffix and prefix words
                after and before the search terms.
     */
    private Book                            book;
    private HashMap<Integer, Integer>       locationMap;
    private HashMap<Word, OccurenceList>    occurenceMap;

    public Concordance(Book book)
    {
        if(book == null)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    " Concordance constructor: book cannot be null " + book);
        }

        this.book = book;
        this.locationMap = new HashMap<Integer, Integer>();
        this.occurenceMap = new HashMap<Word, OccurenceList>();
    }

    private void populateOccurenceMap()
    {
        OccurenceList   list;
        Word[]          words;
        Word            word;

        for(int i = 0; i < getBook().getNumberOfWords(); i++)
        {
            word = getBook().getWord(i);
            if(getOccurenceMap().containsKey(getBook().getWord(i)))
            {
                list = this.occurenceMap.get(word);
                list.add(i);
            }
            else
            {
                OccurenceList listNew;
                listNew = new OccurenceList();
                this.occurenceMap.put(word, listNew);
                listNew.add(i);
            }
        }
    }

    private void populateLocationMap()
    {
        int j;
        j = 0;
        for (int i = 0; i < getBook().getNumberOfWords(); i++)
        {
            while (!getBook().getWord(i).get().equals(getBook().getToken(j))) { j++; }
            getLocationMap().put(i, j);
        }
    }

    private Book getBook() { return this.book; }

    private HashMap<Word, OccurenceList> getOccurenceMap() { return this.occurenceMap; }

    public HashMap<Integer, Integer> getLocationMap() { return this.locationMap; }

    public void getContext(Word wordA, int distance, Word wordB) throws InterruptedException
    {
        NeighborFinder      neihbrFndr;
        NeighborFinder      neighbrFndr2;
        NeighborLocations   neibrLoc;
        OccurenceList       wordOne;
        OccurenceList       wordTwo;

        if(wordA == null)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    " getContext method: Word object cannot be null " + wordA);
        }
        if(wordB == null)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    " getContext method: Word object cannot be null " + wordB);
        }
        if(distance < 1)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    " getContext method: distance must be greater than zero " + distance);
        }

        wordA = new Word(wordA.get());
        wordB = new Word(wordB.get());

        this.populateLocationMap();
        this.populateOccurenceMap();

        neibrLoc = NeighborLocations.getInstance();
        neibrLoc.setSearchTermOne(wordA);
        neibrLoc.setSearchTermTwo(wordB);
        neibrLoc.setDistance(distance);

        wordOne = getOccurenceMap().get(wordA);
        wordTwo = getOccurenceMap().get(wordB);

        if(wordA != null && wordB != null)
        {
            neihbrFndr = new NeighborFinder(wordOne, wordTwo, distance);
            neighbrFndr2 = new NeighborFinder(wordTwo, wordOne, distance);

            Thread threadOne;
            Thread threadTwo;

            threadOne = new Thread(neihbrFndr);
            threadTwo = new Thread(neighbrFndr2);

            threadOne.start();
            Thread.sleep(500);
            threadTwo.start();

            try
            {
                threadOne.join();
                threadTwo.join();
            }
            catch(InterruptedException ie)
            {
                throw new RuntimeException(ie.getMessage());
            }
        }
    }

    public void display(int numberOfPrefixWords, int numberOfSuffixWords)
    {
        Integer distance;
        String  dis;
        int     left;
        int     right;

        if(numberOfPrefixWords <= 0)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    " display method: number of prefix words must be at least zero words " + numberOfPrefixWords);
        }
        if(numberOfSuffixWords <= 0)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    " display method: number of suffix words must be at least zero words " + numberOfSuffixWords);
        }

        LocationPair[] pair;
        pair = NeighborLocations.getInstance().toArray();
        int numofLocations = NeighborLocations.getInstance().numberOfLocations();


        HTML htmlObj;
        String wordA = NeighborLocations.getInstance().getSearchTermOne().get();
        String wordB = NeighborLocations.getInstance().getSearchTermTwo().get();
        htmlObj = new HTML();
        String prologue;
        String epilogue;


        distance = NeighborLocations.getInstance().getDistance();
        dis = distance.toString();
        htmlObj.addInsideDiv("Search results for " + wordA + " within " + dis + " words of " + wordB);
        if(numofLocations == 0)
        {
            htmlObj.addInsideDiv("Those words do not appear within that distance of each other." );
        }
        else
        {
            for(int i = 0; i < numofLocations; i++)
            {
                int     check;
                int     left2;
                int     right2;
                String  textBetweenWords;
                String  value;

                prologue = "";
                epilogue = "";
                textBetweenWords = "";
                left = pair[i].getLeft();
                check = getLocationMap().get(left);
                left = getLocationMap().get(left) + 1;
                right = pair[i].getRight();
                right = getLocationMap().get(right) - 1;

                value = getBook().getToken(check);
                if(!wordA.equals(value))
                {
                    String temp;
                    temp = wordA;
                    wordA = wordB;
                    wordB = temp;
                }

                while(left <= right)
                {
                    textBetweenWords = textBetweenWords + getBook().getToken(left);
                    left++;
                }

                left = pair[i].getLeft() - numberOfPrefixWords;
                left = getLocationMap().get(left);
                left2 = pair[i].getLeft() - 1;
                left2 = getLocationMap().get(left2);

                while (left <= left2)
                {
                    prologue = prologue + getBook().getToken(left);
                    left++;
                }

                right = pair[i].getRight();
                right = getLocationMap().get(right) + 1;
                right2 = pair[i].getRight() + numberOfSuffixWords;
                right2 = getLocationMap().get(right2);

                while(right <= right2)
                {
                    epilogue = epilogue + getBook().getToken(right);
                    right++;
                }

                System.out.println();
                htmlObj.addInsideDiv(prologue, wordA, textBetweenWords, wordB, epilogue);
            }
        }
        htmlObj.launchBrowser();
    }
}
