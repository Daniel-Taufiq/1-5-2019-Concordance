import java.io.*;
public class Book
{
    /*
            Daniel Taufiq
            1/5/2020

            Class Description:
                Holds all the context from a book or any document

            Instance Variables:
                TokenList allTokens
                    holds all tokens from the text file
                Word[] allWords
                    holds an array of Word ojbects, each containing
                    only words from the file

             Constructor:
                Book(File file)
                    calls the overloaded constructor
                public Book(File file, String excerptStartTag, String excerptStopTag)
                    will initialize the instance variables using ExcerptFilter
                    and WordFilter classes

              Methods
                 private TokenList getAllTokens()
                    returns the instance variable
                 public int getNumberOfTokens()
                    returns the size of the instance variable
                 public String[] getTokens()
                    returns a shallow clone version of instance variable
                 public String getToken(int i)
                    returns a String of allTokens at the index
                 private Word[] getAllWords()
                    returns thr instance variable
                 public int getNumberOfWords()
                    returns a shallow clone of the instance variable
                 public Word getWord(int i )
                    returns thee Word object at the index provided

         */

    TokenList   allTokens;
    Word[]      allWords;

    public Book(File file) throws IOException
    {
        this(file, null, null);
    }

    public Book(File file, String excerptStartTag, String excerptStopTag) throws IOException
    {
        Word[]              allWords;
        TokenFilter         excerptFilter;
        TokenList           list;
        TokenList           list2;
        String[]            strArray;


        // update allTokens
        excerptFilter = ExcerptFilter.factory(excerptStartTag, excerptStopTag);
        list = new TokenList();
        list.updateUsing(file, excerptFilter);

        // update allWords
        list2 = new TokenList();
        list2 = list.clone();
        strArray = list2.toArray();
        list2.updateUsing(strArray, new WordFilter());
        strArray = list2.toArray();
        allWords = Word.toWordArray(strArray);

        this.allTokens = list;
        this.allWords = allWords;
    }

    private TokenList getAllTokens() { return this.allTokens; }

    public int getNumberOfTokens() { return this.allTokens.size(); }

    public String[] getTokens()
    {
        String[] tokens;
        tokens = getAllTokens().toArray().clone();
        return tokens;
    }

    public String getToken(int i) { return allTokens.get(i); }

    private Word[] getAllWords() { return this.allWords; }

    public int getNumberOfWords() { return this.allWords.length; }

    public Word[] getWords()
    {
        Word[] word;
        word = getAllWords().clone();
        return word;
    }

    public Word getWord(int i ) { return allWords[i]; }
}