package lab4;

import java.io.PrintStream;

public interface HtmlList {
    public void addItem(ListItem item);
    public void writeHTML(PrintStream out);
}
