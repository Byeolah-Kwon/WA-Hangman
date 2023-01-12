import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * The Hangman program implements a game that the user guesses a name of the
 * city in Washington State
 * 
 * @author Byeolah Kwon
 * @version 1.0
 * @since 2020-06-12
 *
 */
public class Hangman {

	public static final String[] CITY = { "Seattle", "Spokane", "Tacoma", "Vancouver", "Bellevue", "Kent", "Everett",
			"Renton", "Yakima", "Kirkland", "Bellingham", "Auburn", "Redmond", "Lakewood", "Shoreline", "Olympia",
			"Bothell", "Edmonds", "Lynnwood", "Pullman", "Mukilteo", "Monroe", "Snoqualmie", "Leavenworth", "Arlington",
			"Brier", "Snohomish" };
	public static final Random RANDOM = new Random();
	public static final int MAX_ERRORS = 6;

	private String wordToFind;
	private char[] wordFound;
	private int numberOfErrors;
	private ArrayList<String> letters = new ArrayList<>();
	private ArrayList<String> alreadyEntered = new ArrayList<>();

	/**
	 * This method is used to get a random city
	 * 
	 * @return a random city
	 */
	private String nextWordToFind() {
		return CITY[RANDOM.nextInt(CITY.length)];
	}

	/**
	 * This method is used to make a default setting before starting a new game
	 */
	public void newGame() {
		numberOfErrors = 0;
		letters.clear();
		wordToFind = nextWordToFind();
		wordFound = new char[wordToFind.length()];
		wordFound[0] = wordToFind.charAt(0);
		for (int i = 1; i < wordFound.length; i++) {
			wordFound[i] = '_';
		}
		System.out.println("\n**Hint: the City Starts with " + wordToFind.charAt(0));
	}

	/**
	 * This method is used to show the results for the user 
	 * Show how many tries user can do 
	 * Show the user won or lost
	 * 
	 */
	public void play() {
		try (Scanner input = new Scanner(System.in)) {
			while (numberOfErrors < MAX_ERRORS) {
				System.out.print("\n    Enter a Letter: ");
				String userInput = input.next();
				
				if (alreadyEntered.contains(userInput)) {
					System.out.println(
							"    You've already Entered " + "'" + userInput + "'" + " \n    Please Try Another One");
				}

				if (userInput.length() > 1) {
					userInput = userInput.substring(0, 1);
				}
				getHangman(userInput);

				System.out.println("\n" + wordFoundContent());

				if (wordFound()) {
					System.out.println("\n---------Congratulations! You win--------------");
					break;
				} else {
					System.out.println("\nNumber of remaining tries: " + (MAX_ERRORS - numberOfErrors));
				}
			}

			if (numberOfErrors == MAX_ERRORS) {
				System.out.println("\n--------Oh...The hangman is dead :(----------");
				System.out.println("The city was: " + wordToFind);
			}
		}
	}

	/**
	 * This method is used to check if user found the hidden random city
	 * 
	 * @return Boolean true or false
	 */
	public boolean wordFound() {
		return wordToFind.contentEquals(new String(wordFound));
	}

	/**
	 * This method is used to show the hangman 
	 * It depends on how many times the user found a wrong letter
	 * 
	 * @param String userInput
	 */
	private void getHangman(String userInput) {
		if (!letters.contains(userInput)) {
			if (wordToFind.contains(userInput)) {
				int index = wordToFind.indexOf(userInput);

				while (index >= 0) {
					wordFound[index] = userInput.charAt(0);
					index = wordToFind.indexOf(userInput, index + 1);
				}

			} else {
				numberOfErrors++;
				alreadyEntered.add(userInput);
			}
			letters.add(userInput);
			alreadyEntered.add(userInput);
		}

		if (numberOfErrors == 0) {
			System.out.println("\n_________" + "\n|" + "\n|" + "\n|" + "\n|" + "\n|" + "\n|_______________________\n");
		} else if (numberOfErrors == 1) {
			System.out.println("\n_________" + "\n|                |" + "\n|                O" + "\n|" + "\n|" + "\n|"
					+ "\n|_______________________\n");
		} else if (numberOfErrors == 2) {
			System.out.println("\n_________" + "\n|                |" + "\n|                O" + "\n|                |"
					+ "\n|" + "\n|" + "\n|_______________________\n");

		} else if (numberOfErrors == 3) {
			System.out.println("\n_________" + "\n|                |" + "\n|                O" + "\n|             ---|"
					+ "\n|" + "\n|" + "\n|_______________________\n");
		} else if (numberOfErrors == 4) {
			System.out.println("\n_________" + "\n|                |" + "\n|                O"
					+ "\n|             ---|---" + "\n|" + "\n|" + "\n|_______________________\n");

		} else if (numberOfErrors == 5) {
			System.out
					.println("\n_________" + "\n|                |" + "\n|                O" + "\n|             ---|---"
							+ "\n|               /" + "\n|              /" + "\n|_______________________\n");
		}

		else if (numberOfErrors == 6) {
			System.out
					.println("\n_________" + "\n|                |" + "\n|                O" + "\n|             ---|---"
							+ "\n|               /\\" + "\n|              /  \\" + "\n|_______________________");

		}
	}

	/**
	 * This method is used to show the letters that user found so far
	 * 
	 * @return StringBuilder letters that user found
	 */
	private String wordFoundContent() {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < wordFound.length; i++) {
			builder.append(wordFound[i]);

			if (i < wordFound.length - 1) {
				builder.append(" ");
			}
		}
		return builder.toString();
	}
}
