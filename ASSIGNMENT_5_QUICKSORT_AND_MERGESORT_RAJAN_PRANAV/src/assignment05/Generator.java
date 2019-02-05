package assignment05;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public class Generator {

  public static ArrayList<String> generateStrings(int length) {
    final String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    SecureRandom rnd = new SecureRandom();

    ArrayList<String> wordList = new ArrayList<String>();
    for (int i = 0; i < length; i++) {
      int wordLength = (int) (Math.random() * 15 + 5);
      StringBuilder sb = new StringBuilder(wordLength);
      for (int j = 0; j < wordLength; j++) {

        sb.append(AB.charAt(rnd.nextInt(AB.length())));
      }
      wordList.add(sb.toString());
    }
    return wordList;
  }

  public static ArrayList<Integer> generateIntegers(int size) {
    ArrayList<Integer> list = new ArrayList<Integer>();

    Random rand = new Random();
    for (int i = 1; i <= size; i++) {
      list.add(rand.nextInt(Integer.MAX_VALUE));
    }
    return list;
  }
}
