import java.io.*;
import java.util.*;

public class FileReaderWriter
{
    private static final FileReaderWriter instance = new FileReaderWriter();

    // forces user to obtain reference through the static method
    private FileReaderWriter() {};

    public static FileReaderWriter getInstance() { return instance; }

    public static FileReaderWriter newInstance() { return getInstance(); }

    public ArrayList<String> read(File source) throws IOException
    {
        BufferedReader input = null;
        String record;
        ArrayList list;

        list = new ArrayList();

        if(source == null)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    "read method: File source cannot be null " + source);
        }

        input = new BufferedReader(new FileReader(source));
        record = input.readLine();

        while(record != null)
        {
            list.add(record);
            record = input.readLine();
        }

        input.close();
        return list;
    }
    public void write(File file, String source) throws IOException	// write this "source" onto existing file
    {
        ArrayList<String>	list;

        list = new ArrayList<String>();

        list.add(source);   // add source to ArrayList

        write(file, list);
    }


    public void write(File file, ArrayList<String> source) throws IOException
    {
        String[]            array;
        ArrayList<String>   list;
        PrintWriter         writer;

        list = new ArrayList<String>();

        for(int i = 0; i < source.size(); i++)
        {
            list.add(source.get(i));
        }

        array = list.toArray(new String[0]);

        write(file, array);
    }

    public void write(File file, String[] source) throws IOException
    {
        String[]            array;
        ArrayList<String>   list;
        PrintWriter         writer;

        writer = new PrintWriter(file);
        list = new ArrayList<String>();

        for(int i = 0; i < source.length; i++)
        {
            list.add(source[i]);
        }

        array = list.toArray(new String[0]);

        write(writer, array);

        writer.close();
    }

    public void write(PrintWriter output, String[] source) throws IOException
    {
        for(int i = 0; i < source.length; i++)
        {
            output.println(source[i]);
        }
    }
}
