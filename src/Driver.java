import java.io.File;
import java.io.IOException;

public class Driver
{
    public static void main(String[] args) throws IOException, InterruptedException
    {
        Book b;
        File file;
        file = new File("TreasureIsland.txt");
        b = new Book(file, "Stevenson", "Pieces of eight! Pieces of eight!");

        Concordance c;
        c = new Concordance(b);

        Word wordA;
        Word wordB;
        wordA = new Word("treasure");
        wordB = new Word("buried");

        c.getContext(wordA, 2, wordB);


        c.display(3, 3);
    }
}
