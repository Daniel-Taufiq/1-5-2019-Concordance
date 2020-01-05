public class WordFilter implements TokenFilter
{
    /*
        Daniel Taufiq
        1/5/2020

        Class Description:
            filters out words of a String.

        Method:
            public boolean accept(String token)
                returns true only if the String is greater than 1 and starts with a letter.
     */

    public boolean accept(String token)
    {
        boolean	result;

        result = true;

        if(token == null) { result = false; }

        if(token.length() < 1) { result = false; }

        if(!Character.isLetter(token.charAt(0))) { result = false; }

        return result;
    }
}
