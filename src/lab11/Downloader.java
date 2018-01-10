package lab11;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Downloader extends Thread implements Runnable {
    private final String url;

    Downloader(String url) {
        this.url = url;
    }

    public void run() {
        String filename = System.getProperty("user.dir")
            + ("/src/lab11/out" + this.url.substring(this.url.lastIndexOf("/")))
            .replace("\\/", java.nio.file.FileSystems.getDefault().getSeparator());

        File file = new File(filename);

        try {
            InputStream in = new URL(url).openStream();
            file.createNewFile();
            FileOutputStream out = new FileOutputStream(file);

            while (true) {
                int nextByte = in.read();

                if (nextByte < 0) break;

                out.write(nextByte);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done: " + filename);
    }
}
