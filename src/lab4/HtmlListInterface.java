package lab4;

import java.io.PrintStream;

public interface HtmlListInterface {
    void addItem(ListItem item);
    void writeHTML(PrintStream out);
}
