package assignment09;


import java.util.Collection;
import java.util.LinkedList;

/**
 * A hash table that implements separate chaining.
 * @author Pranav Rajan
 * version March 30, 2018
 *
 */
public class ChainingHashTable implements Set<String> {

  /** The array that backs the hash table */
  private LinkedList<String>[] storage;

  /** The number of elements in the hash table */
  private int size;

  /** Representation of a hashing function */
  HashFunctor functor;

  /**
   * The load factor. The load factor represents the load value threshold and indicates when the
   * hash table should be rehashed and doubled in size.
   */
  private static final double loadFactor = 0.75;

  /** Variable that keeps track of the number of collisions that have occurred */
  private int numCollisions;

  /** Variable that keeps track of the number of times the hash table has been rehashed */
  private int numTimesRehashed;

  /**
   * Constructs a hash table of the given capacity that uses the hashing function specified by the
   * given functor.
   */
  @SuppressWarnings("unchecked")
  public ChainingHashTable(int capacity, HashFunctor functor) {

    //check if the capacity is less than 0 
    if (capacity < 0) {
      throw new IllegalArgumentException();
    }
    
    //initialize the array that backs the hash table
    storage = (LinkedList<String>[]) new LinkedList[primeNumberGenerator(Math.abs(capacity))];

    //initialize the hash function
    this.functor = functor;

    //initialize the size of the table
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

    //hash the string
    int hashValue = Math.abs(functor.hash(item));

    //compute the index
    int index = hashValue % storage.length;

    //check if the string is already in the set
    if (this.contains(item)) {
      return false;
    }
    //keep track of the number of collisions
    //for separate chaining the number of collisions is measured by
    //how far you have to go into the linked list to find the spot where to insert the element

    if (storage[index].size() > 0) {
      numCollisions = numCollisions + storage[index].size();
    }

    //add the item to the linked list
    storage[index].add(item);

    //increment the size
    size++;
    

    //determine if we have to rehash the table
    //check if the hash table is filled halfway or more with the lambda threshold
    //If it is then we have to expand the hash table and rehash all of its values
    //have to convert the size and hashTable length to double
    //to prevent runtime error where instead of doing double division the method does integer division
    if (((double) size / (double) storage.length) >= loadFactor) {
      this.rehash();
    }
    
    return true;
  }

  @SuppressWarnings("unchecked")
  private void rehash() {

    //create a temporary array to store all the values that are currently in the hash table
    LinkedList<String>[] tempStorage = storage;

    //double the size of the hashTable and find a prime value that is close to double the size
    storage = (LinkedList<String>[]) new LinkedList[primeNumberGenerator(storage.length * 2)];

    //reset the size to 0 for the new hash table because we will be using the add method to rehash all the values
    //which increments the size in the method each time a value is added to the hash table
    this.size = 0;

    //iterate through the temporary hash table and fill the new hash table
    for (int i = 0; i < tempStorage.length; i++) {

      //look up the linked list located in the specific index in the temporary hash table
      //after looking up the linked list add all the values that are in that linked list to the newly resized hash table
      //iterate through the temporary hash table and fill the new hash table
      //only add the lists that are not null to avoid null exceptions the same way we did for the QuadProbeHashTable
      if (tempStorage[i] != null) {
        for (String s : tempStorage[i]) {
          this.add(s);
        }
        numTimesRehashed++;
      }
    }
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

    //loop through all the elements in the set
    //If the set is modified return true otherwise return false

    if (items.size() == 0) {
      return false;
    }

    for (String s : items) {
      if (!this.add(s)) {
        setModified = false;
      }
    }
    return setModified;
  }

  /** Removes all items from this set. The set will be empty after this method call. */
  @SuppressWarnings("unchecked")
  @Override
  public void clear() {

    //reset the size to zero
    this.size = 0;

    //initialize a new hash table with a size that was the size of the hash table before clear was called
    storage = (LinkedList<String>[]) new LinkedList[7];

    //initialize the number of collisions that have occurred
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
    int hashValue = functor.hash(item);

    //compute the index
    int index = Math.abs(hashValue % storage.length);

    if (storage[index] == null) {
      storage[index] = new LinkedList<String>();
      return false;
    }
    
    return storage[index].contains(item);
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
    //loop through all the elements in the collection
    //If one of the elements in the collection is not in the hash table return false, otherwise return true

    //A collection of size zero is a subset of a set
    if (items.size() == 0) {
      return true;
    }
    
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
