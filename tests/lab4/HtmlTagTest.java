package lab4;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.fail;

public abstract class HtmlTagTest {
    protected String output(HtmlTag tag) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outputStream);

        tag.writeHTML(out);

        String output = "";

        try {
            output = outputStream.toString("UTF8");
        } catch (UnsupportedEncodingException e) {
            fail("Unsupported charset");
        }

        return output;
    }

    public String getStringBetweenTags(String output) {
        return output.substring(output.indexOf(">") + 1).substring(0, output.indexOf("<"));
    }
}
