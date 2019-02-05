package assignment09;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

public class QuadProbeHashTableTest {

  @Test
  public void testSomeProperties() {
    QuadProbeHashTable hashTable = new QuadProbeHashTable(4, new BadHashFunctor());
    hashTable.add("Hello");
    hashTable.add("World");
    hashTable.add("Rajan");

    ArrayList<String> list = new ArrayList<String>();
    list.add("Hello");
    list.add("Brown");
    list.add("World");
    list.add("Rajan");

    assertEquals(false, hashTable.containsAll(list));
    assertEquals(3, hashTable.size());
    assertEquals(3, hashTable.getNumberOfCollisions());
    assertEquals(0, hashTable.getNumberOfRehashes());
  }

  //The following tests hash tables using the BadHashFunctor

  @Test
  public void testAddOneElement() {
    QuadProbeHashTable hashTable = new QuadProbeHashTable(37, new BadHashFunctor());
    hashTable.add("Hello");
    assertEquals(1, hashTable.size());
    assertEquals(0, hashTable.getNumberOfCollisions());
    assertEquals(0, hashTable.getNumberOfRehashes());
  }

  @Test
  public void testContainsOneElement() {
    QuadProbeHashTable hashTable = new QuadProbeHashTable(37, new BadHashFunctor());
    hashTable.add("Hello");
    assertEquals(true, hashTable.contains("Hello"));
  }

  @Test
  public void testAddTwoElements() {
    QuadProbeHashTable hashTable = new QuadProbeHashTable(37, new BadHashFunctor());
    hashTable.add("Hello");
    hashTable.add("Rajasthan");
    assertEquals(2, hashTable.size());
    assertEquals(0, hashTable.getNumberOfCollisions());
    assertEquals(0, hashTable.getNumberOfRehashes());
  }

  @Test
  public void testContainsTwoElements() {
    QuadProbeHashTable hashTable = new QuadProbeHashTable(37, new BadHashFunctor());
    hashTable.add("Hello");
    hashTable.add("Rajasthan");
    assertEquals(true, hashTable.contains("Hello"));
    assertEquals(true, hashTable.contains("Rajasthan"));
  }

  @Test
  public void testAddTwoElementsOfTheSameSize() {
    QuadProbeHashTable hashTable = new QuadProbeHashTable(37, new BadHashFunctor());
    hashTable.add("Hello");
    hashTable.add("Delhi");
    assertEquals(2, hashTable.size());
    assertEquals(1, hashTable.getNumberOfCollisions());
    assertEquals(0, hashTable.getNumberOfRehashes());
  }

  @Test
  public void testContainsTwoElementsOfTheSameSize() {
    QuadProbeHashTable hashTable = new QuadProbeHashTable(37, new BadHashFunctor());
    hashTable.add("Hello");
    hashTable.add("Delhi");
    assertEquals(true, hashTable.contains("Hello"));
    assertEquals(true, hashTable.contains("Delhi"));
  }

  @Test
  public void testAddFourElements() {
    QuadProbeHashTable hashTable = new QuadProbeHashTable(37, new BadHashFunctor());
    hashTable.add("Hello");
    hashTable.add("Prague");
    hashTable.add("Budapest");
    hashTable.add("Rajasthan");
    assertEquals(4, hashTable.size());
    assertEquals(0, hashTable.getNumberOfCollisions());
    assertEquals(0, hashTable.getNumberOfRehashes());
  }

  @Test
  public void testAddFourElementsOfTheSameSize() {
    QuadProbeHashTable hashTable = new QuadProbeHashTable(37, new BadHashFunctor());
    hashTable.add("Berlin");
    hashTable.add("Prague");
    hashTable.add("Mumbai");
    hashTable.add("Bombay");
    assertEquals(4, hashTable.size());
    assertEquals(6, hashTable.getNumberOfCollisions());
    assertEquals(0, hashTable.getNumberOfRehashes());
  }

  @Test
  public void testHashTableThatGetsRehashed() {
    QuadProbeHashTable hashTable = new QuadProbeHashTable(11, new BadHashFunctor());
    hashTable.add("Jazz");
    hashTable.add("Buzz");
    hashTable.add("Fuzz");
    hashTable.add("Fizz");
    hashTable.add("Hajj");
    hashTable.add("Juju");
    hashTable.add("Quiz");
    hashTable.add("Razz");
    hashTable.add("Jeez");
    hashTable.add("Jeux");
    hashTable.add("Jinx");
    hashTable.add("Jock");
    hashTable.add("Jack");
    hashTable.add("Jump");
    hashTable.add("Jamb");
    hashTable.add("Juku");
    hashTable.add("Jivy");
    hashTable.add("Joky");

    assertEquals(18, hashTable.size());
    assertEquals(234, hashTable.getNumberOfCollisions());
    assertEquals(2, hashTable.getNumberOfRehashes());
  }

