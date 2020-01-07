import java.awt.*;
import java.io.*;
import java.util.*;
public class HTML
{
    /*
		Daniel Taufiq
		5/14/2019
		Dr. Jarvis

		Class Description:
			Displays the results to the web browser

		Instance Variables:
			private ArrayList<String> html
				holds tge Arraylist of the results
		    private static File PROPERTIES_FILE
		    	identifies the properties file
		    private static String PROPERTIES_KEYWORD
		    	holds the keyword for the properties file
    		private static ArrayList<String> HTML_TEMPLATE
    			loads the HTML template

    	Constructor:
    		public HTML()
    			checks the templte and adds each line of the template to the file.

    	Methods:
    		private ArrayList<String> getHTML()
    			returns the instance variable
    		public void add(String html)
    			adds the results with the tags to the instance variable
    		public void addInsideDiv(String html)
    			adds div tags around the parameter
    		public void addInsideDiv(String prologue, String searchTermOne, String textBetweenSearchTerms, String searchTermTwo, String epilogue)
    			adds the span tags to all 5 parameters
    		public void launchBrowser()
    			creates temporary file and writes data to it and launches browser

	*/

    private static File PROPERTIES_FILE = new File("Concordance.properties");
    private static String PROPERTIES_KEYWORD = "HTMLTemplate.html";
    private static ArrayList<String> HTML_TEMPLATE = loadHTMLTemplate();
    private ArrayList<String> html;

    public HTML()
    {
        this.html = new ArrayList<String>();
    }

    private static ArrayList<String> loadHTMLTemplate()
    {
        ArrayList<String> result;
        result = null;
        Properties properties;
        properties = new Properties();

        try
        {
            properties.load(new FileReader(PROPERTIES_FILE));
            result = FileReaderWriter.getInstance().read(new File(PROPERTIES_KEYWORD));
            if(properties == null)
            {
                throw new RuntimeException(" loadHTMLTemplate method: properties file not found");
            }
        }
        catch (IOException ioe) { ioe.getMessage(); }
        if(!result.contains("</div>"))
        {
            throw new RuntimeException("loadHTMLTemplate method: missing div tag ");
        }
        return result;
    }

    private ArrayList<String> getHTML() { return this.html; }

    public void add(String html) { this.html.add(html); }

    public void addInsideDiv(String html)
    {
        html= "<div>" + html + "</div>";
        add(html);
    }

    public void addInsideDiv(String prologue, String searchTermOne, String textBetweenSearchTerms, String searchTermTwo, String epilogue)
    {
        String results;
        prologue  = "<span>" + prologue + "</span>";
        epilogue = "<span>" + epilogue + "</span>";
        searchTermOne = "<span> " + searchTermOne + "</span>";
        searchTermTwo = "<span> " + searchTermTwo + "</span>";
        textBetweenSearchTerms = "<span>" + textBetweenSearchTerms + "</span>";

        results = prologue + searchTermOne + textBetweenSearchTerms + searchTermTwo + epilogue;
        addInsideDiv(results);
    }

    public void launchBrowser()
    {
        ArrayList<String>   hold;
        int                 index;
        File                tempFile;

        hold = new ArrayList<String>();
        hold = HTML.HTML_TEMPLATE;

        index = hold.indexOf("</div>");
        for(int i = 0; i < getHTML().size(); i++)
        {
            hold.add(index, html.get(i));
            index++;
        }

        try
        {
            tempFile = File.createTempFile("ContextSearchResults",".html");
            FileReaderWriter.getInstance().write(tempFile, hold);
            Desktop.getDesktop().open(tempFile);
            tempFile.deleteOnExit();
        }
        catch (IOException ioe) { ioe.getMessage(); }
    }
}