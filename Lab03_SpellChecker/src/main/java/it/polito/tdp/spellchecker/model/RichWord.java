package it.polito.tdp.spellchecker.model;

import java.util.*;

public class RichWord {
	private String word;
	private boolean correct;	
	
	public RichWord(String word, List<String> dictionary) {
		this.word = word;
		this.correct = this.linearCheckPresence(dictionary);
	}
	
	public String getWord() {
		return word;
	}
	public boolean isCorrect() {
		return correct;
	}

	@Override
	public String toString() {
		return word;
	}
	public boolean checkPresence(List<String> dictionary) {
		if(dictionary.contains(word))
			return true;
		else 
			return false;
	}
	
	public boolean linearCheckPresence(List<String> dictionary) {
		for(String k : dictionary)
			if (k.equals(word))
				return true;
		
		return false;
	}
	
	public boolean dichotomicCheckPresence(List<String> dictionary) {					
		int result = Collections.binarySearch(dictionary, this.word);
		
		if (result >= 0)
			return true;
		
		return false;
		
	}
	
	
	
}
