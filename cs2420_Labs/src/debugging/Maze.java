package debugging;

import java.util.List;
import java.util.Properties;

import codemaze.Backpack;
import codemaze.Checkpoint;
import codemaze.Constants;
import codemaze.MazeException;
import codemaze.SpawnPoint;
import codemaze.binarysearch.Cauldron;
import codemaze.classic.Password;
import codemaze.conditional.PuzzleWall;
import codemaze.conditional.PuzzleWall.Tumbler;
import codemaze.firsthurdle.Entrance;
import codemaze.npe.Chain;
import codemaze.npe.ChainFence;
import codemaze.sphinx.Sphinx;
import codemaze.spin.TeaCupCode;
import codemaze.spin.TeaCups;

public class Maze {
	Backpack backpack = new Backpack();
	Chain chain; // chain can be constructed with a default constructor

	/**
	 * This code expects a maze.properties file to exist in your project,
	 * and expects a certain key=value pair!
	 */
	public void enterMaze() {
		System.out.println(Entrance.getEntranceMessage());
		String uidPropertyFilePath = "maze.properties";
		Properties uidPropertyfile = Entrance.readPropertyFile(uidPropertyFilePath);
		String uid = uidPropertyfile.getProperty("uid");
		Entrance.declareUID(uid);
	}

	/**
	 * The debugging will happen {@link #binarySearch(Integer[], Integer)}
	 */
	public void potionProblem() {
		System.out.println(Constants.Cauldron.GREETING);
		backpack.setBinarySearch(this::binarySearch);
		Cauldron.testBinarySearch(backpack);
	}
	
	/**
	 * The debugging happens in {@link #teacups()}
	 */
	public void aroundWeGo() {
		System.out.println(Constants.TeaCups.GREETING);
		TeaCups cups = new TeaCups(this::teacups);
		backpack.checkCode(cups.getCode());
	}
	
	/**
	 * The debugging happens in {@link #checkPassword(String)}
	 */
	public void sphinx() {
		System.out.println(Constants.Sphinx.GREETING);
		Sphinx cyborgSphinx = new Sphinx(this::checkPassword); // debug inside the "Check password" method.
		backpack.debugSphinx(cyborgSphinx, "1 4 9 16"); 
	}
	
	/**
	 * The debugging happens in {@link #calculatePuzzlePassword(List, List, List)}
	 * 
	 * PuzzleWall.generateTumblers returns a list of Tumblers, each represented by a letter.
	 * The puzzle wall then scrambles the tumbler lists, and it is your job to see at what point the all
	 * "line up" when the outer ring is on it's last tumbler
	 * 
	 * In other words, when the outerRing.get(i) == middleRing.get(j) == innerRing.get(k) and the outerRing has gone through all of its
	 * iterations the correct Puzzle Wall password will be "i-j-k", where i, j, and k are replaced with their numerical value. For example, 
	 * outer.get(1) == middle.get(1) == inner.get(1), (this would be when the outer ring is on its SECOND tumbler) and the password would be 1-1-1. 
	 * Your job is to find out when the line up when the outer ring is on it's last iteration.
	 * 
	 * The correct password will be the respective indices that the outer, middle, and inner ring all equal each other. The password
	 * format is "outerIndex-middleIndex-innerIndex" as a string
	 */
	public void puzzleWall() {
		System.out.println(Constants.PuzzleWall.GREETING);
		PuzzleWall puzzWall = new PuzzleWall();
		List<Tumbler> outerRing = puzzWall.outerRing;
		List<Tumbler> middleRing = puzzWall.middleRing;
		List<Tumbler> innerRing = puzzWall.innerRing;
		System.out.println(Constants.PuzzleWall.EXPLANATION);
		puzzWall.tryPassword(calculatePuzzlePassword(outerRing, middleRing, innerRing));
	}
	
	/**
	 * Find and remove the method that throws the NPE!
	 */
	public void theChainLinkFence() {
		System.out.println(Constants.Fence.GREETING);
		/*a*/ chain.is().only().as().strong().as().its().weakest().link();
		ChainFence.allowThrough(chain);
	}
	
