package assignment09;

import java.util.Arrays;
import java.util.Collection;

/**
 * A hash table that implements quadratic probing
 * @author Pranav Rajan
 * @author March 30, 2018
 *
 */
public class QuadProbeHashTable implements Set<String> {

  /** The array that will back the hash table */
  private String[] hashTable;

  /** The number of elements in the hash table */
  private int size;

  /** Representation of a hashing function */
  HashFunctor functor;

  /** Variable that keeps track of the number of collisions that have occurred */
  private int numCollisions;

  /**
   * The load factor. The load factor represents the load value threshold and indicates when the
   * hash table should be rehashed and doubled in size.
   */
  private static final double loadFactor = 0.5;

  /** Variable that keeps track of the number of times the hash table has been rehashed */
  private int numTimesRehashed;

  /**
   * Constructs a hash table of the given capacity that uses the hashing function specified by the
   * given functor.
   */
  public QuadProbeHashTable(int capacity, HashFunctor functor) {
    //Initialize the array that backs the hash table
    this.hashTable = new String[primeNumberGenerator(Math.abs(capacity))];

    //initialize the hash function
    this.functor = functor;

    //initialize the number of elements in the table
    this.size = 0;

    //initialize the number of collisions
    this.numCollisions = 0;

    //initialize the number of times the hash table has been rehashed
    this.numTimesRehashed = 0;
  }

  /**
   * Ensures that this set contains the specified item.
   *
   * @param item - the item whose presence is ensured in this set
   * @return true if this set changed as a result of this method call (that is, if the input item
   *     was actually inserted); otherwise, returns false
   */
  @Override
  public boolean add(String item) {

    //check if the string is already in the set
    if (this.contains(item)) {
      return false;
    }

    if (((double) size / (double) hashTable.length) >= loadFactor) {
      this.rehash();
    }

    //hash the string
    int hashValue = Math.abs(functor.hash(item));

    //compute the index
    //    System.out.println("The hash table length " + hashTable.length);
    //    System.out.println("The index as computed " + hashValue % hashTable.length);

    int index = hashValue % hashTable.length;

    //create a counter to keep track of the index for changing with quadratic probing
    int counter = 1;

    while (true) {
      if (hashTable[index] == null) {
        //insert the element into the particular index
        hashTable[index] = item;

        //increase the size
        size++;

        return true;
      }

      //increment
      index = (int) (hashValue + Math.pow(counter, 2));

      //increment the counter
      counter++;

      //increment the number of times collisions have occurred
      numCollisions++;

      //handle the wrap around once the index is greater than the size of the hash table
      if (index >= hashTable.length) {
        //        index = Math.abs(hashTable.length - index);
        index = index % hashTable.length;
      }

      //  //handle the wrap around once the index is greater than the size of the hash table
      if (index >= hashTable.length) {
        //        index = Math.abs(hashTable.length - index);
        index = index % hashTable.length;
      }
    }
  }

  /**
   * Method that doubles the size of the hash table and rehashes all of the values in hash table
   * once a load factor (lambda) greater than or equal to 0.5 has been reached
   */
  private void rehash() {

    //create a temporary array to store all the values that are currently in the hash table
    String[] tempHashTable = Arrays.copyOf(hashTable, hashTable.length);

    //double the size of the hashTable and find a prime value that is close to double the size
    hashTable = new String[primeNumberGenerator(hashTable.length * 2)];

    //reset the size to 0 for the new hash table because we will be using the add method to rehash all the values
    //which increments the size in the method each time a value is added to the hash table
    this.size = 0;

    //iterate through the temporary hash table and fill the new hash table
    //only add the values that are not null to avoid null exceptions
    for (int i = 0; i < tempHashTable.length; i++) {
      if (tempHashTable[i] != null) {
        this.add(tempHashTable[i]);
      }
    }

    //increment the number of times the hash table has been rehashed
    numTimesRehashed++;
  }

  /**
   * Ensures that this set contains all items in the specified collection.
   *
   * @param items - the collection of items whose presence is ensured in this set
   * @return true if this set changed as a result of this method call (that is, if any item in the
   *     input collection was actually inserted); otherwise, returns false
   */
  @Override
  public boolean addAll(Collection<? extends String> items) {

    //create a boolean flag to indicate whether there has been a change to the set
    boolean setModified = true;

    if (items.size() == 0) {
      return false;
    }

    //loop through all the elements in the set
    //If the set is modified return true otherwise return false
    for (String s : items) {
      if (!this.add(s)) {
        setModified = false;
      }
    }
    return setModified;
  }

