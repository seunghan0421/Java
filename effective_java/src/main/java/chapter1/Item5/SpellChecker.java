package chapter1.Item5;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SpellChecker {
	private final Dictonary dictonary;

	public SpellChecker(Dictonary dictonary) {
		this.dictonary = Objects.requireNonNull(dictonary);
	}

	public boolean isValid(String word) {
		//...
		return true;
	}

	public List<String> suggestions(String typo) {
		//...
		return new ArrayList<>();
	}
}

class Dictonary {
}

