package lab4;

import java.util.List;

public class Section {
    protected String title;
    protected List<Paragraph> paragraphs;

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

    void writeHTML() {
        //
    }
}
