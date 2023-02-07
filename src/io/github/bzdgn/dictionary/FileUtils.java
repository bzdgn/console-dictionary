package io.github.bzdgn.dictionary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class FileUtils {
	
	public static final String DEFAULT_SEPARATOR = "||";
	public static final String DEFAULT_FILE_NAME = "dictionary.txt";
	public static final String DEFAULT_PATH = "./";
	
	public static void writeDictionaryToFile(Map<String, String> map, Configuration config) throws IOException {
		String filePath = config.getFilePath();
		String fileName = config.getFileName();
		String separator = config.getSeparator();
		
		if (separator == null || separator == "") {
			separator = DEFAULT_SEPARATOR;
		}
		
		if (filePath == null || filePath == "") {
			filePath = DEFAULT_PATH;
		}
		
		if (fileName == null || fileName == "") {
			fileName = DEFAULT_FILE_NAME;
		}
		
	    Path path = Paths.get(filePath + fileName);
	    
	    StringBuilder sb = new StringBuilder();
	    
	    for(String word: map.keySet()) {
	    	sb.append(String.format("%s%s%s", word, separator, map.get(word)));
	    	sb.append(System.lineSeparator());
	    }
	    byte[] strToBytes = sb.toString().getBytes();

	    Files.createDirectories(path.getParent());
	    Files.write(path, strToBytes);
	}
	
	public static void readFromDictionary(Map<String, String> map, Configuration config) throws IOException {
		String filePath = config.getFilePath();
		String fileName = config.getFileName();
		String separator = config.getSeparator();
		
		if (separator == null || separator == "") {
			separator = DEFAULT_SEPARATOR;
		}
		
		if (filePath == null || filePath == "") {
			filePath = DEFAULT_PATH;
		}
		
		if (fileName == null || fileName == "") {
			fileName = DEFAULT_FILE_NAME;
		}
		
		Path path = Paths.get(filePath + fileName);
		
		if (!path.toFile().exists()) {
			System.out.println("File does not exist: " + path.toFile());
			return;
		}
		
		List<String> lines = Files.readAllLines(path);
		
		for(String line: lines) {
			String[] parts = line.split(escapeSeparator(separator));
			
			map.put(parts[0], parts[1]);
		}
	}
	
	private static String escapeSeparator(String separator) {
		return separator.replace("|", "\\|");
	}
	
}
