package kolokwium.books;

import lab6.CSVReader.CSVReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static app.Helpers.tap;

public class BooksList {
    List<Book> books = new ArrayList<>();

    public void read(String filename) throws IOException {
        CSVReader reader = new CSVReader(filename, ";", true);

        while (reader.next()) {
            this.books.add(tap(new Book(), book -> {
                // book.id = reader.get("Ibuk ID");
                book.title = reader.get("Tytuł");
                book.author = reader.get("Autor");
                book.isbn = reader.get("ISBN");
                book.publisher = reader.get("Wydawnictwo");
                book.year = reader.get("Rok wydania");
                book.category = reader.get("Kategoria");
                book.subcategory = reader.get("Podkategoria");
                // book.link = reader.get("Link do książki");
            }));
        }
    }

    public Map<String, Integer> getYearToAmountMap() {
        return tap(new HashMap<>(), result -> this.books.forEach(book -> {
            if (result.containsKey(book.year)) {
                result.replace(book.year, result.get(book.year) + 1);
            } else {
                result.put(book.year, 1);
            }
        }));
    }

    public List<Book> getAllByPublisher(String publisher) {
        return books.stream().filter(book -> book.publisher.equals(publisher)).collect(Collectors.toList());
    }

    public Map<String, Integer> getCategoryToAmountMap() {
        return tap(new HashMap<>(), result -> this.books.forEach(book -> {
            if (result.containsKey(book.category)) {
                result.replace(book.category, result.get(book.category) + 1);
            } else {
                result.put(book.category, 1);
            }
        }));
    }
}
