package lab4;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Main {
    public static void main(String[] args) {
        Document cv = new Document("Jan Kowalski - CV");

        cv.setPhoto("https://www.1plusx.com/app/mu-plugins/all-in-one-seo-pack-pro/images/default-user-image.png");

        cv.addSection("Wykształcenie")
            .addParagraph("2000-2005 Przedszkole im. Królewny Śnieżki w ...")
            .addParagraph("2006-2012 SP7 im Ronalda Reagana w ...")
            .addParagraph("...");

        cv.addSection("Umiejętności")
            .addParagraph(
                new ParagraphWithList()
                    .setContent("Umiejętności")
                    .addListItem("C")
                    .addListItem("C++")
                    .addListItem("Java")
            );

        // Write to file
         try {
             cv.writeHTML(new PrintStream("cv.html", "UTF-8"));
         } catch (FileNotFoundException | UnsupportedEncodingException e) {
             System.out.println(e.getMessage());
         }

        // Write to the console
         cv.writeHTML(System.out);

        // Write to XML
        cv.write("cv.xml");
    }
}
