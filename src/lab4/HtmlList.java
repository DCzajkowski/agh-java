package lab4;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public abstract class HtmlList implements HtmlListInterface, HtmlTag {
    List<ListItem> items = new ArrayList<>();

    public void addItem(ListItem item) {
        this.items.add(item);
    }

    public abstract void writeHTML(PrintStream out);
}