  @Test
  public void testHashTableOfSizeOne() {
    QuadProbeHashTable hashTable = new QuadProbeHashTable(1, new BadHashFunctor());
    hashTable.add("Jazz");
    hashTable.add("Buzz");
    hashTable.add("Fuzz");
    hashTable.add("Fizz");
    hashTable.add("Hajj");
    hashTable.add("Juju");
    hashTable.add("Quiz");
    hashTable.add("Razz");
    hashTable.add("Jeez");
    hashTable.add("Jeux");
    hashTable.add("Jinx");
    hashTable.add("Jock");
    hashTable.add("Jack");
    hashTable.add("Jump");
    hashTable.add("Jamb");
    hashTable.add("Juku");
    hashTable.add("Jivy");
    hashTable.add("Joky");

    assertEquals(18, hashTable.size());
    //      assertEquals(4, hashTable.getNumberOfCollisions());
    assertEquals(4, hashTable.getNumberOfRehashes());
  }

  @Test
  public void testAddAllWithBadHashFunctor() {
    QuadProbeHashTable hashTable = new QuadProbeHashTable(37, new BadHashFunctor());
    ArrayList<String> list = new ArrayList<String>();
    list.add("pazazz");
    list.add("pizazz");
    list.add("jazzbo");
    list.add("bezazz");
    list.add("jazzed");
    list.add("zizzle");
    list.add("jazzes");
    list.add("jazzer");
    list.add("muzjik");
    list.add("whizzy");
    list.add("mizzly");
    list.add("huzzah");
    list.add("mizzle");
    list.add("mezuza");
    list.add("guzzle");
    list.add("fezzed");
    list.add("hazzan");
    list.add("fezzes");
    list.add("wizzes");
    list.add("fizzes");
    list.add("cozzes");
    list.add("huzzas");
    list.add("pizzas");
    list.add("piazza");
    list.add("bijoux");

    assertEquals(true, hashTable.addAll(list));
  }

  @Test
  public void testAddAllWithDuplicatesAndBadHashFunctor() {
    QuadProbeHashTable hashTable = new QuadProbeHashTable(37, new BadHashFunctor());
    ArrayList<String> list = new ArrayList<String>();
    list.add("pazazz");
    list.add("pizazz");
    list.add("jazzbo");
    list.add("bezazz");
    list.add("jazzed");
    list.add("zizzle");
    list.add("jazzes");
    list.add("jazzer");
    list.add("muzjik");
    list.add("whizzy");
    list.add("bijoux");
    list.add("mizzly");
    list.add("huzzah");
    list.add("mizzle");
    list.add("mezuza");
    list.add("guzzle");
    list.add("fezzed");
    list.add("hazzan");
    list.add("fezzes");
    list.add("wizzes");
    list.add("fizzes");
    list.add("cozzes");
    list.add("huzzas");
    list.add("pizzas");
    list.add("piazza");
    list.add("bijoux");

    assertEquals(false, hashTable.addAll(list));
  }

  @Test
  public void testClearWithBadHashFunctor() {
    QuadProbeHashTable hashTable = new QuadProbeHashTable(37, new BadHashFunctor());
    ArrayList<String> list = new ArrayList<String>();
    list.add("pazazz");
    list.add("pizazz");
    list.add("jazzbo");
    list.add("bezazz");
    list.add("jazzed");
    list.add("zizzle");
    list.add("jazzes");
    list.add("jazzer");
    list.add("muzjik");
    list.add("whizzy");
    list.add("mizzly");
    list.add("huzzah");
    list.add("mizzle");
    list.add("mezuza");
    list.add("guzzle");
    list.add("fezzed");
    list.add("hazzan");
    list.add("fezzes");
    list.add("wizzes");
    list.add("fizzes");
    list.add("cozzes");
    list.add("huzzas");
    list.add("pizzas");
    list.add("piazza");
    list.add("bijoux");
    list.add("bijoux");

    assertEquals(false, hashTable.addAll(list));

    hashTable.clear();
    assertEquals(0, hashTable.size());
  }

  @Test
  public void testContainsAll() {
    QuadProbeHashTable hashTable = new QuadProbeHashTable(37, new BadHashFunctor());
    ArrayList<String> list = new ArrayList<String>();
    list.add("pazazz");
    list.add("pizazz");
    list.add("jazzbo");
    list.add("bezazz");
    list.add("jazzed");
    list.add("zizzle");
    list.add("jazzes");
    list.add("jazzer");
    list.add("muzjik");
    list.add("whizzy");
    list.add("mizzly");
    list.add("huzzah");
    list.add("mizzle");
    list.add("mezuza");
    list.add("guzzle");
    list.add("fezzed");
    list.add("hazzan");
    list.add("fezzes");
    list.add("wizzes");
    list.add("fizzes");
    list.add("cozzes");
    list.add("huzzas");
    list.add("pizzas");
    list.add("piazza");
    list.add("bijoux");

    hashTable.addAll(list);

    assertEquals(true, hashTable.containsAll(list));
  }

