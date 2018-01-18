package kolokwium.books;

import java.io.IOException;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        BooksList list = new BooksList();
        String filename = System.getProperty("user.dir") + ("/src/kolokwium/books/assets/ibuk_wykaz_pozycji.csv".replace("\\/", java.nio.file.FileSystems.getDefault().getSeparator()));

        try {
            list.read(filename);
        } catch (IOException e) {
            System.out.printf("Failed to open the file: %s\n", filename);
            e.printStackTrace();
        }

        System.out.println(list.getYearToAmountMap());
        System.out.println(list.getAllByPublisher("Wydawnictwo Naukowe PWN").stream().map(book -> book.title).collect(Collectors.toList()));
        System.out.println(list.getCategoryToAmountMap());
    }
}
