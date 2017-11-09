package lab4;

import java.io.PrintStream;

public class ParagraphWithList extends Paragraph {
    protected HtmlList list;

    public ParagraphWithList() {
        super();
        this.list = new UnorderedList();
    }

    public ParagraphWithList(String content) {
        super(content);
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
        this.list.writeHTML(out);
    }
}
