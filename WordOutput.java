import java.util.*;
public class WordOutput {


	public static void outputWordInfo(String userWord) {

		// Get the dictionary Map from the App class
		Map<String, Word> dictionary = App.getDictionary();

			// Case 1: `dictionary` has the word
			if (dictionary.containsKey(userWord)) {
				Word wordObj = dictionary.get(userWord);

				String germanWord = wordObj.getGermanWord();
				String englishWord = wordObj.getEnglishWord();
				String gender = wordObj.getGender();

				// Case 1.a: Word is the same in German and English
				if (userWord.equalsIgnoreCase(germanWord) && userWord.equalsIgnoreCase(englishWord)) {
					System.out.println("Would you like German info? (1 - Yes, 2 - No)");
					Scanner equalityScanner = new Scanner(System.in);
					
					int yesOrNo = equalityScanner.nextInt();
					if (yesOrNo == 1) 
						printGermanWordInfo(germanWord, englishWord, gender);
					else 
						printEnglishWordInfo(germanWord, englishWord, gender);

					return;
				}

				// Case 1.b: Input is German
				if (userWord.equalsIgnoreCase(germanWord)) {
					printEnglishWordInfo(germanWord, englishWord, gender);
				}
				// Case 1.c: Input was in English
				else {
					printGermanWordInfo(germanWord, englishWord, gender);
				}

			// Case 2: `dictionary` doesn't have the word, but hold on!
			} else {
				System.out.println("We dont have that word!") ;

				// IMPLEMENT FANCY LOOKUP ALGORITHM TO FIND CLOSE WORDS
				// Try to find words that the user may have meant to use
				checkSimilarWords(userWord, dictionary);
			}

			// Just some spacing for the next word
			System.out.println();
			System.out.println("---------------------------------------------");
			System.out.println();
			
			return;
		}

		public static void printEnglishWordInfo(String germanWord, String englishWord, String gender) {

			System.out.println();

			boolean writeGender = true; // if true, will print out gender article
			if (gender.equals("N/A")) 
				writeGender = false; // no gender, set it to false

			System.out.println( (writeGender ? gender + " " : "") + germanWord + " (GERMAN) :  " + englishWord + " (ENGLISH)");
			
			return;
		}

		public static void printGermanWordInfo(String germanWord, String englishWord, String gender) {
			
			System.out.println();
			System.out.println(englishWord + " (ENGLISH) in German...");
			System.out.println();

			System.out.println("Article Word");
			System.out.println("_______ ____ \t");
			System.out.println(gender+ " \t" + germanWord);

			return;
		}

		public static void checkSimilarWords(String userWord, Map<String, Word> dictionary) {
			int counter = 0;
			String[] similarWordArr = new String[100];

			for (String key : dictionary.keySet()) {
				
				if (key.toLowerCase().contains(userWord.toLowerCase())) {
					System.out.println("Were you looking for " + key + "? (If so, enter " + counter + ")");

					similarWordArr[counter] = key;
					counter++;
				}
				else if (userWord.toLowerCase().contains(key.toLowerCase())) {
					System.out.println("Were you looking for " + key + "? (If so, enter " + counter + ")");

					similarWordArr[counter] = key;
					counter++;
				}
				
				if (counter >= similarWordArr.length) break;
			}

			Scanner similarWordScanner = new Scanner(System.in);

			int wantedWord = similarWordScanner.nextInt();
			String theWord = similarWordArr[wantedWord];
			// REFACTOR THIS

				Word wordObj = dictionary.get(theWord);

				String germanWord = wordObj.getGermanWord();
				String englishWord = wordObj.getEnglishWord();
				String gender = wordObj.getGender();

			// Case 1.b: Input is German
				if (theWord.equalsIgnoreCase(germanWord)) {
					printEnglishWordInfo(germanWord, englishWord, gender);
				}
				// Case 1.c: Input was in English
				else {
					printGermanWordInfo(germanWord, englishWord, gender);
				}

			return;
		}
}