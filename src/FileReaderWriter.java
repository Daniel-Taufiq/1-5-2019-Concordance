import java.io.*;
import java.util.*;
public class FileReaderWriter
{
    /*
		Daniel Taufiq
		5/9/2019
		Dr. Jarvis

		Class Description:
			reads and writes to files

		Instance Variables:
			private static final FileReaderWriter instance

	*/

    private static final FileReaderWriter instance = new FileReaderWriter();

    private FileReaderWriter() {};

    public static FileReaderWriter getInstance() { return instance; }

    public static FileReaderWriter newInstance() { return getInstance(); }

    public ArrayList<String> read(File source) throws IOException
    {
        // declare local variables
        BufferedReader 	input = null;
        String 			record;
        ArrayList		list;

        list = new ArrayList<String>();							// initialize ArrayList

        if(source == null)
        {
            throw new IllegalArgumentException(getClass().getName() +
                    "read method: File source cannot be null " + source);
        }
        try
        {
            input = new BufferedReader(new FileReader(source));	// open file
            record = input.readLine();							// read the first line of text

            while(record != null)								// keep reading until reaches end of file
            {
                list.add(record);								// add line of text to ArrayList
                record = input.readLine();						// read the next line
            }

        }
        catch(FileNotFoundException fnfe)
        {
            System.out.println("cannot locate file " + fnfe.getMessage());
        }
        finally
        {
            try
            {
                input.close();
            }
            catch(IOException ioe)
            {
                System.out.println("Could not close input " + ioe.getMessage());
            }
        }
        return list;
    }

    public void write(File file, String source) throws IOException	// write this "source" onto existing file
    {
        String[]	        array;
        ArrayList<String>	list;
        PrintWriter         writer;

        writer =  new PrintWriter(file);

        list = read(file);	// call read method & return ArrayList

        list.add(source);   // add source to ArrayList

        array = list.toArray(new String[0]);

        write(writer, array);

        writer.close();
    }


    public void write(File file, ArrayList<String> source) throws IOException
    {
        String[]            array;
        ArrayList<String>   list;
        PrintWriter         writer;

        writer = new PrintWriter(file);

        list = read(file);

        for(int i = 0; i < source.size(); i++)
        {
            list.add(source.get(i));
        }

        array = list.toArray(new String[0]);

        write(writer, array);

        writer.close();
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