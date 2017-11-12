package lab4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListItemTest extends HtmlTagTest {
    @Test
    void test_list_item_is_created_properly() {
        ListItem item = new ListItem("item 1");

        String output = this.output(item);

        assertTrue(output.contains("<li"));
        assertEquals("item 1", this.getStringBetweenTags(output));
        assertTrue(output.contains("</li>"));
    }
}
