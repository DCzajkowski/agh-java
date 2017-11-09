package lab4;

import java.io.PrintStream;

public class Paragraph {
    protected String content;

    public Paragraph() {
         this.setContent("");
    }

    public Paragraph(String content) {
        this.setContent(content);
    }

    public Paragraph setContent(String content) {
        this.content = content;
        return this;
    }

    public void writeHTML(PrintStream out) {
        out.printf("<p>%s</p>", this.content);
    }
}
