import java.util.HashMap;

public class CaseInsensitiveMap extends HashMap<String, Word> {
	
	@Override
	public Word put(String key, Word value) {
		return super.put(key.toLowerCase(), value);
	}

	public Word get(String key) {
		return super.get(key.toLowerCase());
	}
}