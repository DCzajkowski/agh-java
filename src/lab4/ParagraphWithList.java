package lab4;

public class ParagraphWithList extends Paragraph {
    protected HtmlList items;

    public ParagraphWithList() {
        super();
    }

    public ParagraphWithList(String content) {
        super(content);
    }

    public void addItemToList(ListItem item) {
        this.items.addItem(item);
    }

    public ParagraphWithList addListItem(String content) {
        this.items.addItem(new ListItem(content));
        return this;
    }

    public ParagraphWithList setContent(String content) {
        super.setContent(content);
        return this;
    }

    public void writeHTML() {
        //
    }
}
