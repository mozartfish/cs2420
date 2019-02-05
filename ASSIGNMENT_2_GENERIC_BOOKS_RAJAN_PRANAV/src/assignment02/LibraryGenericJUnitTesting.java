package assignment02;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

/**
 * A set of JUnit tests that test the LibraryGeneric Class and its methods
 *
 * @author Pranav Rajan
 * @version January 24, 2018
 */
public class LibraryGenericJUnitTesting {
  /** Initialize some Libraries */
  LibraryGeneric<String> emptyString;

  LibraryGeneric<PhoneNumber> emptyPhone;
  LibraryGeneric<String> checkedInString;
  LibraryGeneric<PhoneNumber> checkedInPhone;
  LibraryGeneric<String> OneString;
  LibraryGeneric<PhoneNumber> OnePhone;
  LibraryGeneric<String> mediumString;
  LibraryGeneric<PhoneNumber> mediumPhone;

  @Before
  public void setUp() {
    //set up libraries
    emptyString = new LibraryGeneric<>();
    emptyPhone = new LibraryGeneric<>();
    checkedInString = new LibraryGeneric<>();
    checkedInPhone = new LibraryGeneric<>();
    OneString = new LibraryGeneric<>();
    OnePhone = new LibraryGeneric<>();
    mediumString = new LibraryGeneric<>();
    mediumPhone = new LibraryGeneric<>();

    //Create some phone numbers
    PhoneNumber num1 = new PhoneNumber("650.565.8105");
    PhoneNumber num2 = new PhoneNumber("801.320.1776");
    PhoneNumber num3 = new PhoneNumber("001.022.2018");
    PhoneNumber num4 = new PhoneNumber("004.011.1999");
    PhoneNumber num5 = new PhoneNumber("011.022.1963");
    PhoneNumber num6 = new PhoneNumber("002.003.1986");

    //add books to libraries
    checkedInString.add(9781843190004l, "Moyra Caldecott", "Weapons of the Wolfhound");
    checkedInPhone.add(9781843190004l, "Moyra Caldecott", "Weapons of the Wolfhound");
    OneString.add(9781843190004l, "Moyra Caldecott", "Weapons of the Wolfhound");
    OnePhone.add(9781843190004l, "Moyra Caldecott", "Weapons of the Wolfhound");
    mediumString.addAll("Mushroom_Publishing.txt");
    mediumPhone.addAll("Mushroom_Publishing.txt");

    //Check out books

    //check out one book
    OneString.checkout(9781843190004l, "Pranav Rajan", 01, 22, 2018);
    OnePhone.checkout(9781843190004l, num1, 01, 22, 2018);

    //check out twenty three books with type String Object
    mediumString.checkout(9781843190004l, "Sundar Rajan", 7, 04, 1776);
    mediumString.checkout(9781843190011l, "Ishaan Rajan", 7, 11, 2001);
    mediumString.checkout(9781843190028l, "Lorenzo(The Big Doggy)", 2, 03, 1986);
    mediumString.checkout(9781843190042l, "Gavin Tieng", 9, 04, 1998);
    mediumString.checkout(9781843190073l, "Nandini Rajan", 2, 15, 2004);
    mediumString.checkout(9781843190110l, "Pranav Rajan", 07, 18, 1968);
    mediumString.checkout(9781843190349l, "Pranav Rajan", 01, 25, 2018);
    mediumString.checkout(9781843190363l, "Pranav Rajan", 01, 22, 2018);
    mediumString.checkout(9781843190394l, "Pranav Rajan", 01, 20, 2018);
    mediumString.checkout(9781843190400l, "Pranav Rajan", 01, 16, 2018);
    mediumString.checkout(9781843190479l, "Pranav Rajan", 01, 13, 2018);
    mediumString.checkout(9781843190516l, "Pranav Rajan", 01, 15, 2018);
    mediumString.checkout(9781843190677l, "Pranav Rajan", 01, 12, 2018);
    mediumString.checkout(9781843190769l, "Pranav Rajan", 01, 31, 2018);
    mediumString.checkout(9781843190875l, "Pranav Rajan", 01, 30, 2018);
    mediumString.checkout(9781843190936l, "Pranav Rajan", 01, 29, 2018);
    mediumString.checkout(9781843190998l, "Pranav Rajan", 01, 28, 2018);
    mediumString.checkout(9781843191230l, "Pranav Rajan", 01, 27, 2018);
    mediumString.checkout(9781843192022l, "Pranav Rajan", 01, 26, 2018);
    mediumString.checkout(9781843192039l, "Pranav Rajan", 01, 25, 2018);
    mediumString.checkout(9781843192701l, "Pranav Rajan", 01, 24, 2018);
    mediumString.checkout(9781843192954l, "Pranav Rajan", 01, 23, 2018);
    mediumString.checkout(9781843193319l, null, 01, 22, 2018);

    //check out twenty three books with type Phone Number Object
    mediumPhone.checkout(9781843190004l, num2, 01, 21, 2018);
    mediumPhone.checkout(9781843190011l, num3, 01, 21, 2018);
    mediumPhone.checkout(9781843190028l, num4, 01, 21, 2018);
    mediumPhone.checkout(9781843190042l, num5, 01, 21, 2018);
    mediumPhone.checkout(9781843190073l, num6, 01, 21, 2018);
    mediumPhone.checkout(9781843190110l, num1, 01, 21, 2018);
    mediumPhone.checkout(9781843190349l, num1, 01, 21, 2018);
    mediumPhone.checkout(9781843190363l, num1, 01, 21, 2018);
    mediumPhone.checkout(9781843190394l, num1, 01, 21, 2018);
    mediumPhone.checkout(9781843190400l, num1, 01, 21, 2018);
    mediumPhone.checkout(9781843190479l, num1, 01, 21, 2018);
    mediumPhone.checkout(9781843190516l, num1, 01, 21, 2018);
    mediumPhone.checkout(9781843190677l, num1, 01, 21, 2018);
    mediumPhone.checkout(9781843190769l, num1, 01, 21, 2018);
    mediumPhone.checkout(9781843190875l, num1, 01, 21, 2018);
    mediumPhone.checkout(9781843190936l, num1, 01, 21, 2018);
    mediumPhone.checkout(9781843190998l, num1, 01, 21, 2018);
    mediumPhone.checkout(9781843191230l, num1, 01, 21, 2018);
    mediumPhone.checkout(9781843192022l, num1, 01, 21, 2018);
    mediumPhone.checkout(9781843192039l, num1, 01, 21, 2018);
    mediumPhone.checkout(9781843192701l, num1, 01, 21, 2018);
    mediumPhone.checkout(9781843192954l, num1, 01, 21, 2018);
    mediumPhone.checkout(9781843193319l, null, 01, 21, 2018);
  }

