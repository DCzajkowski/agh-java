package lab4;

import java.util.List;

public class UnorderedList implements HtmlList {
    List<ListItem> items;

    public void addItem(ListItem item) {
        this.items.add(item);
    }

    public void writeHTML() {
        //
    }
}
