package lab4;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Section implements HtmlTag {
    @XmlAttribute
    protected String title;

    @XmlElements(value = {
        @XmlElement(name = "paragraph", type = Paragraph.class),
        @XmlElement(name = "paragraph-with-list", type = ParagraphWithList.class),
    })
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

    public void writeHTML(PrintStream out) {
        out.printf("<h2>%s</h2>", this.title);

        for (Paragraph paragraph : paragraphs) {
            paragraph.writeHTML(out);
        }
    }
}
