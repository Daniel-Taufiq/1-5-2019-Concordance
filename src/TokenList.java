import java.text.*;
import java.util.*;
import java.io.*;

public class TokenList implements TokenFilter, Cloneable
{
    /*
     * Daniel Taufiq
     * 1/5/2020
     *
     * Class Description:
     *      Reads Strings or files and filters anything that is implemented.
     *
     * Static Class:
     *      static private class CaseInsensitiveAlphabetiCompare implements Comparator<String>
                compares the strings of objects and does natural sorting.
     *
     * Instance Variables:
     *      ArrayList<String> list
     *          list will hold any of the filtered content in ArrayList format.
     *
     * Constructor:
     *      Tokenlist()
     *          a default constructor used to instantiate the instance variable as empty
     *
     * Methods:
     *      public boolean accept(String token)
     *          this method has been inherited by the TokenFilter interface, it does a simple check
     *          to determine if the String is null.
     *      private ArrayList<String> getList()
     *          this method returns the current ArrayList.
     *      private void setList(ArrayList<String> list)
     *          sets an element in the ArrayList.
     *      public String[] toArray()
     *          converts ArrayList to array.
     *      private void updateUsing(String source, TokenFilter filter, BreakIterator breakIterator)
     *          takes in a String as actual parameter, filters out elements using TokenFilter object.
     *      private void updateUsing(String source, TokenFilter filter)
     *          overloaded method that calls 3 parameter method.
     *      private void updateUsing(String source)
     *          overloaded method that calls 2 parameter method.
     *      public void makeUnique()
     *          only keeps elements in the ArrayList that are unique, no repeated elements.
     *      public TokenList clone()
     *          override clone method and clones the TokenList object.
     *      private void updateUsing(String[] source, TokenFilter filter, BreakIterator breakIterator)
     *          takes in a String array as an actual parameter, filters out elements using TokenFilter object.
     *      public void updateUsing(String[] source, TokenFilter filter)
     *          overloaded method that calls the 3 parameter method.
     *      public void updateUsing(String[] source)
     *          overloaded method that calls the 2 parameter method.
     *      private void updateUsing(File source, TokenFilter filter, BreakIterator breakIterator) throws IOException
     *          takes a file as its actual parameter and filters out elements using TokenFilter object.
     *      public void updateUsing(File source, TokenFilter filter)
     *          overloaded method that calls the 3 parameter method.
     *      public void updateUsing(File source)
     *          overloaded method that calls the 2 parameter method.
     *      public void sort()
     *          calls sort with comparator parameter pass it a new CaseInsensitiveAlphabetiCompare()
     *      public void sort(Comparator<String> comparator)
     *          will do the natural ordering of the strings
     *      public String get(elementNumber)
     *          will return the element number at that index
     *      public boolean[] contains(TokenList other)
     *          will return a boolean array the size of list if value of string is in list
     *      public TokenList and(TokenList other)
     *          returns new TokenList containing elements shared by both lists.
     *      public TokenList or(TokenList other)
     *          returns new TokenList containing all elements in both lists.
     *      public TokenList minus(TokenList other)
     *          returns new TokenList containing elements only unique to "this" list.
     *      public TokenList xor(TokenList other)
     *          returns new TokenList containing elements that are only unique to both lists.
     *
     * Modification History:
     *      5-12-2019 : added trailing whitespace to the updateUsing file method
     *
     */
    private ArrayList<String> list;

    // constructor
    public TokenList()
    {
        list = new ArrayList<String>();
    }

    // accept method
    public boolean accept(String token)
    {
        boolean result;
        result = true;
        if (token == null || token.trim().length() < 1)
        {
            result = false;
        }
        return result;
    }

    // getList method
    private ArrayList<String> getList()
    {
        return this.list;
    }

    // setList method
    private void setList(ArrayList<String> list)
    {
        this.list = list;
    }

    public String[] toArray()
    {
        // declare array
        String[] result;

        // initialize array to size of ArrayList size
        result = new String[list.size()];

        // convert ArrayList to Array
        result = list.toArray(result);

        return result;
    }

