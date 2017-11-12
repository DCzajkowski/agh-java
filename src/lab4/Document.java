package lab4;

import java.util.ArrayList;
import java.util.List;
import java.io.PrintStream;

public class Document implements HtmlTag {
    protected String title;
    protected Photo photo;
    protected List<Section> sections = new ArrayList<>();

    public Document(String title) {
        this.setTitle(title);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPhoto(String url) {
        this.photo = new Photo(url);
    }

    public Section addSection(String title) {
        Section section = new Section(title);
        this.sections.add(section);
        return section;
    }

    public void writeHTML(PrintStream out) {
        this.writeHeadOpen(out);
        this.writeEncoding(out);
        this.writeTitle(out);
        this.writeStyles(out);
        this.writeHeadClose(out);
        this.writeBodyOpen(out);
        this.writeHeading(out);
        this.writeSections(out);
        this.writeDocumentEnd(out);
    }

    protected void writeEncoding(PrintStream out) {
        out.print("<meta charset=\"utf-8\">");
    }

    protected void writeSections(PrintStream out) {
        for (Section section : this.sections) {
            section.writeHTML(out);
        }
    }

    protected void writeHeading(PrintStream out) {
        out.print("<div class=\"heading\">");

        out.printf("<h1>%s</h1>", this.title);

        this.photo.writeHTML(out);

        out.print("</div>");
    }

    protected void writeDocumentEnd(PrintStream out) {
        out.print("</div></body></html>");
    }

    protected void writeBodyOpen(PrintStream out) {
        out.print("<body><div class=\"content\">");
    }

    protected void writeHeadClose(PrintStream out) {
        out.print("</head>");
    }

    protected void writeHeadOpen(PrintStream out) {
        out.print("<!DOCTYPE html><html><head>");
    }

    protected void writeStyles(PrintStream out) {
        out.print("<style>.heading{display:flex;align-items:center}h1{flex:1}img{width:80px}.content{padding:1rem 2rem;font-family:Arial,sans-serif}</style>");
    }

    protected void writeTitle(PrintStream out) {
        out.printf("<title>%s</title>", this.title);
    }
}
