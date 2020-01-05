public interface TokenFilter
{
    /*
        Daniel Taufiq
        1/5/2020
        Interface Description:
            allows objects to use other classes that implement this interface
        Method:
            public boolean accept(String token)
                method name being used by other classes that accept a token
     */

    public boolean accept(String token);
}