package lab4;

import java.io.PrintStream;

public class OrderedList extends HtmlList {
    public void writeHTML(PrintStream out) {
        out.print("<ol>");

        for (ListItem item : items) {
            item.writeHTML(out);
        }

        out.print("</ol>");
    }
}