	/**
	 * This algorithm builds up a list of indicies that map to the correct letter
	 * in the passletters list to form the password. 
	 * 
	 * The for loop iterates through passwordIndecies, grabbing the correct string out of passletters and
	 * adding it to a StringBuilder. The StringBuilder's string is then compared to the proper password. 
	 */
	public void oakDoor() {
		List<String> passletters = Password.getPassletters();
		StringBuilder passwordBuilder = new StringBuilder();
		int[] passwordIndecies = new int[]{36, 45, 39, 47, 35, 30};
		
		for(int pIdx = 0; pIdx < passwordIndecies.length; pIdx++) {
			String string = passletters.get(passwordIndecies[pIdx]);
			passwordBuilder.append(string);
		}
		String passwordGuess = passwordBuilder.toString();
		if(passwordGuess.equals(Password.getPassword())) {
			Password.checkPassword(passwordIndecies);
		} else {
			throw new MazeException("No password...no progress!");
		}
	}
	
	/**
	 * Try to find the infinite loops! Delete or comment out the loops that are infinite, but do not change the other variables.
	 */
	private TeaCupCode teacups() {
		int a = 1, b = 0, c = -1, d = 3, f = 5;

		//Loop #1
		while(a < 10) {
			a += d;
			d -= c;
			a++;
			try {
				Thread.sleep(100);
			} catch(InterruptedException e) { }
		}

//		//Loop #2
//		while(b < 10) {
//			b *= a;
//			a += c;
//			d++;
//			try {
//				Thread.sleep(100);
//			} catch(InterruptedException e) { }
//		}

		//Loop #3
		while(a > c) {
			a += d;
			d--;
			b++;
			try {
				Thread.sleep(100);
			} catch(InterruptedException e) { }
		}

//		//Loop #4
//		while(f > 0) {
//			f -= c;
//			b += c;
//			c *= -1;
//			try {
//				Thread.sleep(100);
//			} catch(InterruptedException e) { }
//		}
		TeaCupCode code = new TeaCupCode(a, b, c, d, f);
 		return code;
	}
	
	/**
	 * Don't change any of the code in this function, just change which 
	 * password is passed in. Use your debugger to see WHAT the function expects
	 * password to be. 
	 * 
	 * DO NOT CHANGE THIS FUNCTION.
	 */
	public boolean checkPassword(String password) {
		String[] tokens = password.split(" ");
		int a = 0, b = 1;
		for(int i = 0; i < tokens.length; i++) {
			a += b;
			int currentToken = Integer.parseInt(tokens[i]);
			if(a != currentToken) {
				return false;
			}
			b += 2;
		}
		return b == 11;
	}
	/** SEE HOW HARD IT IS TO DEBUG LOOPS THAT HAVE WORTHLESS VARIABLE NAMES?
	 * Change these variable names via refactoring.
	 * 
	 * Only change the condition inside the triple for-loop to match what the
	 * prompt is asking. Use your debugger to pause at the correction combination,
	 * and change the return statement. 
	 */ 
	public String calculatePuzzlePassword(List<Tumbler> outer, List<Tumbler> middle, List<Tumbler> inner) {
		
		for(int a = 0; a < outer.size(); a++) {
			for(int b = 0; b < middle.size(); b++) {
				for(int c = 0; c < inner.size(); c++) {
					if(outer.get(a).equals(middle.get(b)) && middle.get(b).equals(inner.get(c)) && outer.get(a).equals(inner.get(c))) {
						backpack.recordCombo(a, b, c);
					}
				}
			}
		}
		return "22-15-9";  // <-- CHANGE ME
	}
	
	/**
	 * This is an implementation of the binarySearch algorithm. Thar
	 * be bugs in here.
	 */
	private boolean binarySearch(Integer[] potions, Integer goal) {
		int low = 0, high = potions.length - 1, mid = 0;
		while(low <= high) {
      mid = (low + high) / 2;
      if (potions[mid] == goal) {
        return true;
      } else if (potions[mid] < goal) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
		}
		return false;
	}
	public SpawnPoint respawn() {
		return Checkpoint.returnCheckpoint();
	}
	/**
	 * Congratulations!
	 */
	public void finish() {
		System.out.println("Good job! You're now a debuggin' master!");
	}
	
}
