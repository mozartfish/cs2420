/*
 * Here is a starting point for your Matrix tester. You will have to fill in the rest of "main" with
 * more code to sufficiently test your Matrix class. We will be using our own MatrixTester for grading.
 */
package assignment01;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit tests for the Matrix class
 *
 * @author Dr. Miriah Meyer, Ryan Sargent & Pranav Rajan
 * @version January 11, 2018
 */
public class MatrixJUnitTester {

  Matrix threeByTwo,
      twoByThree,
      twoByTwoResult,
      emptyMatrix,
      threeByThree,
      twoByFour,
      oneByOne,
      rightOneByOne,
      oneByOneSum,
      oneByOneProduct,
      compareTwoByThree,
      rightThreeByThree,
      threeByThreeResult;
  /*Initialize some matrices we can play with for every test!*/

  @Before
  public void setup() {
    threeByTwo = new Matrix(new int[][] {{1, 2, 3}, {2, 5, 6}});
    compareTwoByThree = new Matrix(new int[][] {{1, 42, 32}, {2, 15, 65}});
    oneByOne = new Matrix(new int[][] {{4}});
    rightOneByOne = new Matrix(new int[][] {{8}});
    twoByFour = new Matrix(new int[][] {{1, 2, 3, 4}, {2, 5, 6, 8}});
    twoByThree = new Matrix(new int[][] {{4, 5}, {3, 2}, {1, 1}});
    threeByThree = new Matrix(new int[][] {{4, 5, 6}, {3, 2, 0}, {1, 1, 1}});
    rightThreeByThree = new Matrix(new int[][] {{1, 2, 3}, {5, 2, 0}, {1, 1, 1}});
    emptyMatrix = new Matrix(new int[0][0]);
    // this is the known correct result of multiplying M1 by M2
    twoByTwoResult = new Matrix(new int[][] {{13, 12}, {29, 26}});
    threeByThreeResult = new Matrix(new int[][] {{35, 24, 18}, {13, 10, 9}, {7, 5, 4}});
    oneByOneSum = new Matrix(new int[][] {{12}});
    oneByOneProduct = new Matrix(new int[][] {{32}});
  }

  @Test
  public void timesWithBalancedDimensions() {
    Matrix matrixProduct = threeByTwo.times(twoByThree);
    Assert.assertTrue(twoByTwoResult.equals(matrixProduct));
  }

  @Test
  public void timesWithThreeByThree() {
    Matrix matrixProduct = threeByThree.times(rightThreeByThree);
    Assert.assertTrue(threeByThreeResult.equals(matrixProduct));
  }

  @Test
  public void oneByOneSum() {
    Matrix matrixSum = oneByOne.plus(rightOneByOne);
    Assert.assertTrue(oneByOneSum.equals(matrixSum));
  }

  @Test
  public void oneByOneProduct() {
    Matrix matrixProduct = oneByOne.times(rightOneByOne);
    Assert.assertTrue(oneByOneProduct.equals(matrixProduct));
  }

  @Test
  public void timesWithUnbalancedDimensions() {
    Matrix matrixProduct = threeByTwo.times(twoByFour);
    Assert.assertEquals(null, matrixProduct);
  }

  @Test
  public void plusWithUnbalancedDimensions() {
    Matrix matrixSum = threeByTwo.plus(threeByThree);
    Assert.assertEquals(null, matrixSum);
  }

  @Test
  public void equalsWithDifferentValues() {
    Assert.assertEquals(false, threeByTwo.equals(compareTwoByThree));
  }

  @Test
  public void equalsWithUnbalancedDimensions() {
    Assert.assertEquals(false, threeByTwo.equals(threeByThree));
  }

  @Test
  public void equalsWithDifferentKindOfObject() {
    Assert.assertEquals(false, threeByTwo.equals("Hello World"));
  }

  @Test
  public void toStringThatReturnsAnEmptyStringForAMatrixWithNoElements() {
    Assert.assertEquals("", emptyMatrix.toString());
  }

  @Test
  public void toStringOneByOne() {
    String resultString = "4\n";
    Assert.assertEquals(resultString, oneByOne.toString());
  }

  @Test
  public void twoByTwoToString() {
    String resultString = "13 12\n29 26\n";
    Assert.assertEquals(resultString, twoByTwoResult.toString());
  }
}
