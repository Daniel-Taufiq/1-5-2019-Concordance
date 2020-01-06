import java.util.*;
public class Concordance
{
    private Book book;
    private HashMap<Word, OccurenceList> occurenceMap;
    private HashMap<Integer, Integer> locationMap;

    public Concordance(Book book)
    {
        if(book == null)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    "Concordance constructor: book cannot be null " + book);
        }
        this.book = book;
        this.occurenceMap = new HashMap<Word, OccurenceList>();
        this.locationMap = new HashMap<Integer, Integer>();
    }

    public void main()
    {
        Book b;
        b = "path file"
        Concordance c;
        c = new Concordance(b);
        c.populateOccurenceMap();
        System.out.println(getOccurenceMap());
    }

    private void populateOccurenceMap()
    {
        Word[] words;
        words = book.getBook();
        for(int i =0; i < words.length; i++)
        {
            if(getOccurenceMap().contains(word[i]))
            {
                occurenceMap.get(i)
            }
        }
    }

    private void getLocationMap()
    {

    }

    private Book getBook()
    {
        return this.book;
    }

    private HashMap<Word, OccurenceList> getOccurenceMap()
    {
        return this.occurenceMap;
    }

    private HashMap<Integer, Integer> getLocationMap()
    {
        return this.locationMap;
    }

}