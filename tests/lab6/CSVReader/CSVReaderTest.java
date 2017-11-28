package lab6.CSVReader;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class CSVReaderTest {
    @Test
    void test_exception_is_thrown_for_invalid_reader() {
        Executable callback = () -> new CSVReader("non-existent-file.csv", ",", true);

        assertThrows(IOException.class, callback, "Exception was expected to be thrown for illegal arguments.");
    }

    @Test
    void test_header_is_read_correctly() {
        String text = "a,b,c\n123.4,567.8,91011.12";

        try {
            CSVReader reader = new CSVReader(new StringReader(text), ",", true);

            assertArrayEquals(new String[]{"a", "b", "c"}, reader.getColumnLabels().toArray());
        } catch (IOException e) {
            fail("Exception was thrown, but was not expected");
        }
    }

    @Test
    void test_values_are_returned_correctly_when_header_is_available() {
        String text = "name,age,balance,pesel\nJohn Doe,20,11213.95,13311494985";

        try {
            CSVReader reader = new CSVReader(new StringReader(text), ",", true);

            reader.next();

            assertEquals("John Doe", reader.get("name"));
            assertEquals(20, reader.getInt("age"));
            assertEquals(11213.95D, reader.getDouble("balance"));
            assertEquals(13311494985L, reader.getLong("pesel"));
        } catch (IOException e) {
            fail("Exception was thrown, but was not expected");
        }
    }

    @Test
    void test_runtime_exception_is_thrown_when_there_is_no_call_to_next() {
        String text = "a,b,c\n123.4,567.8,91011.12";

        Executable callback = () -> {
            CSVReader reader = new CSVReader(new StringReader(text), ",", true);
            String _ = reader.get("name");
        };

        assertThrows(RuntimeException.class, callback, "Exception was expected to be thrown for no .next() call.");
    }

    @Test
    void test_values_are_returned_correctly_with_no_header() {
        String text = "John Doe,20,11213.95,13311494985";

        try {
            CSVReader reader = new CSVReader(new StringReader(text), ",", false);

            reader.next();

            assertEquals("John Doe", reader.get(0));
            assertEquals(20, reader.getInt(1));
            assertEquals(11213.95D, reader.getDouble(2));
            assertEquals(13311494985L, reader.getLong(3));
        } catch (IOException e) {
            fail("Exception was thrown, but was not expected");
        }
    }

    @Test
    void test_values_are_returned_correctly_with_colon_as_delimiter() {
        String text = "name;age;balance;pesel\nJohn Doe;20;11213.95;13311494985";

        try {
            CSVReader reader = new CSVReader(new StringReader(text), ";", true);

            reader.next();

            assertEquals("John Doe", reader.get("name"));
            assertEquals(20, reader.getInt("age"));
            assertEquals(11213.95D, reader.getDouble("balance"));
            assertEquals(13311494985L, reader.getLong("pesel"));
        } catch (IOException e) {
            fail("Exception was thrown, but was not expected");
        }
    }

    @Test
    void test_multiple_lines_are_correctly_read() {
        String text = "name,age,balance,pesel\nJohn Doe,20,11213.95,13311494985\nMiranda Jones,21,27234.24,93110158709";

        try {
            CSVReader reader = new CSVReader(new StringReader(text), ",", true);

            assertTrue(reader.next());

            assertEquals("John Doe", reader.get("name"));
            assertEquals(20, reader.getInt("age"));
            assertEquals(11213.95D, reader.getDouble("balance"));
            assertEquals(13311494985L, reader.getLong("pesel"));

            assertTrue(reader.next());

            assertEquals("Miranda Jones", reader.get("name"));
            assertEquals(21, reader.getInt("age"));
            assertEquals(27234.24D, reader.getDouble("balance"));
            assertEquals(93110158709L, reader.getLong("pesel"));

            assertFalse(reader.next());
        } catch (IOException e) {
            fail("Exception was thrown, but was not expected");
        }
    }

    @Test
    void test_runtime_exception_is_thrown_when_there_is_an_invalid_line() {
        String text = "a,b,c\n1,2,3\n4,5,6\n7,8\n9,10,11";

        try {
            CSVReader reader = new CSVReader(new StringReader(text), ",", true);

            assertTrue(reader.next());
            assertTrue(reader.next());

            assertThrows(RuntimeException.class, reader::next, "Invalid line did not throw an exception.");

            assertTrue(reader.next());
        } catch (IOException e) {
            fail("Exception was thrown, but was not expected");
        }
    }

    @Test
    void test_values_in_quotes_are_correctly_interpreted() {
        String text = "\"surname, name\",age,balance,pesel\n\"Doe, John\",20,11213.95,13311494985\n\"Jones, Miranda\",21,27234.24,93110158709";

        try {
            CSVReader reader = new CSVReader(new StringReader(text), ",", true);

            assertTrue(reader.next());

            assertEquals("Doe, John", reader.get("surname, name"));
            assertEquals(20, reader.getInt("age"));
            assertEquals(11213.95D, reader.getDouble("balance"));
            assertEquals(13311494985L, reader.getLong("pesel"));

            assertTrue(reader.next());

            assertEquals("Jones, Miranda", reader.get("surname, name"));
            assertEquals(21, reader.getInt("age"));
            assertEquals(27234.24D, reader.getDouble("balance"));
            assertEquals(93110158709L, reader.getLong("pesel"));

            assertFalse(reader.next());
        } catch (IOException e) {
            fail("Exception was thrown, but was not expected");
        }
    }
}
