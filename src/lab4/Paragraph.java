package lab4;

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

    void writeHTML() {
        //
    }
}
