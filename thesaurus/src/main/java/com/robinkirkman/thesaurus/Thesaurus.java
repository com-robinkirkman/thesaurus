package com.robinkirkman.thesaurus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Thesaurus {

	private static Thesaurus instance;
	
	public static synchronized Thesaurus getInstance() {
		if(instance == null)
			instance = new Thesaurus();
		return instance;
	}

	private Map<String, List<String>> map;
	
	private Thesaurus() {
		map = new HashMap<>();
		try {
			BufferedReader buf = new BufferedReader(new InputStreamReader(Thesaurus.class.getResourceAsStream("mthesaur.txt"), "UTF-8"));
			for(String line = buf.readLine(); line != null; line = buf.readLine()) {
				List<String> group = Collections.unmodifiableList(Arrays.asList(line.split(",")));
				for(String word : group) {
					if(!map.containsKey(word))
						map.put(word, group);
					else {
						List<String> wgroup = new ArrayList<>(map.get(word));
						wgroup.addAll(group);
						map.put(word, Collections.unmodifiableList(wgroup));
					}
				}
			}
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<String> get(String word) {
		return map.get(word);
	}
}
