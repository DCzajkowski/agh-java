package lab4;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import java.io.PrintStream;

public class ParagraphWithList extends Paragraph implements HtmlTag {
    @XmlElements(value = {
        @XmlElement(name = "unordered-list", type = UnorderedList.class),
        @XmlElement(name = "ordered-list", type = OrderedList.class)
    })
    protected HtmlListInterface list;

    public ParagraphWithList() {
        super();
        this.list = new UnorderedList();
    }

    public ParagraphWithList(String content) {
        super(content);
        this.list = new UnorderedList();
    }

    public void addItemToList(ListItem item) {
        this.list.addItem(item);
    }

    public ParagraphWithList addListItem(String content) {
        this.list.addItem(new ListItem(content));
        return this;
    }

    public ParagraphWithList setContent(String content) {
        super.setContent(content);
        return this;
    }

    public void writeHTML(PrintStream out) {
        out.printf("<p>%s", this.content);
        this.list.writeHTML(out);
        out.print("</p>");
    }
}
