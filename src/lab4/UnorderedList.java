package lab4;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class UnorderedList implements HtmlList {
    List<ListItem> items = new ArrayList<>();

    public void addItem(ListItem item) {
        this.items.add(item);
    }

    public void writeHTML(PrintStream out) {
        out.print("<ul>");

        for (ListItem item : items) {
            item.writeHTML(out);
        }

        out.print("</ul>");
    }
}
