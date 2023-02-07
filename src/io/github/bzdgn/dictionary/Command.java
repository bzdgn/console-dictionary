package io.github.bzdgn.dictionary;

public enum Command {
	
	HELP("help"),
	LIST_WORDS("list"),
	ADD_WORD("add"),
	GET_WORD("get"),
	UPDATE_WORD("update"),
	LIST_VOCABULARY("vocab"),
	SAVE("save"),
	LOAD("load"),
	SET_CONFIG("set_config"),
	SHOW_CONFIG("show_config"),
	QUIT("quit");
	
	public final String value;
	
	private Command(String value) {
		this.value = value;
	}

}
