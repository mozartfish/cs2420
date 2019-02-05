package assignment02;

import java.util.GregorianCalendar;

/**
 * A generic representation of a library book. Library books are defined by certain features
 * including a unique ISBN number, author, title, due date and library patron.
 *
 * @author Pranav Rajan
 * @version January 24, 2018
 */
public class LibraryBookGeneric<Type> extends Book {

  /** The type used to indicate a library patron */
  private Type holder;
  /** The Gregorian calendar used to indicate a due date */
  private GregorianCalendar dueDate;

  /**
   * Constructor with isbn, author, and title for new LibraryBook
   *
   * @param isbn a long used to initialize an isbn number for a LibraryBook
   * @param author a string used to initialize an author for a LibraryBook
   * @param title a string used to initialize a title for a LibraryBook
   */
  public LibraryBookGeneric(long isbn, String author, String title) {
    super(isbn, author, title);
    holder = null;
    dueDate = null;
  }

  /**
   * A method that returns the identification of the library patron who checks out a book
   *
   * @return the name of the patron who checked out the LibraryBook
   */
  public Type getHolder() {
    return holder;
  }

  /**
   * A method that returns the due date for a LibraryBook
   *
   * @return the due date for the LibraryBook
   */
  public GregorianCalendar getDueDate() {
    return dueDate;
  }

  /** A method that checks in a LibraryBook */
  public void checkIn() {
    holder = null;
    dueDate = null;
  }

  /**
   * A method that checks out a LibraryBook to a patron
   *
   * @param patron a string that represents a patron that is assigned to holder
   * @param year an int that represents the year the LibraryBook was checked out that is assigned to
   *     dueDate
   * @param month an int that represents the month the LibraryBook was checked out that is assigned
   *     to dueDate
   * @param dayOfMonth an int that represents the day the LibraryBook was checked out that is
   *     assigned to dueDate
   */
  public void checkOut(Type patron, int year, int month, int dayOfMonth) {
    holder = patron;
    dueDate = new GregorianCalendar(year, month, dayOfMonth);
  }
}
