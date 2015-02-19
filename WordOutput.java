import java.util.*;
public class WordOutput {


	public static void outputWordInfo(String userWord) {

		Map<String, Word> dictionary = App.getDictionary();

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
				checkSimilarWords(userWord, dictionary);
			}

			System.out.println();
			System.out.println("---------------------------------------------");
			System.out.println();
			return;
		}

		public static void printEnglishWordInfo(String germanWord, String englishWord, String gender) {

			System.out.println();

			boolean writeGender = true; // if true, will print out gender article
			if (gender.equals("N/A")) writeGender = false; // no gender, set it to false

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
			for (String key : dictionary.keySet()) {
				if (key.toLowerCase().contains(userWord.toLowerCase()))
					System.out.println("Were you looking for " + key + "?");
				else if (userWord.toLowerCase().contains(key.toLowerCase()))
					System.out.println("Were you looking for " + key + "?");
			}
			return;
		}
}