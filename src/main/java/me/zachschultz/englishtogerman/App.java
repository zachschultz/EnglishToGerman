package me.zachschultz.englishtogerman;


import java.util.*;
import java.io.*;

public class App {

	// Map to store Word objects, using a TreeMap allows me to ignore the case of the keys, making lookups easier
	public static Map<String, Word> dictionary = new TreeMap<String, Word>(String.CASE_INSENSITIVE_ORDER);
	// Filename of word database
	private static final String filename = "../../src/main/java/me/zachschultz/englishtogerman/wordDatabase.txt";
	public static Map<String, Word> getDictionary() {
		return dictionary;
	}

	public static void main(String[] args) {

		/* Debugging purposes to find out file path so we can correctly find word database file */
		// File here = new File(".");
		// System.out.println(here.getAbsolutePath());

		// File object for word database
		File wordDatabaseFile = new File(filename);
				
		/* ADD ALL WORDS FROM THE WORD DATABASE TO THE dictionary Map */
		try {
			// Scaner for the word database
			Scanner dbScanner = new Scanner(wordDatabaseFile);

			while (dbScanner.hasNextLine()) {
				// Grab each line from the database
				String line = dbScanner.nextLine();
				// wordArr[0] - English, wordArr[1] - German, wordArr[2] - gender
				String[] wordArr = line.split(" ");

				// Remove all whitespace from each word
				for (String word : wordArr) {
					word = word.replaceAll("\\s", "");
				}
				// Create a new Word object 
				Word newWord = new Word(wordArr[0], wordArr[1], wordArr[2].charAt(0));
				
				/* Probably inefficient, store both English and German word as keys
					so that the lookup can work better */
				dictionary.put(newWord.getEnglishWord(), newWord);
				dictionary.put(newWord.getGermanWord(), newWord);
			}

			dbScanner.close();

		} catch (FileNotFoundException e) {
			System.out.println("Error: word database file not found.");
		}

		/* GET USER INPUT AND RUN THE PROGRAM! */
		// Scanner for user input
		Scanner inputScanner = new Scanner(System.in);

		
		System.out.println("Enter a word (German or English) [x to quit]: ");
		String userWord = inputScanner.next();

		while (!userWord.equals("x")) {
			if (userWord.matches(".*\\d.*")) {
				System.out.println("Please only enter words");
				userWord = inputScanner.next();
				continue;
			}
			WordOutput.outputWordInfo(userWord);
			userWord = inputScanner.next();

		}

		// while ()

		// do {

		// 	while (inputScanner.hasNextInt()) {
		// 		System.out.println("Enter a word silly!");
		// 		userWord = inputScanner.next();
		// 	}
		// 	userWord = inputScanner.next();
		
		// 	System.out.println(); // spacer
		// 	userWord = inputScanner.nextLine();
		// } while (!userWord.equals("x"));

		// System.out.println("Good bye!");

	}
}