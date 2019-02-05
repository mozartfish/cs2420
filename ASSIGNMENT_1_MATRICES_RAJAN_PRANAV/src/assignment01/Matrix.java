package assignment01;

/**
 * Implementation of a 2D Mathematical Matrix - includes functionality for multiplying and adding
 * matrices
 *
 * @author Dr.Miriah Meyer, Ryan Sargent, Pranav Rajan
 * @version January 11, 2018
 */
public class Matrix {

  int numRows;
  int numColumns;
  int data[][];

  /**
   * Constructor with data for new matrix (automatically determines dimensions)
   *
   * @param data -- a 2D integer array of data used to initialize the new Matrix object
   */
  public Matrix(int data[][]) {
    numRows = data.length; // d.length is the number of 1D arrays in the 2D array
    if (numRows == 0) {
      numColumns = 0;
    } else {
      numColumns = data[0].length; // d[0] is the first 1D array
    }
    this.data = new int[numRows][numColumns]; // create a new matrix to hold the data
    // copy the data over
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numColumns; j++) {
        this.data[i][j] = data[i][j];
      }
    }
  }

  /**
   * Determines whether two objects are equivalent Matrices
   *
   * @param other -- an object to compare to
   * @return -- true if the Matrices are equivalent and false otherwise (or if the second object
   *     isn't a Matrix)
   */
  @Override
  // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
  public boolean equals(Object other) {
    if (!(other instanceof Matrix)) { // make sure the Object we're comparing to is a Matrix
      return false;
    }
    Matrix matrix =
        (Matrix) other; // if the above was not true, we know it's safe to treat 'o' as a Matrix
    if (matrix.numRows != this.numRows || matrix.numColumns != this.numColumns) {

      return false;

    } else {
      for (int a = 0; a < numRows; a++) {
        for (int b = 0; b < numColumns; b++) {
          if (matrix.data[a][b] != this.data[a][b]) {
            return false;
          }
        }
      }
      return true;
    }
  }

  /**
   * Converts the Matrix object into a string representation of its data
   *
   * @return -- string representation of the matrix
   */
  @Override
  // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
  public String toString() {
    StringBuilder s = new StringBuilder();
    for (int a = 0; a < numRows; a++) {
      for (int b = 0; b < numColumns; b++) {
        if (b == numColumns - 1) {
          s.append(data[a][b]).append("\n");
        } else {
          s.append(data[a][b]).append(" ");
        }
      }
    }
    return s.toString();
  }

  /**
   * Multiplies two matrix objects resulting in a new product matrix
   *
   * @param matrix -- the right hand side matrix to be multiplied
   * @return -- the resulting matrix of the multiplication
   */
  public Matrix times(Matrix matrix) {
    Matrix product;
    if (this.numColumns != matrix.numRows) {
      return null;
    } else {
      product = new Matrix(new int[this.numRows][matrix.numColumns]);
      for (int i = 0; i < this.numRows; i++) {
        for (int j = 0; j < matrix.numColumns; j++) {
          for (int k = 0; k < this.numColumns; k++) {
            product.data[i][j] += this.data[i][k] * matrix.data[k][j];
          }
        }
      }
    }
    return product;
  }

  /**
   * Adds two matrix objects together
   *
   * @param matrix -- the right hand side matrix to be added
   * @return -- the resulting matrix of the addition
   */
  public Matrix plus(Matrix matrix) {
    Matrix sum;
    if (matrix.numRows != this.numRows || matrix.numColumns != this.numColumns) {
      return null;
    } else {
      sum = new Matrix(new int[numRows][numColumns]);
      for (int i = 0; i < numRows; i++) {
        for (int j = 0; j < numColumns; j++) {
          sum.data[i][j] = this.data[i][j] + matrix.data[i][j];
        }
      }
    }
    return sum;
  }
}