    private void updateUsing(String source, TokenFilter filter, BreakIterator breakIterator)
    {
        int end;
        boolean result;
        int start;

        if (source == null)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    "String cannot be null" + source);
        }


        breakIterator.setText(source);

        start = breakIterator.first();
        end = breakIterator.next();

        while (end != BreakIterator.DONE)
        {
            result = filter.accept(source.substring(start, end));
            if (result)
            {
                list.add(source.substring(start, end));
            }
            start = end;
            end = breakIterator.next();
        }
    }

    // overloaded methods
    public void updateUsing(String source, TokenFilter filter)
    {
        if (source == null)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    "String cannot be null" + source);
        }
        this.updateUsing(source, filter, BreakIterator.getWordInstance());
    }

    public void updateUsing(String source)
    {
        if (source == null)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    "String cannot be null" + source);
        }
        if (source.length() < 1)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    "String cannot be less than 1" + source);
        }
        this.updateUsing(source, this);
    }

    public void makeUnique()
    {
        ArrayList<String> list2;
        boolean result;

        list2 = new ArrayList<String>();

        for (int i = 0; i < list.size(); i++)
        {
            if (!list2.contains(list.get(i)))
            {
                list2.add(list.get(i));
            }
        }
        setList(list2);
        list = list2;
    }

    // want to clone arraylist so don't give copies to same arraylist
    @Override
    public TokenList clone()
    {
        TokenList result;
        result = new TokenList();
        result.list = new ArrayList<String>(this.list);
        return result;
    }

    private void updateUsing(String[] source, TokenFilter filter, BreakIterator breakIterator)
    {
        int end;
        boolean result;
        String text;
        int start;

        list = new ArrayList<String>();

        for (int i = 0; i < source.length; i++)
        {
            text = source[i].substring(0);
            breakIterator.setText(text);

            start = breakIterator.first();
            end = breakIterator.next();

            while (end != BreakIterator.DONE)
            {
                result = filter.accept(text.substring(start, end));
                if (result)
                {
                    list.add(text.substring(start, end));
                }
                start = end;
                end = breakIterator.next();
            }
        }
    }

    // overloaded methods
    public void updateUsing(String[] source, TokenFilter filter)
    {
        if (source == null)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    "String cannot be null" + source);
        }
        if (source.length < 1)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    "String cannot be less than 1" + source);
        }
        this.updateUsing(source, filter, BreakIterator.getWordInstance());
    }

    public void updateUsing(String[] source)
    {
        if (source == null)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    "String cannot be null" + source);
        }
        if (source.length < 1)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    "String cannot be less than 1" + source);
        }
        this.updateUsing(source, this);
    }

    private void updateUsing(File source, TokenFilter filter, BreakIterator breakIterator) throws IOException
    {
        int end;
        BufferedReader input;
        boolean result;
        int start;
        String text;

        list = new ArrayList<String>();

        input = new BufferedReader(new FileReader(source));

        text =input.readLine() + " ";    //word letters numbers symbols

        while(text != null)
        {
            updateUsing(text + " ", filter);
            text = input.readLine();
        }

//        while (text != null)
//        {
//            breakIterator.setText(text);
//            start = breakIterator.first();
//            end = breakIterator.next();
//
//            while (end != BreakIterator.DONE)
//            {
//                result = filter.accept(text.substring(start, end));
//
//                if (result)
//                {
//                    list.add(text.substring(start, end));
//                }
//                start = end;
//                end = breakIterator.next();
//            }
//            text = input.readLine();
//        }
        input.close();
    }

    public void updateUsing(File source, TokenFilter filter) throws IOException
    {
        if (source == null)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    "String cannot be null" + source);
        }

        this.updateUsing(source, filter, BreakIterator.getWordInstance());
    }

    public void updateUsing(File source) throws IOException
    {
        if (source == null)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    "String cannot be null" + source);
        }
        if (source.length() < 1)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    "String cannot be less than 1" + source);
        }
        this.updateUsing(source, this);
    }

    public void updateUsing(TokenFilter filter)
    {
        ArrayList<String> list2;

        list2 = new ArrayList<String>();

        for(int i = 0; i < list.size(); i++)
        {
            if(filter.accept(list.get(i)))
            {
                list2.add(list.get(i));
            }
        }
        setList(list2);
    }

    public static void main(String[] args)
    {
        TokenList list;
        ArrayList<String> a;
        list = new TokenList();
        a = new ArrayList<String>();

    }


    public boolean contains(String x)
    {
        boolean result;

        result = false;

        if (x == null)
        {
            throw new IllegalArgumentException("token cannot be null" + x);
        }
        if (x.length() < 1)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    "String cannot be null" + x);
        }

        if (this.getList().contains(x))
        {
            result = true;
        }
        return result;
    }

    public void sort()
    {
        this.sort(new CaseInsensitiveAlphabetiCompare());
    }

    public void sort(Comparator<String> comparator)
    {
        if (comparator == null)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    "sort method: comparator String is null. " + comparator);
        }
        // invoke the two parameter Collections.sort method
        Collections.sort(list, new CaseInsensitiveAlphabetiCompare());
    }

    public String get(int elementNumber)
    {
        String x;

        if (elementNumber < 0)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    "sort method: elementNumber cannot be 0. " + elementNumber);
        }
        if (elementNumber > list.size())
        {
            throw new IllegalArgumentException(getClass().getName() +
                    "sort method: elementNumber larger than size of list. " + elementNumber);
        }


        x = list.get(elementNumber);
        return x;
    }

    public boolean[] contains(TokenList other)
    {
        if (other == null)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    "String cannot be null" + other);
        }
        boolean[] result;
        result = new boolean[list.size()];
        for (int i = 0; i < list.size(); i++)
        {
            if (other.contains(list.get(i)))
            {
                result[i] = true;
            }
        }
        return result;
    }

    public TokenList and(TokenList other)
    {
        if (other == null)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    "token cannot be null" + other);
        }

        ArrayList<String> list2;
        TokenList list3;
        ArrayList<String> list4;
        String x;

        list2 = new ArrayList<String>();
        list3 = new TokenList();
        list4 = other.getList();

        for (int i = 0; i < list.size(); i++)
        {
            x = list.get(i);
            if (list4.contains(x))
            {
                list2.add(list.get(i));
            }
        }
        list3.setList(list2);
        return list3;
    }

    public TokenList xor(TokenList other)
    {
        if (other == null)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    "token cannot be null" + other);
        }
        ArrayList<String> list2;
        TokenList list3;
        ArrayList<String> list4;

        list2 = new ArrayList<String>();
        list3 = new TokenList();
        list4 = other.getList();

        for (int i = 0; i < list.size() && i < list4.size(); i++)
        {
            if (!list.contains(other.get(i)))
            {
                list2.add(other.get(i));
            }
            if (!other.contains(list.get(i)))
            {
                list2.add(list.get(i));
            }
        }
        list3.setList(list2);
        return list3;
    }

    public TokenList minus(TokenList other)
    {
        if (other == null)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    "token cannot be null" + other);
        }

        ArrayList<String> list2;
        TokenList list3;
        ArrayList<String> list4;

        list2 = new ArrayList<String>();
        list3 = new TokenList();
        list4 = other.getList();

        for (int i = 0; i < list.size() && i < list4.size(); i++)
        {
            if (!list4.contains(list.get(i)))
            {
                list2.add(list.get(i));
            }
        }
        list3.setList(list2);
        return list3;
    }

    public TokenList or(TokenList other)
    {
        if (other == null)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    "token cannot be null" + other);
        }
        ArrayList<String> list2;
        TokenList list3;
        ArrayList<String> list4;

        list2 = new ArrayList<String>();
        list3 = new TokenList();
        list4 = other.getList();

        for (int i = 0; i < list.size(); i++)
        {
            list2.add(list.get(i));
        }
        for (int i = 0; i < list4.size(); i++)
        {
            list2.add(list4.get(i));
        }
        list3.setList(list2);
        return list3;
    }

    public int size()
    {
        return this.list.size();
    }

    static private class CaseInsensitiveAlphabetiCompare implements Comparator<String>
    {
        @Override
        public int compare(String obj1, String obj2)
        {
            int result;
            result = obj1.compareToIgnoreCase(obj2);
            return result;
        }
    }
}