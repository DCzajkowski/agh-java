package lab4;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Section {
    protected String title;
    protected List<Paragraph> paragraphs = new ArrayList<>();

    public Section(String title) {
        this.setTitle(title);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Section addParagraph(ParagraphWithList paragraph) {
        this.paragraphs.add(paragraph);
        return this;
    }

    public Section addParagraph(String content) {
        Paragraph paragraph = new Paragraph(content);
        this.paragraphs.add(paragraph);
        return this;
    }

    void writeHTML(PrintStream out) {
        out.printf("<h2>%s</h2>", this.title);

        for (Paragraph paragraph : paragraphs) {
            paragraph.writeHTML(out);
        }
    }
}