  @Test
  public void testIsEmpty() {
    QuadProbeHashTable hashTable = new QuadProbeHashTable(37, new BadHashFunctor());
    assertEquals(true, hashTable.isEmpty());
  }

  //Tests hash tables with the GoodHashFunctor

  @Test
  public void testAddOneElementGoodHashFunctor() {
    QuadProbeHashTable hashTable = new QuadProbeHashTable(37, new GoodHashFunctor());
    hashTable.add("Hello");
    assertEquals(1, hashTable.size());
    assertEquals(0, hashTable.getNumberOfCollisions());
    assertEquals(0, hashTable.getNumberOfRehashes());
  }

  @Test
  public void testContainsOneElementGoodHashFunctor() {
    QuadProbeHashTable hashTable = new QuadProbeHashTable(37, new GoodHashFunctor());
    hashTable.add("Hello");
    assertEquals(true, hashTable.contains("Hello"));
  }

  @Test
  public void testAddTwoElementsGoodHashFunctor() {
    QuadProbeHashTable hashTable = new QuadProbeHashTable(37, new GoodHashFunctor());
    hashTable.add("Hello");
    hashTable.add("Rajasthan");
    assertEquals(2, hashTable.size());
    assertEquals(0, hashTable.getNumberOfCollisions());
    assertEquals(0, hashTable.getNumberOfRehashes());
  }

  @Test
  public void testContainsTwoElementsGoodHashFunctor() {
    QuadProbeHashTable hashTable = new QuadProbeHashTable(37, new GoodHashFunctor());
    hashTable.add("Hello");
    hashTable.add("Rajasthan");
    assertEquals(true, hashTable.contains("Hello"));
    assertEquals(true, hashTable.contains("Rajasthan"));
  }

  @Test
  public void testAddTwoElementsOfTheSameSizeGoodHashFunctor() {
    QuadProbeHashTable hashTable = new QuadProbeHashTable(37, new GoodHashFunctor());
    hashTable.add("Hello");
    hashTable.add("Delhi");
    assertEquals(2, hashTable.size());
    assertEquals(0, hashTable.getNumberOfCollisions());
    assertEquals(0, hashTable.getNumberOfRehashes());
  }

  @Test
  public void testContainsTwoElementsOfTheSameSizeGoodHashFunctor() {
    QuadProbeHashTable hashTable = new QuadProbeHashTable(37, new GoodHashFunctor());
    hashTable.add("Hello");
    hashTable.add("Delhi");
    assertEquals(true, hashTable.contains("Hello"));
    assertEquals(true, hashTable.contains("Delhi"));
  }

  @Test
  public void testAddFourElementsGoodHashFunctor() {
    QuadProbeHashTable hashTable = new QuadProbeHashTable(37, new GoodHashFunctor());
    hashTable.add("Hello");
    hashTable.add("Prague");
    hashTable.add("Budapest");
    hashTable.add("Rajasthan");
    assertEquals(4, hashTable.size());
    assertEquals(0, hashTable.getNumberOfCollisions());
    assertEquals(0, hashTable.getNumberOfRehashes());
  }

  @Test
  public void testAddFourElementsOfTheSameSizeGoodHashFunctor() {
    QuadProbeHashTable hashTable = new QuadProbeHashTable(37, new GoodHashFunctor());
    hashTable.add("Berlin");
    hashTable.add("Prague");
    hashTable.add("Mumbai");
    hashTable.add("Bombay");
    assertEquals(4, hashTable.size());
    assertEquals(0, hashTable.getNumberOfCollisions());
    assertEquals(0, hashTable.getNumberOfRehashes());
  }

  @Test
  public void testHashTableThatGetsRehashedGoodHashFunctor() {
    QuadProbeHashTable hashTable = new QuadProbeHashTable(11, new GoodHashFunctor());
    hashTable.add("Jazz");
    hashTable.add("Buzz");
    hashTable.add("Fuzz");
    hashTable.add("Fizz");
    hashTable.add("Hajj");
    hashTable.add("Juju");
    hashTable.add("Quiz");
    hashTable.add("Razz");
    hashTable.add("Jeez");
    hashTable.add("Jeux");
    hashTable.add("Jinx");
    hashTable.add("Jock");
    hashTable.add("Jack");
    hashTable.add("Jump");
    hashTable.add("Jamb");
    hashTable.add("Juku");
    hashTable.add("Jivy");
    hashTable.add("Joky");

    assertEquals(18, hashTable.size());
    assertEquals(9, hashTable.getNumberOfCollisions());
    assertEquals(2, hashTable.getNumberOfRehashes());
  }

  @Test
  public void testHashTableThatIsVeryLong() {
    QuadProbeHashTable hashTable = new QuadProbeHashTable(1001, new GoodHashFunctor());
    ArrayList<String> bigList = new ArrayList<String>();
    for (int i = 0; i < 500_000; i++) {
      bigList.add(i + "");
    }
    hashTable.addAll(bigList);
  }
}
