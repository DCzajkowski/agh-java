package lab4;

public class Main {
    public static void main(String[] args) {
        Document cv = new Document("Jan Kowalski - CV");

        cv.setPhoto("https://en.gravatar.com/avatar/a703468a9fcafd4f309b0c8def5b5738.jpg");

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

        cv.writeHTML(System.out);
    }
}
