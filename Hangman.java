/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.program.ConsoleProgram;
import acm.util.RandomGenerator;

public class Hangman extends ConsoleProgram {

	public void run() {
		getReadyForGame();
		gameProcess();
	}

	/* getting ready for the game */
	private void getReadyForGame() {
		println("Welcome To Hangman.");
		getWord();
		hiddenWord();
	}

	/* generates a random word from the HangmanLexicon */
	private void getWord() {
		int i = rgen.nextInt(0, lexiconWord.getWordCount() - 1);
		word = lexiconWord.getWord(i);
	}

	/*
	 * it makes word hidden , each letter becomes "-", so we know how many
	 * letters it has
	 */
	private void hiddenWord() {
		for (int i = 0; i < word.length(); i++) {
			hiddenWord += "-";
		}
	}

	/*
	 * it prints the hidden word condition, how many letters are guessed and
	 * left(visually)
	 */
	private void wordLook() {
		println("The word now looks like this : " + hiddenWord);
		displayWordonCanvas();
	}

	/* game process */
	private void gameProcess() {
		while (finishPlay == true) {
			wordLook();
			leftGueses();
			enterGuess();
			checkGuess();
		}
	}

	/*
	 * displays the hidden word condition, how many words are guessed and
	 * left(visually) For example: --A-B-C-
	 */
	private void displayWordonCanvas() {
		canvas.displayWord(hiddenWord);
	}

	/* prints how many guess(incorrect guess) left */
	private void leftGueses() {
		println("You have " + tries + " guesses left.");
	}

	/*
	 * here we enter our guess if we entered one symbol and it's a letter we can
	 * check if it is correct or not if it is not one symbol or it isn't a
	 * letter, program doesn't continue while we enter this kind of guess(we
	 * have to enter one symbol(letter))
	 */
	private void enterGuess() {
		String guess = readLine("Your guess: ");
		if (guess.length() == 1 && Character.isLetter(guess.charAt(0))) {
			currentGuess = guess;
		} else {
			while (guess.length() != 1
					|| Character.isLetter(guess.charAt(0)) != true) {
				guess = readLine("Please enter anather : ");
			}
			currentGuess = guess;
		}
	}

	/*
	 * it checks if we entered current guess.(it doesn't matter if we guessed
	 * the letter and it is upper case or lower case). if we guessed program
	 * puts letter. if we didn't we lost try and checks if we lost the game.
	 */
	private void checkGuess() {
		if (word.contains(currentGuess.toLowerCase())
				|| word.contains(currentGuess.toUpperCase())) {
			println("That guess is correct.");
			puttLetter();
		} else {
			println("There are no " + currentGuess + "'s in the word.");
			canvas.noteIncorrectGuess(currentGuess.charAt(0));
			tries--;
			checkLose();
		}
	}

	/*
	 * this method puts the guessed letter in the correct place, and then checks
	 * if we won.
	 */
	private void puttLetter() {
		String rez = "";
		char guessLowCase = Character.toLowerCase(currentGuess.charAt(0));
		char guessUppCase = Character.toUpperCase(currentGuess.charAt(0));
		for (int i = 0; i < word.length(); i++) {
			if (guessLowCase == word.charAt(i)
					|| guessUppCase == word.charAt(i)) {
				rez += word.charAt(i);
			} else {
				rez += hiddenWord.charAt(i);
			}
		}
		hiddenWord = rez;
		checkWin();
	}

	/* it checks if we won */
	private void checkWin() {
		displayWordonCanvas();
		if (hiddenWord.indexOf('-') == -1) {
			finishPlay = false;
			println("You guessed the word : " + word);
			println("You Win.");
			afterWinOrLose();
		}
	}

	/* it checks if we lost */
	private void checkLose() {
		if (tries == 0) {
			println("The word was : " + word);
			println("You lose.");
			afterWinOrLose();
			finishPlay = false;
		}
	}

	/*
	 * this method is used when the game is over(Won or lost) and if we enter
	 * "1" it means that we want to continue the game and the games will start
	 * again from the begining
	 */
	private void afterWinOrLose() {
		int st = readInt("Type >>>  1  <<<  if you want to play again : ");
		if (st == 1) {
			setDefault();
			run();
		}
	}

	/* it sets the default values */
	private void setDefault() {
		canvas.reset();
		currentGuess = "";
		hiddenWord = "";
		tries = 8;
		finishPlay = true;
	}

	/* init method , creates new hangmanCanvas object */
	public void init() {
		canvas = new HangmanCanvas();
		canvas.setCoordinate(getWidth() / 2);
		add(canvas);
	}

	private HangmanLexicon lexiconWord = new HangmanLexicon();
	private RandomGenerator rgen = new RandomGenerator();
	private int tries = 8;
	private String word; // the word from the lexicon
	private String currentGuess = "";// current guess we entered
	private String hiddenWord = "";
	private boolean finishPlay = true;
	private HangmanCanvas canvas;
}