package lab4;

import javax.xml.bind.annotation.XmlAttribute;
import java.io.PrintStream;

public class Photo implements HtmlTag {
    @XmlAttribute
    protected String url;

    public Photo(String url) {
        this.url = url;
    }

    public void writeHTML(PrintStream out) {
        out.printf("<img src=\"%s\">", this.url);
    }
}
