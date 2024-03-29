package regex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NeedleFinder {
	public static void main(String[] args) {
		StringBuilder passphrase = new StringBuilder();
		Pattern UID_PATTERN    = Pattern.compile("[uU][0-9]{7}+");
		Pattern HEX_PATTERN    = Pattern.compile("u1136324\\W[0-9-Fa-fA]{10}\\W");
		Pattern JAVA_PATTERN   = Pattern.compile("u1136324[a-zA-Z_$][0-9a-zA-Z_$]{5,8}\\W\\W\\p{XDigit}{8}\\W");
		Pattern REPEAT_PATTERN = Pattern.compile("(..)\\1{3,}u1136324");
		String possiblePassphrase;
		try {
			String haystack = new String(Files.readAllBytes(Paths.get("haystack.txt")));
			
			/** PART ONE - A NEEDLE IN A HAYSTACK */
			System.out.println("Running part one...");
			possiblePassphrase = getPassphrase(UID_PATTERN, -1, haystack);
			// If you know the group number that has your passphrase, supply it in the above
			// method. If not, subString possiblePassphrase and supply it to the passphrase 
			// StringBuilder.
			passphrase.append(possiblePassphrase);
			
			String needles = new String(Files.readAllBytes(Paths.get("needles.txt")));
			/** PART TWO - NEEDLE IN A STACK OF NEEDLES */
			System.out.println("Running part two...");
			possiblePassphrase = getPassphrase(HEX_PATTERN, -1, needles);
			// If you know the group number that has your passphrase, supply it in the above
			// method. If not, substring possiblePassphrase and supply it to the passphrase 
			// StringBuilder.
			passphrase.append(possiblePassphrase);
			
			
			/** PART THREE - No time like the present */
			System.out.println("Running part three...");
			possiblePassphrase = getPassphrase(JAVA_PATTERN, -1, needles);
			// If you know the group number that has your passphrase, supply it in the above
			// method. If not, substring possiblePassphrase and supply it to the passphrase 
			// StringBuilder.
			passphrase.append(possiblePassphrase);
			
			/** PART FOUR - No time like the present */
			System.out.println("Running part four...");
			possiblePassphrase = getPassphrase(REPEAT_PATTERN, -1, needles);
			// If you know the group number that has your passphrase, supply it in the above
			// method. If not, substring possiblePassphrase and supply it to the passphrase 
			// StringBuilder.
			passphrase.append(possiblePassphrase);
			
			System.out.println("If you did everything correctly...your password should be: ");
			System.out.println(passphrase.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param pattern - The compiled Regex pattern to search for
	 * @param groupNum - group number that holds your password
	 * @param input - The string to search.
	 * @return
	 */
	public static String getPassphrase(Pattern pattern, int groupNum, String input) {
		Matcher matcher = pattern.matcher(input);
		String output = null;
		
		while(matcher.find()) {
			if(output != null) {
				System.err.println("Woops! Your pattern matched multiple strings. It should only match once!");
			}
			System.out.println("Matched: " + matcher.group());
			output = matcher.group(); // matches EVERYTHING
			if(groupNum > 0) { // if a valid group number, grab that specific group.
				output = matcher.group(groupNum);
			}
		}
		if(output == null) {
			System.err.println("Oh no! Your pattern didn't match anything!");
		}
		return output;
	}
}
