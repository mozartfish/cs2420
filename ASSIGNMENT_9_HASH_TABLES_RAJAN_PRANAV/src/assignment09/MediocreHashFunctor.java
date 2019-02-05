package assignment09;

public class MediocreHashFunctor implements HashFunctor {

  /**
   * A functor (aka function object) is a function embedded in an object, so that it may be passed
   * as a parameter to another function.
   *
   * <p>This is a mediocre hash function because even though strings can have the same length they
   * can have different ASCII character sums which in makes them unique. This hash function computes
   * the ASCII sum of a unique string and then takes the average in attempt to get a well
   * distributed hash table
   */
  @Override
  public int hash(String item) {
    //variable that keeps track of the total sum
    int sum = 0;

    //variable that keeps track of the average of the ASCII characters in a unique string
    int average = 0;

    //iterate through all the characters in item and compute the sum of all the characters in item
    for (int i = 0; i < item.length(); i++) {
      sum = sum + (int) item.charAt(i);
    }

    //take the average of sum
    average = sum / item.length();

    return average;
  }
}
