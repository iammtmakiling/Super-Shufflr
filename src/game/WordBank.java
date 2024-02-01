package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import soloGame.GameTimer;

public class WordBank {

	private String[] wordBank = {
		"byte", "code", "data", "file", "loop", "stack", "cache", "queue",
		"logic", "input", "output", "error", "debug", "disk", "shell",
		"alias", "script", "cursor", "index", "memory", "buffer", "socket",
		"kernel", "thread", "mutex", "route", "ping", "proxy", "query",
		"parse", "token", "lexer", "regex", "scope", "class", "object",
		"inherit", "method", "field", "static", "dynamic", "array", "heap",
		"tree", "graph", "node", "edge", "path", "hash", "sort", "search",
		"binary", "matrix", "pixel", "bitmap", "bit", "chip", "state",
		"block", "cookie", "debugs", "encrypt", "format", "hacker", "locale",
		"malware", "modems", "offline", "online", "parser", "plugin",
		"queries", "random", "router", "server", "source", "syntax",
		"vector", "access", "backup", "bridge", "client", "desktop", "driver",
		"execute", "library", "network", "packet", "process", "program",
		"scanner", "storage", "utility"
	};
	private int upperBound = wordBank.length-1;
	private String answer;
	private String shuffled;

	private String shuffle(String toShuffle){
		List<Character> alpha = new LinkedList<>();
        for(char c:toShuffle.toCharArray()){
			alpha.add(c);
        }
        StringBuilder ans = new StringBuilder();
        for (int i=0;i<toShuffle.length();i++){
			int randomPosition = new Random().nextInt(alpha.size());
            ans.append(alpha.get(randomPosition));
            alpha.remove(randomPosition);
        }
        return ans.toString();
	}
	
	public void randomizeWord() {
		Random rand = new Random();
		GameTimer.resetGameCounter();
		this.answer = wordBank[rand.nextInt(upperBound)];
		this.shuffled = this.shuffle(this.answer);

		System.out.println(this.wordBank.length);
		System.out.println(this.answer);
		while (this.answer == this.shuffled){
			this.shuffled = this.shuffle(this.answer);
		}

		List<String> list = new ArrayList<String>(Arrays.asList(wordBank));
		list.remove(this.answer);
		this.upperBound = this.wordBank.length-1;
		this.wordBank = list.toArray(new String[0]);
	}
	public String getShuffled() {
        return shuffled;
	}
	public String getAnswer(){
		return this.answer;
	}
	protected int getLength(){
		return this.answer.length();
	}
	public boolean checkAnswer(String answer){
		if (answer.equalsIgnoreCase(getAnswer())){
			return true;
		} else {
			return false;
		}
	}
}

