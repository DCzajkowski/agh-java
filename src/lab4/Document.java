package lab4;

import java.util.ArrayList;
import java.util.List;
import java.io.PrintStream;

public class Document {
    protected String title;
    protected Photo photo;
    protected List<Section> sections =  new ArrayList<Section>();

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
        out.printf("<!DOCTYPE html><html><head><title>%s</title></head><body><div>", this.title);
        out.printf("<h1>%s</h1>", this.title);

        this.photo.writeHTML(out);

        for (Section section : sections) {
            section.writeHTML(out);
        }

        out.print("</div></body></html>");
    }
}
