package lab4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnorderedListTest extends HtmlTagTest {
    @Test
    void test_list_is_empty_when_there_are_no_items() {
        UnorderedList list = new UnorderedList();

        String output = this.output(list);

        assertTrue(output.contains("<ul"));
        assertTrue(this.getStringBetweenTags(output).isEmpty());
        assertTrue(output.contains("</ul>"));
    }

    @Test
    void test_list_contains_added_items() {
        UnorderedList list = new UnorderedList();
        list.addItem(new ListItem("item 1"));
        list.addItem(new ListItem("item 2"));

        String output = this.output(list);

        assertTrue(output.contains("<ul"));
        assertTrue(output.contains(">item 1</"));
        assertTrue(output.contains(">item 2</"));
        assertTrue(output.contains("</ul>"));
    }
}
