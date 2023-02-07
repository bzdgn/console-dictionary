package io.github.bzdgn.dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * https://support.smartbear.com/alertsite/docs/monitors/web/selenium/export-eclipse-java-project-as-runnable-jar.html
 * 
 * export -> runnable jar -> package required libraries into generated Jar -> java -jar <file>
 */
public class Main {

	private static String WELCOME = """
			Please enter a command [help, list, add, update, vocab, save, load, set_config, show_config, quit];
			""";

	private static String HELP = """
			help			List commands
			list			List words
			add     		Add a new word definition
			update			Update an existing word definition
			vocab			List vocabulary
			quit			Quit program
			save			Save to file
			load			Load from file
			set_config		Configure file path, name and separator
			show_config		Show current configuration
			""";

	public static void main(String[] args) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		String word = null;
		String definition = null;
		Dictionary dictionary = new Dictionary();
		try {
			while (!input.equalsIgnoreCase(Command.QUIT.value)) {
				showMenu();
				input = in.readLine();
				if (input.equals(Command.HELP.value)) {
					showHelp();
				} else if (input.equals(Command.LIST_WORDS.value)) {
					dictionary.listWords();
				} else if (input.equals(Command.ADD_WORD.value)) {
					System.out.println("Add word");

					System.out.println("Please enter word");
					word = in.readLine();
					System.out.println("Please enter definition");
					definition = in.readLine();

					dictionary.add(word, definition);
					word = null;
					definition = null;
				} else if (input.equals(Command.GET_WORD.value)) {
					System.out.println("Get word");
					System.out.println("Please enter the word");
					word = in.readLine();
					System.out.println(dictionary.getWordDefinition(word));
					word = null;
				} else if (input.equals(Command.UPDATE_WORD.value)) {
					System.out.println("Update word");
					System.out.println("Please enter the word");
					word = in.readLine();
					System.out.println("Please enter the definition");
					definition = in.readLine();
					dictionary.update(word, definition);
					word = null;
					definition = null;
				} else if (input.equals(Command.LIST_VOCABULARY.value)) {
					System.out.println("List words");
					dictionary.listVocabulary();
				} else if (input.equals(Command.SAVE.value)) {
					dictionary.save();
				} else if (input.equals(Command.LOAD.value)) {
					dictionary.load();
				} else if (input.equals(Command.SET_CONFIG.value)) {
					Configuration config = Configuration.ConfigBuilder();
					
					System.out.println("Please enter filePath");
					config.filePath(in.readLine().trim());
					
					System.out.println("Please enter fileName");
					config.fileName(in.readLine().trim());
					
					System.out.println("Please enter separator");
					config.separator(in.readLine().trim());
					
					System.out.println("Configuration set: " + config);
					dictionary.setConfig(config);
				} else if (input.equals(Command.SHOW_CONFIG.value)) {
					System.out.println("Configuration: " + dictionary.getConfig());
				} else if (input.equals(Command.QUIT.value)) {
					System.out.println("Quitting");
				} else {
					System.out.println("Unrecognized command: " + input);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void showMenu() {
		System.out.println(WELCOME);
	}

	private static void showHelp() {
		System.out.println(HELP);
	}

}