  /** Removes all items from this set. The set will be empty after this method call. */
  @Override
  public void clear() {

    //set the size of the number of elements in the hash table to zero
    this.size = 0;

    //initialize the size of a new hash table with the size of the hash table before the clear method was called
    this.hashTable = new String[7];

    //initialize the number of collisions
    this.numCollisions = 0;

    //initialize the number of times the hash table has been rehashed
    this.numTimesRehashed = 0;
  }

  /**
   * Determines if there is an item in this set that is equal to the specified item.
   *
   * @param item - the item sought in this set
   * @return true if there is an item in this set that is equal to the input item; otherwise,
   *     returns false
   */
  @Override
  public boolean contains(String item) {

    //hash the string
    int hashValue = Math.abs(functor.hash(item));

    //compute the index
    int index = hashValue % hashTable.length;

    //use quadratic probing to find an empty bucket to insert the item into the hash table
    //start the loop at 1 and keep on looping by i^2 until we find an index that is empty

    //create a counter to keep track of the value for quadratic probing
    int counter = 1;

    while (true) {
      //if the index is null return false
      if (hashTable[index] == null) {
        return false;
      }

      //if the index is occupied and contains the element that we are looking for return true
      if (hashTable[index] != null && hashTable[index].equals(item)) {
        return true;
      }

      //if the above conditions are not satisfied use quadratic probing to find the index

      //variable to keep track of where we start
      int tempIndex = index;

      //if the bucket is not null and the element we are looking for is not found at the particular bucket
      //use quadratic probing to find where to find the element that we are looking for
      index = (int) ((hashValue + Math.pow(counter, 2))) % hashTable.length;

      //increment the counter
      counter++;

      //check to see whether we have searched the entire hash table for the item we are looking for
      if (index == tempIndex) {
        return false;
      }
    }
  }

  /**
   * Determines if for each item in the specified collection, there is an item in this set that is
   * equal to it.
   *
   * @param items - the collection of items sought in this set
   * @return true if for each item in the specified collection, there is an item in this set that is
   *     equal to it; otherwise, returns false
   */
  @Override
  public boolean containsAll(Collection<? extends String> items) {

    //if the collection is empty then return true because an empty set is a subset of a set
    if (items.size() == 0) {
      return true;
    }

    //loop through all the elements in the collection
    //If one of the elements in the collection is not in the hash table return false, otherwise return true
    for (String s : items) {
      if (!this.contains(s)) {
        return false;
      }
    }
    return true;
  }

  /** Returns true if this set contains no items. */
  @Override
  public boolean isEmpty() {
    if (this.size == 0) {
      return true;
    } else {
      return false;
    }
  }

  /** Returns the number of items in this set. */
  @Override
  public int size() {
    return this.size;
  }

  /**
   * Method that determines whether or not a number is prime
   *
   * @author Ryan Sargent and Pranav Rajan
   */
  private static boolean isPrime(int number) {

    //Check whether a number is divisible by two. If it is then return false
    if (number % 2 == 0) {
      return false;
    }

    //check all the other factors until you get to the square root of the number
    for (int factor = 3; factor <= Math.sqrt(number); factor += 2) {
      if (number % factor == 0) {
        return false;
      }
    }
    return true;
  }

  /** Method that generates a prime number */
  private static int primeNumberGenerator(int n) {

    //Some ideas about prime numbers

    /*Not all odd numbers are prime
     * ie. 9 is not a prime number because it can be decomposed into a multiple of primes 3 * 3
     * 2 is the only even prime number and all subsequent
     * prime numbers are odd
     * A list of the first few prime numbers are as follows:
     * 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47,  53,
     * E, O, O, O,  O,  O,  O,  O,  O,  O,  O,  O,  O,  O,  O,  O where E represents Even and O represents Odd
     */

    /*Steps to generate a prime numbers
     * 1) if n is even then convert it into an odd number since we know all numbers that are multiples of 2
     * except for 2 are composite numbers
     * 2) now that we have an odd number we need to check that we do not have a composite odd number such as 9
     * 3) generate odd numbers until we find an odd number that is prime
     * 4) return the odd number that we find is prime
     */

    if (n % 2 == 0) {
      n = n + 1;
    }
    while (!isPrime(n)) {
      n = n + 2;
    }
    return n;
  }

  /** A getter method that returns the number of collisions that have occurred */
  public int getNumberOfCollisions() {
    return this.numCollisions;
  }

  /** A getter method that returns the number of times the hash table has been rehashed */
  public int getNumberOfRehashes() {
    return this.numTimesRehashed;
  }
}