  //tests on an empty library
  @Test
  public void emptyStringlookupISBN() {
    assertEquals(null, emptyString.lookup(9781843190004l));
  }

  @Test
  public void emptyPhonelookupISBN() {
    assertEquals(null, emptyPhone.lookup(9781843190004l));
  }

  @Test
  public void emptyStringlookupHolder() {
    ArrayList<LibraryBookGeneric<String>> checkedOutBooks = emptyString.lookup("Pranav Rajan");
    assertEquals(0, checkedOutBooks.size());
  }

  @Test
  public void emptyPhonelookupHolder() {
    PhoneNumber num1 = new PhoneNumber("650.565.8105");
    ArrayList<LibraryBookGeneric<PhoneNumber>> checkedOutBooks = emptyPhone.lookup(num1);
    assertEquals(0, checkedOutBooks.size());
  }

  @Test
  public void emptyStringcheckout() {
    assertEquals(false, emptyString.checkout(9781843190004l, "Pranav Rajan", 01, 22, 2018));
  }

  @Test
  public void emptyPhonecheckout() {
    PhoneNumber num1 = new PhoneNumber("650.565.8105");
    assertEquals(false, emptyPhone.checkout(9781843190004l, num1, 01, 22, 2018));
  }

  @Test
  public void emptyStringcheckinISBN() {
    assertEquals(false, emptyString.checkin(9781843190004l));
  }

  @Test
  public void emptyPhonecheckinISBN() {
    assertEquals(false, emptyPhone.checkin(9781843190004l));
  }

  @Test
  public void emptyStringcheckinHolder() {
    assertEquals(false, emptyString.checkin("Pranav Rajan"));
  }

  @Test
  public void emptyPhonecheckinHolder() {
    PhoneNumber num1 = new PhoneNumber("650.565.8105");
    assertEquals(false, emptyPhone.checkin(num1));
  }

  //tests on a library with one book checked in
  @Test
  public void checkedInStringlookupISBN() {
    assertEquals(null, checkedInString.lookup(9781843190004l));
  }

  @Test
  public void checkedInPhonelookupISBN() {
    assertEquals(null, checkedInPhone.lookup(9781843190004l));
  }

  @Test
  public void checkedInStringlookupHolder() {
    ArrayList<LibraryBookGeneric<String>> checkedOutBooks = checkedInString.lookup("Pranav Rajan");
    assertEquals(0, checkedOutBooks.size());
  }

  @Test
  public void checkedInPhonelookupHolder() {
    PhoneNumber num1 = new PhoneNumber("650.565.8105");
    ArrayList<LibraryBookGeneric<PhoneNumber>> checkedOutBooks = checkedInPhone.lookup(num1);
    assertEquals(0, checkedOutBooks.size());
  }

  @Test
  public void checkedInStringcheckout() {
    assertEquals(true, checkedInString.checkout(9781843190004l, "Pranav Rajan", 01, 22, 2018));
  }

  @Test
  public void checkedInPhone() {
    PhoneNumber num1 = new PhoneNumber("650.565.8105");
    assertEquals(true, checkedInPhone.checkout(9781843190004l, num1, 01, 22, 2018));
  }

  @Test
  public void checkedInStringcheckinISBN() {
    assertEquals(false, checkedInString.checkin(9781843190004l));
  }

