package lab4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParagraphTest extends HtmlTagTest {
    @Test
    void test_paragraph_contains_no_text_when_created_with_empty_constructor() {
        Paragraph paragraph = new Paragraph();

        String output = this.output(paragraph);

        assertTrue(output.contains("<p"));
        assertTrue(this.getStringBetweenTags(output).isEmpty());
        assertTrue(output.contains("</p>"));
    }

    @Test
    void test_paragraph_contains_given_text_when_initialized_with_one() {
        String content = "Content of a paragraph";
        Paragraph paragraph = new Paragraph(content);

        String output = this.output(paragraph);

        assertTrue(output.contains(content));
        assertTrue(output.contains("<p"));
        assertTrue(output.contains("</p>"));
    }

    @Test
    void test_paragraph_contains_given_text_when_content_set_afterwards() {
        String content = "Content of a paragraph";
        Paragraph paragraph = new Paragraph();
        paragraph.setContent(content);

        String output = this.output(paragraph);

        assertTrue(output.contains(content));
        assertTrue(output.contains("<p"));
        assertTrue(output.contains("</p>"));
    }
}
