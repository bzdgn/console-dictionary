package io.github.bzdgn.dictionary;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Dictionary {
	
	private Configuration config = null;
	
	private Map<String, String> dictionary;
	
	public Dictionary() {
		this(Configuration.defaultConfig());
	}
	
	public Dictionary(Configuration config) {
		this.config = config;
		this.dictionary = new TreeMap<>();
	}
	
	public void add(String word, String definition) {
		if (dictionary.get(word) != null) {
			System.out.printf("[Not adding word] Word exists: %s\n", dictionary.get(word));
		} else {
			dictionary.put(word, definition);
		}
	}
	
	public void update(String word, String definition) {
		if (dictionary.get(word) == null) {
			System.out.printf("[Not updating word] Word does not exist %s\n", dictionary.get(word));
		} else {
			dictionary.put(word, definition);
		}
	}
	
	public String getWordDefinition(String word) {
		return dictionary.get(word);
	}
	
	public void listWords() {
		if (dictionary.keySet().isEmpty()) {
			System.out.println("Vocabulary is empty");
			return;
		}
		
		System.out.println("Word List");
		System.out.println("------------");
		for(String key : dictionary.keySet()) {
			System.out.println(key);
		}
		System.out.println("------------");
	}
	
	public void listVocabulary() {
		if (dictionary.keySet().isEmpty()) {
			System.out.println("Vocabulary is empty");
			return;
		}
		
		System.out.println("Word List");
		System.out.println("------------");
		for(String key : dictionary.keySet()) {
			System.out.println(wordDefinition(key, dictionary.get(key)));
		}
		System.out.println("------------");
	}
	
	public void save() throws IOException {
		if (dictionary.keySet().isEmpty()) {
			System.out.println("Vocabulary is empty");
			return;
		}
		FileUtils.writeDictionaryToFile(dictionary, config);
	}
	
	public void load() throws IOException {
		FileUtils.readFromDictionary(dictionary, config);
	}
	
	public void setConfig(Configuration config) {
		this.config = config;
	}
	
	public Configuration getConfig() {
		return this.config;
	}
	
	public static String wordDefinition(String word, String definition) {
		return String.format("%s\t%s", word, definition);
	}
	
}
