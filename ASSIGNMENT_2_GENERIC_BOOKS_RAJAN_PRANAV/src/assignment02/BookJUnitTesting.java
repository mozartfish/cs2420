package assignment02;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * A set of tests to test the equals method in the Book class
 *
 * @author Pranav Rajan
 * @version January 24, 2018
 */
public class BookJUnitTesting {

  /** Initialize some new books */
  Book book1;
  Book book2;
  Book book3;
  Book book4;
  Book book5;

  @Before
  public void setUp() {
    book1 = new Book(123456789, "Rajan", "Hello");
    book2 = new Book(123456789, "Rajan", "Hello");
    book3 = new Book(223487802, "Rajan", "Hello");
    book4 = new Book(123456789, "Chuck Norris", "Hello");
    book5 = new Book(123456789, "Rajan", "Introduction to VHDL");
  }

  @Test
  public void testSameBooks() {
    assertEquals(true, book1.equals(book2));
  }

  @Test
  public void testDifferentISBN() {
    assertEquals(false, book1.equals(book3));
  }

  @Test
  public void testDifferentAuthors() {
    assertEquals(false, book1.equals(book4));
  }

  @Test
  public void testDifferentTitles() {
    assertEquals(false, book1.equals(book5));
  }
}
