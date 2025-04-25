import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.*;

class Book {
    private String title;
    private String author;
    private String genre;
    private double rating;

    public Book(String title, String author, String genre, double rating) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.rating = rating;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public double getRating() { return rating; }
}

class BookRecommendation {
    private String title;
    private double rating;

    public BookRecommendation(String title, double rating) {
        this.title = title;
        this.rating = rating;
    }

    public String getTitle() { return title; }
    public double getRating() { return rating; }

    @Override
    public String toString() {
        return title + " (Rating: " + rating + ")";
    }
}

public class BookRecommender {
    public static List<List<BookRecommendation>> recommendBooks(List<Book> books) {
        List<BookRecommendation> topBooks = books.stream()
            .filter(b -> b.getGenre().equalsIgnoreCase("Science Fiction"))
            .filter(b -> b.getRating() > 4.0)
            .map(b -> new BookRecommendation(b.getTitle(), b.getRating()))
            .sorted(Comparator.comparingDouble(BookRecommendation::getRating).reversed())
            .limit(10)
            .collect(toList());

        List<List<BookRecommendation>> pages = new ArrayList<>();
        int pageSize = 5;
        for (int i = 0; i < topBooks.size(); i += pageSize) {
            pages.add(topBooks.subList(i, Math.min(i + pageSize, topBooks.size())));
        }

        return pages;
    }

    public static void main(String[] args) {
        List<Book> books = Arrays.asList(
            new Book("Dune", "Frank Herbert", "Science Fiction", 4.6),
            new Book("Neuromancer", "William Gibson", "Science Fiction", 4.2),
            new Book("Foundation", "Isaac Asimov", "Science Fiction", 4.5),
            new Book("Snow Crash", "Neal Stephenson", "Science Fiction", 4.1),
            new Book("Hyperion", "Dan Simmons", "Science Fiction", 4.3),
            new Book("Ender's Game", "Orson Scott Card", "Science Fiction", 4.4),
            new Book("The Martian", "Andy Weir", "Science Fiction", 4.7),
            new Book("The Left Hand of Darkness", "Ursula K. Le Guin", "Science Fiction", 4.0),
            new Book("Blindsight", "Peter Watts", "Science Fiction", 4.2),
            new Book("Old Man's War", "John Scalzi", "Science Fiction", 4.1),
            new Book("Red Mars", "Kim Stanley Robinson", "Science Fiction", 4.3)
        );

        List<List<BookRecommendation>> pages = recommendBooks(books);
        for (int i = 0; i < pages.size(); i++) {
            System.out.println("Page " + (i + 1));
            pages.get(i).forEach(System.out::println);
            System.out.println();
        }
    }
}
