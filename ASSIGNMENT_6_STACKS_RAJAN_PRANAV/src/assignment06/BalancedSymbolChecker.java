package assignment06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class containing the checkFile method for checking if the (, [, and { symbols in an input file
 * are correctly matched.
 *
 * @author Erin Parker && Pranav Rajan
 * @version March 1, 2018
 */
public class BalancedSymbolChecker {

  /**
   * Generates a message indicating whether the input file has unmatched symbols. (Use the helper
   * methods below for constructing messages.)
   *
   * @param filename - name of the input file to check
   * @return a message indicating whether the input file has unmatched symbols
   * @throws FileNotFoundException if the file does not exist
   */
  public static String checkFile(String filename) throws FileNotFoundException {

    //Create a new stack that will push and pop the characters
    StackArray<Character> stack = new StackArray<Character>();

    //Create a new file object
    File file = new File(filename);

    //Using try with resources create a new scanner object to store and read the file and check that the file exists
    try (Scanner scn = new Scanner(file)) {

      //Create a variable to store the line number
      int lineNumber = 0;

      //Create a boolean flag that states whether or not a block comment has been found
      //The flag is outside the while loop because we only need to set the flag once since
      //a block comment can span multiple lines
      boolean isBlockComment = false;

      //Go through each line of the file
      while (scn.hasNextLine()) {

        //Update the line number to the current line number
        lineNumber = lineNumber + 1;

        //Create a boolean flag that states whether or not a string character has been found
        //The flag is inside the while loop because when a block comment occurs we have to make sure that the flag
        //is reset every time a new line occurs such that string characters are not read during a block comment
        boolean isString = false;

        //Create a boolean flag that states whether or not a character character has been found
        //The flag is inside the while loop because when a block comment occurs we have to make sure that the flag
        //is reset every time a new line occurs such that the character characters are not read during a block comment
        boolean isChar = false;

        //Store the line to be analyzed in a variable
        String s = scn.nextLine();

        //Store the number of characters in the line in a variable
        int numCharacters = s.length();

        //Look at each character in the line. The column number refers to each character in the line
        for (int colNumber = 0; colNumber < numCharacters; colNumber++) {

          //Update the current character to the location of the colNumber in the string s
          char currentChar = s.charAt(colNumber);

          //If the index of the current character is less than the number of characters in the line, check if a char symbol is found
          if (colNumber < numCharacters && currentChar == '\'') {

            //If an opening char character ' is found set the boolean to true to indicate the start of a character
            if (isChar == false) {
              isChar = true;
            }

            //If a closing char symbol ' is found set the boolean to false to indicate the end of a character
            else {
              isChar = false;
            }
          }

          //If the index of the current character is less than the number of characters in the line, check if a string symbol is found
          if (colNumber < numCharacters - 1 && currentChar == '\"') {

            //If an opening string character " is found set the boolean to true to indicate the start of a string
            if (isString == false) {
              isString = true;
              isBlockComment = false;
              isChar = false;
            }

            //If a closing string character " is found set the boolean to false to indicate the end of a string
            else {
              isString = false;
            }
          }

          //If the index of the current character is less than the number of characters in the line, check for escape sequences. If there is a \ check and see if it is followed by a " character to determine whether a string escape sequence has been found
          //If it is then skip the escape character to the next character so that if there is a string with an escape string sequence the program does not misinterpret the " character and update the column number
          if (colNumber < numCharacters - 1
              && currentChar == '\\'
              && s.charAt(colNumber + 1) == '\"') {
            currentChar = s.charAt(colNumber + 1);
            colNumber = colNumber + 1;
          }

          //while the index of the current character is less than the number of characters in the line, check if a single line comment / start character is found
          // if a / is found check if the character right after it is / also. If it is then ignore the entire line
          if (colNumber < numCharacters - 1
              && currentChar == '/'
              && s.charAt(colNumber + 1) == '/') {
            isString = false;
            isBlockComment = false;
            isChar = false;
            break;
          }

          //If the index of the current character is less than the number of characters in the line, check if a block comment / start character is found
          //If a / is found check if the character right after it is a *. If it is then set the boolean to true to indicate the start of a block comment
          if (!isString
              && colNumber < numCharacters - 1
              && currentChar == '/'
              && s.charAt(colNumber + 1) == '*') {
            isBlockComment = true;
          }

          //If a block comment exists and if any characters or strings are found within the block comment make sure they are excluded
          if (isBlockComment == true && (currentChar == '\'' || currentChar == '\"')) {
            isChar = false;
            isString = false;
          }

          //If the index of the current character is less than the number of characters in the line, check if a block comment * end character is found
          //If a * is found check if the character right after it is a /. If it is then set the boolean to false to indicate the end of a block comment
          if (colNumber < numCharacters - 1
              && currentChar == '*'
              && s.charAt(colNumber + 1) == '/') {
            isBlockComment = false;
          }

          //If the character is an opening brace push it onto the stack
          if ((currentChar == '{' || currentChar == '(' || currentChar == '[')
              && (isChar == false && isString == false)
              && isBlockComment == false) {

            stack.push(currentChar);
            continue;
          }

          //If the current character is a closing brace pop it off the stack
          if ((currentChar == '}' || currentChar == ')' || currentChar == ']')
              && (isChar == false && isString == false)
              && isBlockComment == false) {

            //Check to see if there is a closing brace without an opening brace
            if (stack.isEmpty()) {
              return unmatchedSymbol(lineNumber, colNumber + 1, currentChar, ' ');
            }

            //If the stack is not empty, and the program reaches this point, pop the symbol off the stack and see if matches the current value of currentChar
            char result = stack.pop();

            if (result == '{' && currentChar != '}') {
              return unmatchedSymbol(lineNumber, colNumber + 1, currentChar, '}');
            }
            if (result == '(' && currentChar != ')') {
              return unmatchedSymbol(lineNumber, colNumber + 1, currentChar, ')');
            }
            if (result == '[' && currentChar != ']') {
              return unmatchedSymbol(lineNumber, colNumber + 1, currentChar, ']');
            }
          }
        }
      }

      //Check if a block comment did not get closed
      if (isBlockComment == true) {
        return unfinishedComment();
      }

      //Check if the stack is empty, if it is then return a message stating that all symbols have been matched
      if (stack.isEmpty()) {
        return allSymbolsMatch();
      }

      //If the stack is not empty, then an opening brace was not matched with its corresponding closing brace.
      char unmatchedSymbol = stack.pop();
      if (unmatchedSymbol == '{') {
        return unmatchedSymbolAtEOF('}');
      } else if (unmatchedSymbol == '(') {
        return unmatchedSymbolAtEOF(')');
      } else {
        return unmatchedSymbolAtEOF(']');
      }
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException();
    }
  }

  /**
   * Use this error message in the case of an unmatched symbol.
   *
   * @param lineNumber - the line number of the input file where the matching symbol was expected
   * @param colNumber - the column number of the input file where the matching symbol was expected
   * @param symbolRead - the symbol read that did not match
   * @param symbolExpected - the matching symbol expected
   * @return the error message
   */
  private static String unmatchedSymbol(
      int lineNumber, int colNumber, char symbolRead, char symbolExpected) {
    return "ERROR: Unmatched symbol at line "
        + lineNumber
        + " and column "
        + colNumber
        + ". Expected "
        + symbolExpected
        + ", but read "
        + symbolRead
        + " instead.";
  }

  /**
   * Use this error message in the case of an unmatched symbol at the end of the file.
   *
   * @param symbolExpected - the matching symbol expected
   * @return the error message
   */
  private static String unmatchedSymbolAtEOF(char symbolExpected) {
    return "ERROR: Unmatched symbol at the end of file. Expected " + symbolExpected + ".";
  }

  /**
   * Use this error message in the case of an unfinished comment (i.e., a file that ends with an
   * open /* comment).
   *
   * @return the error message
   */
  private static String unfinishedComment() {
    return "ERROR: File ended before closing comment.";
  }

  /**
   * Use this message when no unmatched symbol errors are found in the entire file.
   *
   * @return the success message
   */
  private static String allSymbolsMatch() {
    return "No errors found. All symbols match.";
  }
}
