public class Word implements Comparable<Word>
{
    /*
        Daniel Taufiq
        1/7/2019

        Class Description:
            holds the String of a word that is being used by the
            Book class

        Instance Variables:
            String word
                holds a word as a String

        Constructor:
            public Word(String word)
                initializes the instance variable

        Methods:
            public static Word[] toWordArray(String[] array)
                converts the String array to a Word object array
            public String get()
                returns the instance variable
            public int compareTo(Word other)
                compares the String object to the Word object ignoring
                case sensitivity.
            public boolean equals(Object other)
                overrides the Object equals method and calls the compareTo
                method
            public int hashCode()
                overrides the Object hashCode method and returns the hashcode
                integer for the word
     */
    private String word;

    public Word(String word)
    {
        if(word == null)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    "Word constructor: word cannot be null " + word);
        }
        this.word = word;
    }

    public static Word[] toWordArray(String[] array)
    {
        Word[] result;
        result = new Word[array.length];

        for(int i = 0; i < array.length; i++) { result[i] = new Word(array[i]); }

        return result;
    }

    public String get() { return this.word; }

    @Override
    public int compareTo(Word other) { return get().compareToIgnoreCase(other.get()); }

    @Override
    public boolean equals(Object other)
    {
        if(other == null)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    " equals method: other cannot be null " + other);
        }

        try { return compareTo((Word) other) == 0; }
        catch(ClassCastException cce)
        {
            throw new RuntimeException(getClass().getName() +
                    " equals method: cannot cast to Word " + cce.getMessage());
        }
    }

    @Override
    public int hashCode() { return this.get().toUpperCase().hashCode(); }
}
