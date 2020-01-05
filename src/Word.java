public class Word
{
    private String word;

    // constructor
    public Word(String word)
    {
        if(word == null)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    "Word Constructor: word cannot be null " + word);
        }
        this.word = word;
    }

    public static Word[] toWordArray(String[] array)
    {
        Word[] wordArr = new Word[array.length];

        for(int i = 0; i < array.length; i++)
        {
            wordArr[i] = new Word(array[i]);
        }
        return wordArr;
    }

    // returns word instance
    public String get() { return this.word; }

    // compare to word objects
    public int compareTo(Word other)
    {
        int result;
        return result = get().compareToIgnoreCase(other.get());
    }

    @Override
    public boolean equals(Object other)
    {
        Word word;
        boolean result;
        result = false;
        word = (Word) other;

        try
        {
            if(get().equals(word))
            {
                result = true;
            }
            return result;
        }
        catch(ClassCastException cce)
        {
            System.out.println(cce.getMessage());
        }
        return result;
    }

    @Override
    public int hashCode()
    {
        return this.get().hashCode();
    }
}
