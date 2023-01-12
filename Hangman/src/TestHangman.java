/**
 * This class is for testing the hangman game
 *
 * @author Byeolah Kwon
 * @version 1.0
 * @since 2020-06-12
 *
 */
public class TestHangman {
	/**
	 * This method gives the basic instruction to the user 
	 * and makes the user play the game
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("=======================================");
		System.out.println("       Byeolah's Hangman Game ");
		System.out.println("=======================================");
		System.out.println("\n    Let's Guess a City in WA! ");
		Hangman hangmanGame = new Hangman();
		hangmanGame.newGame();
		hangmanGame.play();

	}

}
