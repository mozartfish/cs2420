package assignment09;

public class BadHashFunctor implements HashFunctor {

  /**
   * A functor (aka function object) is a function embedded in an object, so that it may be passed
   * as a parameter to another function.
   *
   * <p>This is a terrible hash function. The reason is because there are many strings that can have
   * the same length and the items entered into the hash table will not be evenly distributed.
   */
  @Override
  public int hash(String item) {
    return item.length();
  }
}
