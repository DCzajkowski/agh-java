package lab4;

import java.io.PrintStream;

public class Photo {
    protected String url;

    public Photo(String url) {
        this.url = url;
    }

    void writeHTML(PrintStream out) {
        out.printf("<img src=\"%s\">", this.url);
    }
}
