import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/** The Reading List logic, ported from TypeScript (see ../typescript/books.ts and ../SPEC.md). */
public class Books {

    static final Map<String, String> STATUS_LABELS = Map.of(
            "want-to-read", "Want to read",
            "reading", "Reading",
            "done", "Done");

    /** status is "want-to-read", "reading", "done" or "all". */
    static List<Book> filterBooks(List<Book> books, String status, String query) {
        // Trim first, then lower-case, and only then use the query.
        String normalizedQuery = query.strip().toLowerCase(Locale.ROOT);

        // An empty list, not null, when nothing matches.
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            boolean statusMatches = status.equals("all") || book.status().equals(status);
            boolean titleMatches = book.title().toLowerCase(Locale.ROOT).contains(normalizedQuery);
            if (statusMatches && titleMatches) {
                result.add(book);
            }
        }
        return result;
    }

    /** Returns an error message per invalid field. An empty map means valid. */
    static Map<String, String> validateBook(String title, String author) {
        Map<String, String> errors = new HashMap<>();
        if (title.strip().isEmpty()) {
            errors.put("title", "Title is required");
        }
        if (author.strip().isEmpty()) {
            errors.put("author", "Author is required");
        }
        return errors;
    }
}
