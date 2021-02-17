import java.io.File;
import java.io.IOException;

public class Driver
{
    public static void main(String[] args) throws IOException, InterruptedException
    {
        Book b;
        File file;
        file = new File("TreasureIsland.txt");
        //file = new File("The Longest Journey.txt");
        b = new Book(file, "Stevenson", "Pieces of eight! Pieces of eight!");
        //b = new Book(file, "Forster", "introductory memoir.");

        Concordance c;
        c = new Concordance(b);

        Word wordA;
        Word wordB;
        wordA = new Word("treasure");
        wordB = new Word("buried");
        //wordA = new Word("will");
        //wordB = new Word("about");

        c.getContext(wordA, 2, wordB);


        c.display(3, 3);
    }
}
