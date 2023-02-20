import java.awt.*;
import java.net.URI;

public class GFG {
    public static void main(String[] args)
            throws Exception
    {
        Desktop desk = Desktop.getDesktop();

        // now we enter our URL that we want to open in our
        // default browser
        desk.browse(new URI("https://mytimetable.mmu.ac.uk/m/#loggedin"));
    }
}
