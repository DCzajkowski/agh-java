package lab4;

import java.util.List;

public class Document {
    protected String title;
    protected Photo photo;
    protected List<Section> sections;

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

    public void writeHTML() {
        //
    }
}
