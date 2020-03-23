package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Model {
	private List<String> dictionary;
	private List<RichWord> words;
	private String language;
	private List<RichWord> correctWords;
	
	public Model() {
		this.dictionary = new LinkedList<String>();
		this.words = new LinkedList<RichWord>();
		this.language = new String();
		this.correctWords = new LinkedList<RichWord>();
	}

	private void loadDictionary(String language) {		
		// Clear dictionary if language changed
		if (this.language.equals(language))
			return;
		else
			dictionary.clear();
		
		// Saving dictionary from selected file to list
		try {
			FileReader fr = new FileReader("src/main/resources/" + language + ".txt");
			BufferedReader br = new BufferedReader(fr);
			String word;
			
			while((word = br.readLine()) != null) 
				dictionary.add(word.toLowerCase());
			
			br.close();
			
		// Check for IO Errors
		} catch (IOException e) {
			System.out.println("Errore nella lettura del file: " + e);
		}
		
		// Set default language for instance, useful for next requests
		this.language = language;
	}
	
	private void loadText(String language, String inputText) {
		// Set Dictionary
		this.loadDictionary(language);
		
		// Clean Text Cache
		words.clear();

		// Operations on input
		String inputTexto = inputText.replaceAll("[^a-zA-Z\\s]", "");
		// Save on List with tokenizer
		StringTokenizer token = new StringTokenizer(inputTexto);
		
		// Save from string to richWord using Tokenizer, and lower case String part of richWord in order to check properly dictionary
		// Dictionary parameter call to same reference, in order to not have many copies of same object
		while (token.hasMoreTokens()) 
			 words.add(new RichWord(token.nextToken().toLowerCase(), dictionary));
		 
	}
	
	public List<RichWord> spellCheckText(String language, String inputTextList) {
		// Clear List (Cache) of correct Words to load another request and not conflict on previous
		this.correctWords.clear();
		
		// Loading required text for check Spell
		this.loadText(language, inputTextList);
		
		// Add to correctWords List richWord which didn't match with dictionary (correctWord spell can confuse, but I left it because on previous
		// version I checked differently which words didn't match dictionary.
		for (RichWord rw : words) 
			if ((rw!= null) && !rw.isCorrect()) 
				correctWords.add(rw);
		
		// return words that don't match with dicionary words: (in)correct words
		return correctWords;
	}
	

	public String toString(List<RichWord> input) {
		// Stringize output List created with spellCheckText function -> on Controller: model.toString(model.spellCheckText(language, inputTextList));
		
		StringBuffer stringa = new StringBuffer();
		for (RichWord r : input)
			if (r != null)
				stringa.append(r.toString() + "\n");

		return stringa.toString();
	}

	public String getWrongWords() {
		return "The text contains " + correctWords.size() + " errors.";
	}
}
