class Word {
	private String englishWord;
	private String germanWord;
	private char gender; // m - masculine, f - feminine, n - neutral, 0 - no gender

	Word() {
		englishWord = null;
		germanWord = null;
		gender = 0;
	}

	Word(String englishWord, String germanWord, char gender) {
		this.englishWord = englishWord;
		this.germanWord = germanWord;
		this.gender = gender;
	}

	public String getEnglishWord() {
		return this.englishWord;
	}

	public String getGermanWord() {
		return this.germanWord;
	}

	public String getGender() {
		if (this.gender == 'm') 
			return "Der";
		else if (this.gender == 'f') 
			return "Die";
		else if (this.gender == 'n') 
			return "Das";
		else 
			return "N/A";
	}
}