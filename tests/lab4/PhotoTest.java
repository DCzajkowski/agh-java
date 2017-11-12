package lab4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhotoTest extends HtmlTagTest {
    @Test
    void test_image_tag_is_created_properly() {
        Photo photo = new Photo("http://example.com");

        String output = this.output(photo);

        assertTrue(output.contains("<img"));
        assertTrue(output.contains("src=\"http://example.com\""));
    }
}