  @Test
  public void checkedInPhonecheckinISBN() {
    assertEquals(false, checkedInPhone.checkin(9781843190004l));
  }

  @Test
  public void checkedInStringcheckinHolder() {
    assertEquals(false, checkedInString.checkin("Pranav Rajan"));
  }

  @Test
  public void checkedInPhoneStringcheckinHolder() {
    PhoneNumber num1 = new PhoneNumber("650.565.8105");
    assertEquals(false, checkedInPhone.checkin(num1));
  }

  //tests with one book checked out
  @Test
  public void oneStringLookupISBN() {
    assertEquals("Pranav Rajan", OneString.lookup(9781843190004l));
  }

  @Test
  public void onePhoneLookupISBN() {
    PhoneNumber num1 = new PhoneNumber("650.565.8105");
    assertEquals(num1, OnePhone.lookup(9781843190004l));
  }

  @Test
  public void oneStringlookupHolder() {
    ArrayList<LibraryBookGeneric<String>> checkedOutBooks = OneString.lookup("Pranav Rajan");
    assertEquals(1, checkedOutBooks.size());
  }

  @Test
  public void onePhonelookupHolder() {
    PhoneNumber num1 = new PhoneNumber("650.565.8105");
    ArrayList<LibraryBookGeneric<PhoneNumber>> checkedOutBooks = OnePhone.lookup(num1);
    assertEquals(1, checkedOutBooks.size());
  }

  @Test
  public void oneStringcheckout() {
    assertEquals(false, OneString.checkout(9781843190004l, "Pranav Rajan", 01, 22, 2018));
  }

  @Test
  public void onePhonecheckout() {
    PhoneNumber num1 = new PhoneNumber("650.565.8105");
    assertEquals(false, OnePhone.checkout(9781843190004l, num1, 01, 22, 2018));
  }

  @Test
  public void oneStringcheckinISBN() {
    assertEquals(true, OneString.checkin(9781843190004l));
  }

  @Test
  public void onePhonecheckinISBN() {
    assertEquals(true, OnePhone.checkin(9781843190004l));
  }

  @Test
  public void oneStringcheckinHolder() {
    assertEquals(true, OneString.checkin("Pranav Rajan"));
  }

  @Test
  public void onePhonecheckinHolder() {
    PhoneNumber num1 = new PhoneNumber("650.565.8105");
    assertEquals(true, OnePhone.checkin(num1));
  }

  // tests on a medium size library

  @Test
  public void mediumStringlookupISBN() {
    assertEquals("Sundar Rajan", mediumString.lookup(9781843190004l));
  }

  @Test
  public void mediumPhonelookupISBN() {
    PhoneNumber num2 = new PhoneNumber("801.320.1776");
    assertEquals(num2, mediumPhone.lookup(9781843190004l));
  }

  @Test
  public void mediumStringlookupHolder() {
    ArrayList<LibraryBookGeneric<String>> checkedOutBooks = mediumString.lookup("Pranav Rajan");
    assertEquals(17, checkedOutBooks.size());
  }

  @Test
  public void mediumPhonelookupHolder() {
    PhoneNumber num1 = new PhoneNumber("650.565.8105");
    ArrayList<LibraryBookGeneric<PhoneNumber>> checkedOutBooks = mediumPhone.lookup(num1);
    assertEquals(17, checkedOutBooks.size());
  }

  @Test
  public void mediumStringcheckout() {
    assertEquals(false, mediumString.checkout(9781843190004l, "Pranav Rajan", 01, 22, 2018));
  }

  @Test
  public void mediumPhonecheckout() {
    PhoneNumber num1 = new PhoneNumber("650.565.8105");
    assertEquals(false, mediumPhone.checkout(9781843190004l, num1, 01, 22, 2018));
  }

  @Test
  public void mediumStringcheckinISBN() {
    assertEquals(false, mediumString.checkin(9781843193319l));
  }

  @Test
  public void mediumPhoneinISBN() {
    assertEquals(false, mediumPhone.checkin(9781843193319l));
  }

  @Test
  public void mediumStringcheckinHolder() {
    assertEquals(true, mediumString.checkin("Pranav Rajan"));
    assertEquals(true, mediumString.checkin("Sundar Rajan"));
    assertEquals(false, mediumString.checkin("Chuck Norris"));
  }

  @Test
  public void mediumPhonecheckinHolder() {
    PhoneNumber num1 = new PhoneNumber("650.565.8105");
    PhoneNumber num2 = new PhoneNumber("801.320.1776");
    PhoneNumber num3 = new PhoneNumber("801.038.6784");
    assertEquals(true, mediumPhone.checkin(num1));
    assertEquals(true, mediumPhone.checkin(num2));
    assertEquals(false, mediumPhone.checkin(num3));
  }

  @Test
  public void getOverdueList() {
    System.out.println(mediumString.getOverdueList(01, 01, 2018));
  }

  @Test
  public void sortByAuthor() {
    System.out.println(mediumString.getOrderedByAuthor());
  }
}
