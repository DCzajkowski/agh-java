package lab4;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Document implements HtmlTag {
    @XmlElement
    protected String title;

    @XmlElement
    protected Photo photo;

    @XmlElement(name = "section")
    protected List<Section> sections = new ArrayList<>();

    public Document(String title) {
        this.setTitle(title);
    }

    /* Required for XmlRootElement */
    public Document() {
        this.setTitle("");
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

    public void write(String fileName) {
        try {
            JAXBContext jc = JAXBContext.newInstance(Document.class);
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            FileWriter writer = new FileWriter(fileName);
            m.marshal(this, writer);
        } catch (JAXBException | IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Document read(String fileName) {
        try {
            JAXBContext jc = JAXBContext.newInstance(Document.class);
            Unmarshaller m = jc.createUnmarshaller();
            FileReader reader = new FileReader(fileName);
            return (Document) m.unmarshal(reader);
        } catch (JAXBException | FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
