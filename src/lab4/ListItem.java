package lab4;

import javax.xml.bind.annotation.XmlValue;
import java.io.PrintStream;

public class ListItem implements HtmlTag {
    @XmlValue
    protected String content;

    public ListItem(String content) {
        this.content = content;
    }

    public void writeHTML(PrintStream out) {
        out.printf("<li>%s</li>", this.content);
    }
}
