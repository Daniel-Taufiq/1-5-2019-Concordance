import java.util.*;
import java.io.*;
public class Book
{
    private TokenList allTokens;
    private Word[] allWords;

    public Book(File file) throws IOException
    {
        this(file, null, null);
    }

    public Book(File file, String excerptStartTag, String excerptStopTag) throws IOException
    {
        TokenFilter         allTokens;
        ArrayList<String>   readMethodArrayList;
        String[]            filterArray;
        TokenList           list;
        FileReaderWriter    readFile;
        Word[]              words;

        if(file == null)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    "Book constructor: file cannot be null " + file);
        }

        list = new TokenList();

        readFile = FileReaderWriter.getInstance();
        readMethodArrayList = readFile.read(file);                      // read & store in ArrayList

        filterArray = readMethodArrayList.toArray(new String[0]);       // instantiate size of array

        list.updateUsing(filterArray, new WordFilter());                // get all the tokens from array
        filterArray = list.toArray();

        // update words using already created array and access Word static method
        words = Word.toWordArray(filterArray);

        // update list using default excerpt factory
        allTokens = ExcerptFilter.factory(null, null);  // uses default filter to read all tokens
        list.updateUsing(allTokens);

        this.allTokens = list;
        this.allWords = words;
    }

    private TokenList getAllTokens() { return this.allTokens; }

    public int getNumberOfTokens() { return this.allTokens.size(); }

    public String[] getTokens() { return this.allTokens.toArray(); }

    public String getToken(int i) { return allTokens.get(i); }

    private Word[] getAllWords() { return this.allWords; }

    public int getNumberOfWords() { return this.allWords.length; }

    public Word[] getWords() { return this.allWords; }

    public Word getWord(int i ) { return allWords[i]; }


}
