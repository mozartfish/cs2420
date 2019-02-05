package assignment02;

/**
 * Class representation of a book. The ISBN, author, and title can never change once the book is
 * created.
 *
 * <p>Note that ISBNs are unique.
 *
 * @author Dr. Miriah Meyer, Ryan Sargent, Pranav Rajan
 * @version January 24, 2018
 */
public class Book {
  /** The long used to represent the ISBN number of a book */
  private long isbn;

  /** The string used to represent the author of a book */
  private String author;

  /** The String used to represent the title of a book */
  private String title;

  /**
   * Constructor with ISBN, author and title of a Book object
   *
   * @param isbn a long used to initialize an isbn number for a LibraryBook
   * @param author a string used to initialize an author for a LibraryBook
   * @param title a string used to initialize a title for a LibraryBook
   */
  public Book(long isbn, String author, String title) {
    this.isbn = isbn;
    this.author = author;
    this.title = title;
  }

  /** @return the author */
  public String getAuthor() {

    return this.author;
  }

  /** @return the ISBN */
  public long getIsbn() {
    return this.isbn;
  }

  /** @return the title */
  public String getTitle() {

    return this.title;
  }

  /**
   * Two books are considered equal if they have the same ISBN, author, and title.
   *
   * @param other -- the object begin compared with "this"
   * @return true if "other" is a Book and is equal to "this", false otherwise
   */
  public boolean equals(Object other) {
    if (!(other instanceof Book)) {
      return false;
    }
    Book book = (Book) other;
    if (this.isbn == book.isbn
        && this.author.equals(book.author)
        && this.title.equals(book.title)) {
      return true;
    }
    return false;
  }

  /** Returns a string representation of the book. */
  public String toString() {

    return isbn + ", " + author + ", \"" + title + "\"";
  }

  @Override
  public int hashCode() {

    return (int) isbn + author.hashCode() + title.hashCode();
  }
}
