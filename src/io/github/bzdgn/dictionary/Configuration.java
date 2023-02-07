package io.github.bzdgn.dictionary;

public class Configuration {
	
	public static final String DEFAULT_FILEPATH = "./";
	public static final String DEFAULT_FILENAME = "dictionary.txt";
	public static final String DEFAULT_SEPARATOR = "||";
	
	private String fileName;
	private String filePath;
	private String separator;
	
	// TODO: better builder pattern
	public static Configuration ConfigBuilder() {
		return new Configuration();
	}
	
	public static Configuration defaultConfig() {
		return ConfigBuilder().fileName(null).filePath(null).separator(null);
	}
	
	private Configuration() {}
	
	public Configuration filePath(String filePath) {
		if (filePath == null || filePath == "") {
			this.filePath = DEFAULT_FILEPATH;
		} else {
			this.filePath = filePath;
		}
		
		return this;
	}
	
	public Configuration fileName(String fileName) {
		if (fileName == null || fileName == "") {
			this.fileName = DEFAULT_FILENAME;
		} else {
			this.fileName = fileName;
		}
		
		return this;
	}
	
	public Configuration separator(String separator) {
		if (separator == null || separator == "") {
			this.separator = DEFAULT_SEPARATOR;
		} else {
			this.separator = separator;
		}
		
		return this;
	}

	public String getFileName() {
		return fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public String getSeparator() {
		return separator;
	}

	@Override
	public String toString() {
		return "Configuration [fileName=" + fileName + ", filePath=" + filePath + ", separator=" + separator + "]";
	}
	
}
