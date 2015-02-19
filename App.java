import java.util.*;
import java.io.*;

public class App {

	// Map to store Word objects
	static Map<String, Word> dictionary = new TreeMap<String, Word>(String.CASE_INSENSITIVE_ORDER);

	public static void main(String[] args) {

		// File object for word database
		File wordDatabaseFile = new File("wordDatabase.txt");
				
		try {
			// Scaner for word database
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


		// Scanner for user input
		Scanner inputScanner = new Scanner(System.in);


		System.out.println("Enter a word (German or English) [x to quit]: ");

		String userWord = inputScanner.nextLine();

		while (!userWord.equals("x")) {
			outputWordInfo(userWord);
			System.out.println(); // spacer
			userWord = inputScanner.nextLine();
		}
		System.out.println("Good bye!");

		
	}

	public static void outputWordInfo(String userWord) {

		// System.out.println();


		if (dictionary.containsKey(userWord)) {
			Word wordObj = dictionary.get(userWord);

			String germanWord = wordObj.getGermanWord();
			String englishWord = wordObj.getEnglishWord();
			String gender = wordObj.getGender();

			// First check if word is the same in English and German!
			if (userWord.equalsIgnoreCase(germanWord) && userWord.equalsIgnoreCase(englishWord)) {
				System.out.println("Would you like German info? (1 - Yes, 2 - No)");
				Scanner equalityScanner = new Scanner(System.in);
				int yesOrNo = equalityScanner.nextInt();
				if (yesOrNo == 1) printGermanWordInfo(germanWord, englishWord, gender);
				else printEnglishWordInfo(germanWord, englishWord, gender);

				return;
			}

			// Input was in German
			if (userWord.equalsIgnoreCase(germanWord)) {
				printEnglishWordInfo(germanWord, englishWord, gender);
			}
			// Input was in English
			else {
				printGermanWordInfo(germanWord, englishWord, gender);
			}

		} else {
			System.out.println("We dont have that word!") ;
			// IMPLEMENT FANCY LOOKUP ALGORITHM TO FIND CLOSE WORDS
			checkSimilarWords(userWord);
		}

		System.out.println();
		System.out.println("---------------------------------------------");
		System.out.println();
		return;
	}

	public static void printEnglishWordInfo(String germanWord, String englishWord, String gender) {
		System.out.println(germanWord + " (GERMAN) :  " + englishWord + " (ENGLISH)");
		return;
	}

	public static void printGermanWordInfo(String germanWord, String englishWord, String gender) {
		System.out.println(englishWord + " (ENGLISH) in German...");
		System.out.println();
		System.out.println("Article Word");
		System.out.println("_______ ____ \t");
		System.out.println(gender+ " \t" + germanWord);

		return;
	}

	public static void checkSimilarWords(String userWord) {
		for (String key : dictionary.keySet()) {
			if (key.toLowerCase().contains(userWord.toLowerCase()))
				System.out.println("Were you looking for " + key + "?");
			else if (userWord.toLowerCase().contains(key.toLowerCase()))
				System.out.println("Were you looking for " + key + "?");
		}
		return;
	}
}