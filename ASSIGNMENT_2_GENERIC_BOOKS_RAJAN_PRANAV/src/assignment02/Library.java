package assignment02;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class representation of a library (a collection of library books). A library can check in
 * LibraryBooks, check out LibraryBooks, return the holder of a specific LibraryBook, and return a
 * list of books checked out to a holder
 *
 * @author Dr. Miriah Meyer, Ryan Sargent, Pranav Rajan
 * @version January 24, 2018
 */
public class Library {

  private ArrayList<LibraryBook> library;

  public Library() {
    library = new ArrayList<LibraryBook>();
  }

  /**
   * Add the specified book to the library, assume no duplicates.
   *
   * @param isbn -- ISBN of the book to be added
   * @param author -- author of the book to be added
   * @param title -- title of the book to be added
   */
  public void add(long isbn, String author, String title) {
    library.add(new LibraryBook(isbn, author, title));
  }

  /**
   * Add the list of library books to the library, assume no duplicates.
   *
   * @param list -- list of library books to be added
   */
  public void addAll(ArrayList<LibraryBook> list) {

    library.addAll(list);
  }

  /**
   * Add books specified by the input file. One book per line with ISBN, author, and title separated
   * by tabs.
   *
   * <p>If file does not exist or format is violated, do nothing.
   */
  public void addAll(String filename) {
    ArrayList<LibraryBook> toBeAdded = new ArrayList<LibraryBook>();

    try (Scanner fileIn = new Scanner(new File(filename))) {

      int lineNum = 1;

      while (fileIn.hasNextLine()) {
        String line = fileIn.nextLine();

        try (Scanner lineIn = new Scanner(line)) {
          lineIn.useDelimiter("\\t");

          if (!lineIn.hasNextLong()) {
            throw new ParseException("ISBN", lineNum);
          }
          long isbn = lineIn.nextLong();

          if (!lineIn.hasNext()) {
            throw new ParseException("Author", lineNum);
          }
          String author = lineIn.next();

          if (!lineIn.hasNext()) {
            throw new ParseException("Title", lineNum);
          }
          String title = lineIn.next();
          toBeAdded.add(new LibraryBook(isbn, author, title));
        }
        lineNum++;
      }
    } catch (FileNotFoundException e) {
      System.err.println(e.getMessage() + " Nothing added to the library.");
      return;
    } catch (ParseException e) {
      System.err.println(
          e.getLocalizedMessage()
              + " formatted incorrectly at line "
              + e.getErrorOffset()
              + ". Nothing added to the library.");
      return;
    }

    library.addAll(toBeAdded);
  }

  /**
   * Returns the holder of the library book with the specified ISBN.
   *
   * <p>If no book with the specified ISBN is in the library, returns null.
   *
   * @param isbn -- ISBN of the book to be looked up
   */
  public String lookup(long isbn) {
    for (LibraryBook b : library) {
      if (b.getIsbn() == isbn) {
        return b.getHolder();
      }
    }
    return null;
  }

  /**
   * Returns the list of library books checked out to the specified holder.
   *
   * <p>If the specified holder has no books checked out, returns an empty list.
   *
   * @param holder -- holder whose checked out books are returned
   */
  public ArrayList<LibraryBook> lookup(String holder) {
    ArrayList<LibraryBook> checkedOutBooks = new ArrayList<>();
    for (LibraryBook b : library) {
      if (b.getHolder() != null) {
        if (b.getHolder().equals(holder)) {
          checkedOutBooks.add(b);
        }
      }
    }
    return checkedOutBooks;
  }

  /**
   * Sets the holder and due date of the library book with the specified ISBN.
   *
   * <p>If no book with the specified ISBN is in the library, returns false.
   *
   * <p>If the book with the specified ISBN is already checked out, returns false.
   *
   * <p>Otherwise, returns true.
   *
   * @param isbn -- ISBN of the library book to be checked out
   * @param holder -- new holder of the library book
   * @param month -- month of the new due date of the library book
   * @param day -- day of the new due date of the library book
   * @param year -- year of the new due date of the library book
   */
  public boolean checkout(long isbn, String holder, int month, int day, int year) {
    for (LibraryBook b : library) {
      if (b.getIsbn() == isbn) {
        if (b.getHolder() == null) {
          b.checkOut(holder, year, month, day);
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Unsets the holder and due date of the library book.
   *
   * <p>If no book with the specified ISBN is in the library, returns false.
   *
   * <p>If the book with the specified ISBN is already checked in, returns false.
   *
   * <p>Otherwise, returns true.
   *
   * @param isbn -- ISBN of the library book to be checked in
   */
  public boolean checkin(long isbn) {
    for (LibraryBook b : library) {
      if (b.getIsbn() == isbn) {
        if (b.getHolder() != null) {
          b.checkIn();
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Unsets the holder and due date for all library books checked out be the specified holder.
   *
   * <p>If no books with the specified holder are in the library, returns false;
   *
   * <p>Otherwise, returns true.
   *
   * @param holder -- holder of the library books to be checked in
   */
  public boolean checkin(String holder) {
    ArrayList<LibraryBook> checkInBooks = lookup(holder);
    if (checkInBooks.size() == 0) {
      return false;
    } else {
      for (LibraryBook b : checkInBooks) {
        b.checkIn();
      }
      return true;
    }
  }
}
