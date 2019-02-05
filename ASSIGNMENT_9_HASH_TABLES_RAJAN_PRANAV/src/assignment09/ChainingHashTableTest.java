package assignment09;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

public class ChainingHashTableTest {

  @Test
  public void testAddOneElement() {
    ChainingHashTable hashTable = new ChainingHashTable(37, new BadHashFunctor());
    hashTable.add("Hello");
    hashTable.add("Rajan");
    hashTable.contains("Rajan");
    assertEquals(2, hashTable.size());
  }

  @Test
  public void testSomeElement() {
    ChainingHashTable hashTable = new ChainingHashTable(37, new BadHashFunctor());
    hashTable.add("Hello");
    assertEquals(1, hashTable.size());
  }

  @Test
  public void testContainsOneElement() {
    ChainingHashTable hashTable = new ChainingHashTable(37, new BadHashFunctor());
    hashTable.add("Hello");
    assertEquals(true, hashTable.contains("Hello"));
  }

  @Test
  public void testAddTwoElements() {
    ChainingHashTable hashTable = new ChainingHashTable(37, new BadHashFunctor());
    hashTable.add("Hello");
    hashTable.add("Rajasthan");
    assertEquals(2, hashTable.size());
    assertEquals(0, hashTable.getNumberOfCollisions());
    assertEquals(0, hashTable.getNumberOfRehashes());
  }

  @Test
  public void testAddTwoElementsOfTheSameSize() {
    ChainingHashTable hashTable = new ChainingHashTable(37, new BadHashFunctor());
    hashTable.add("Hello");
    hashTable.add("Delhi");
    assertEquals(2, hashTable.size());
    assertEquals(1, hashTable.getNumberOfCollisions());
    assertEquals(0, hashTable.getNumberOfRehashes());
  }

  @Test
  public void testContainsTwoElementsOfTheSameSize() {
    ChainingHashTable hashTable = new ChainingHashTable(37, new BadHashFunctor());
    hashTable.add("Hello");
    hashTable.add("Delhi");
    assertEquals(true, hashTable.contains("Hello"));
    assertEquals(true, hashTable.contains("Delhi"));
  }

  @Test
  public void testAddFourElements() {
    ChainingHashTable hashTable = new ChainingHashTable(37, new BadHashFunctor());
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
    ChainingHashTable hashTable = new ChainingHashTable(37, new BadHashFunctor());
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
    ChainingHashTable hashTable = new ChainingHashTable(37, new BadHashFunctor());
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
    assertEquals(153, hashTable.getNumberOfCollisions());
    assertEquals(0, hashTable.getNumberOfRehashes());
  }

  @Test
  public void testHashTableOfSizeOne() {
    ChainingHashTable hashTable = new ChainingHashTable(37, new BadHashFunctor());
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
    assertEquals(153, hashTable.getNumberOfCollisions());
    assertEquals(0, hashTable.getNumberOfRehashes());
  }

  @Test
  public void testAddAllWithBadHashFunctor() {
    ChainingHashTable hashTable = new ChainingHashTable(37, new BadHashFunctor());
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
    ChainingHashTable hashTable = new ChainingHashTable(37, new BadHashFunctor());
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
  }

  @Test
  public void testClearWithBadHashFunctor() {
    ChainingHashTable hashTable = new ChainingHashTable(37, new BadHashFunctor());
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
    ChainingHashTable hashTable = new ChainingHashTable(37, new BadHashFunctor());
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
    ChainingHashTable hashTable = new ChainingHashTable(37, new BadHashFunctor());
    assertEquals(true, hashTable.isEmpty());
  }

  //Tests hash tables with the GoodHashFunctor

  @Test
  public void testAddOneElementGoodHashFunctor() {
    ChainingHashTable hashTable = new ChainingHashTable(37, new GoodHashFunctor());
    hashTable.add("Hello");
    assertEquals(1, hashTable.size());
    assertEquals(0, hashTable.getNumberOfCollisions());
    assertEquals(0, hashTable.getNumberOfRehashes());
  }

  @Test
  public void testContainsOneElementGoodHashFunctor() {
    ChainingHashTable hashTable = new ChainingHashTable(37, new GoodHashFunctor());
    hashTable.add("Hello");
    assertEquals(true, hashTable.contains("Hello"));
  }

  @Test
  public void testAddTwoElementsGoodHashFunctor() {
    ChainingHashTable hashTable = new ChainingHashTable(37, new GoodHashFunctor());
    hashTable.add("Hello");
    hashTable.add("Rajasthan");
    assertEquals(2, hashTable.size());
    assertEquals(0, hashTable.getNumberOfCollisions());
    assertEquals(0, hashTable.getNumberOfRehashes());
  }

  @Test
  public void testContainsTwoElementsGoodHashFunctor() {
    ChainingHashTable hashTable = new ChainingHashTable(37, new GoodHashFunctor());
    hashTable.add("Hello");
    hashTable.add("Rajasthan");
    assertEquals(true, hashTable.contains("Hello"));
    assertEquals(true, hashTable.contains("Rajasthan"));
  }

  @Test
  public void testAddTwoElementsOfTheSameSizeGoodHashFunctor() {
    ChainingHashTable hashTable = new ChainingHashTable(37, new GoodHashFunctor());
    hashTable.add("Hello");
    hashTable.add("Delhi");
    assertEquals(2, hashTable.size());
    assertEquals(0, hashTable.getNumberOfCollisions());
    assertEquals(0, hashTable.getNumberOfRehashes());
  }

  @Test
  public void testContainsTwoElementsOfTheSameSizeGoodHashFunctor() {
    ChainingHashTable hashTable = new ChainingHashTable(37, new GoodHashFunctor());
    hashTable.add("Hello");
    hashTable.add("Delhi");
    assertEquals(true, hashTable.contains("Hello"));
    assertEquals(true, hashTable.contains("Delhi"));
  }

  @Test
  public void testAddFourElementsGoodHashFunctor() {
    ChainingHashTable hashTable = new ChainingHashTable(37, new GoodHashFunctor());
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
    ChainingHashTable hashTable = new ChainingHashTable(37, new GoodHashFunctor());
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
    ChainingHashTable hashTable = new ChainingHashTable(11, new GoodHashFunctor());
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
    assertEquals(6, hashTable.getNumberOfCollisions());
    assertEquals(14, hashTable.getNumberOfRehashes());
  }

  @Test
  public void testHashTableThatIsVeryLong() {
    ChainingHashTable hashTable = new ChainingHashTable(1001, new GoodHashFunctor());
    ArrayList<String> bigList = new ArrayList<String>();
    for (int i = 0; i < 500_000; i++) {
      bigList.add(i + "");
    }
    hashTable.addAll(bigList);
  }
}
