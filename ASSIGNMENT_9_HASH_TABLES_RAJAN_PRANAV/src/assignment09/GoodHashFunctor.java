package assignment09;

public class GoodHashFunctor implements HashFunctor {

  /**
   * A functor (aka function object) is a function embedded in an object, so that it may be passed
   * as a parameter to another function.
   */

  /**
   * This algorithm is from the Java API Source Code for String Hashing
   * The h constant is not specifically defined in the source code so I chose 7 as a prime number
   * because Jake said that Java uses 7 as a starting prime value for hashing
   * Link to source:http://hg.openjdk.java.net/jdk8/jdk8/jdk/file/687fd7c7986d/src/share/classes/java/lang/String.java
   * 
   */
  @Override
  public int hash(String item) {

    int h = 7;

    for (int i = 0; i < item.length(); i++) {
      h = 31 * h + item.charAt(i);
    }

    return h;
  }
}
