package assignment02;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

/**
 * A set of JUnit tests that test the Library Class and its methods
 *
 * @author Pranav Rajan
 * @version January 24, 2018
 */
public class LibraryJUnitTesting {

  /** Initialize some libraries */
  Library empty;

  Library checkedIn;
  Library one;
  Library medium;

  @Before
  public void setUp() {
    //set up libraries
    empty = new Library();
    checkedIn = new Library();
    one = new Library();
    medium = new Library();

    // add books to libraries
    checkedIn.add(9781843190004l, "Moyra Caldecott", "Weapons of the Wolfhound");
    one.add(9781843190004l, "Moyra Caldecott", "Weapons of the Wolfhound");
    medium.addAll("Mushroom_Publishing.txt");

    //check books out
    one.checkout(9781843190004l, "Pranav Rajan", 01, 22, 2018);
    medium.checkout(9781843190004l, "Sundar Rajan", 01, 21, 2018);
    medium.checkout(9781843190011l, "Ishaan Rajan", 01, 21, 2018);
    medium.checkout(9781843190028l, "Lorenzo(The Big Doggy)", 01, 21, 2018);
    medium.checkout(9781843190042l, "Gavin Tieng", 01, 21, 2018);
    medium.checkout(9781843190073l, "Nandini Rajan", 01, 21, 2018);
    medium.checkout(9781843190110l, "Pranav Rajan", 01, 21, 2018);
    medium.checkout(9781843190349l, "Pranav Rajan", 01, 21, 2018);
    medium.checkout(9781843190363l, "Pranav Rajan", 01, 21, 2018);
    medium.checkout(9781843190394l, "Pranav Rajan", 01, 21, 2018);
    medium.checkout(9781843190400l, "Pranav Rajan", 01, 21, 2018);
    medium.checkout(9781843190479l, "Pranav Rajan", 01, 21, 2018);
    medium.checkout(9781843190516l, "Pranav Rajan", 01, 21, 2018);
    medium.checkout(9781843190677l, "Pranav Rajan", 01, 21, 2018);
    medium.checkout(9781843190769l, "Pranav Rajan", 01, 21, 2018);
    medium.checkout(9781843190875l, "Pranav Rajan", 01, 21, 2018);
    medium.checkout(9781843190936l, "Pranav Rajan", 01, 21, 2018);
    medium.checkout(9781843190998l, "Pranav Rajan", 01, 21, 2018);
    medium.checkout(9781843191230l, "Pranav Rajan", 01, 21, 2018);
    medium.checkout(9781843192022l, "Pranav Rajan", 01, 21, 2018);
    medium.checkout(9781843192039l, "Pranav Rajan", 01, 21, 2018);
    medium.checkout(9781843192701l, "Pranav Rajan", 01, 21, 2018);
    medium.checkout(9781843192954l, "Pranav Rajan", 01, 21, 2018);
    medium.checkout(9781843193319l, null, 01, 21, 2018);
  }

  //tests on an empty library
  @Test
  public void emptylookupISBN() {
    assertEquals(null, empty.lookup(9781843190004l));
  }

  @Test
  public void emptylookupHolder() {
    ArrayList<LibraryBook> checkedOutBooks = empty.lookup("Pranav Rajan");
    assertEquals(0, checkedOutBooks.size());
  }

  @Test
  public void emptycheckout() {
    assertEquals(false, empty.checkout(9781843190004l, "Pranav Rajan", 01, 22, 2018));
  }

  @Test
  public void emptycheckinISBN() {
    assertEquals(false, empty.checkin(9781843190004l));
  }

  @Test
  public void emptycheckinHolder() {
    assertEquals(false, empty.checkin("Pranav Rajan"));
  }

  //tests on a library with one book checked in

  @Test
  public void checkedInlookupISBN() {
    assertEquals(null, checkedIn.lookup(9781843190004l));
  }

  @Test
  public void checkedInlookupHolder() {
    ArrayList<LibraryBook> checkedOutBooks = checkedIn.lookup("Pranav Rajan");
    assertEquals(0, checkedOutBooks.size());
  }

  @Test
  public void checkedIncheckout() {
    assertEquals(true, checkedIn.checkout(9781843190004l, "Pranav Rajan", 01, 22, 2018));
  }

  @Test
  public void checkedIncheckinISBN() {
    assertEquals(false, checkedIn.checkin(9781843190004l));
  }

  @Test
  public void checkedIncheckinHolder() {
    assertEquals(false, checkedIn.checkin("Pranav Rajan"));
  }

  //tests with one book checked out
  @Test
  public void onelookupISBN() {
    assertEquals("Pranav Rajan", one.lookup(9781843190004l));
  }

  @Test
  public void onelookupHolder() {
    ArrayList<LibraryBook> checkedOutBooks = one.lookup("Pranav Rajan");
    assertEquals(1, checkedOutBooks.size());
  }

  @Test
  public void onecheckout() {
    assertEquals(false, one.checkout(9781843190004l, "Pranav Rajan", 01, 22, 2018));
  }

  @Test
  public void onecheckinISBN() {
    assertEquals(true, one.checkin(9781843190004l));
  }

  @Test
  public void onecheckinHolder() {
    assertEquals(true, one.checkin("Pranav Rajan"));
  }

  // tests on medium size library

  @Test
  public void mediumlookupISBN() {
    assertEquals("Sundar Rajan", medium.lookup(9781843190004l));
  }

  @Test
  public void mediumlookupHolder() {
    ArrayList<LibraryBook> checkedOutBooks = medium.lookup("Pranav Rajan");
    assertEquals(17, checkedOutBooks.size());
  }

  @Test
  public void mediumcheckout() {
    assertEquals(false, medium.checkout(9781843190004l, "Pranav Rajan", 01, 22, 2018));
  }

  @Test
  public void mediumcheckinISBN() {
    assertEquals(false, medium.checkin(9781843193319l));
  }

  @Test
  public void mediumcheckinHolder() {
    assertEquals(true, medium.checkin("Pranav Rajan"));
    assertEquals(true, medium.checkin("Sundar Rajan"));
    assertEquals(false, medium.checkin("Chuck Norris"));
  }
}
