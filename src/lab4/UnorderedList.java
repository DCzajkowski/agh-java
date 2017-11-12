package lab4;

import java.io.PrintStream;

public class UnorderedList extends HtmlList {
    public void writeHTML(PrintStream out) {
        out.print("<ul>");

        for (ListItem item : items) {
            item.writeHTML(out);
        }

        out.print("</ul>");
    }
}
