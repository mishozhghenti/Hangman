/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class HangmanLexicon {

	public HangmanLexicon() {
		try {
			BufferedReader bfr = new BufferedReader(new FileReader(
					"HangmanLexicon.txt"));
			while (true) {
				String line = bfr.readLine();
				if (line == null) {
					break;
				}
				wordList.add(line);
			}
			bfr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return wordList.size();
	}

	/** Returns the word at the specified index. */
	public String getWord(int index) {
		return wordList.get(index);
	}

	private ArrayList<String> wordList = new ArrayList<String>();
	
}
