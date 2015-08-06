package com.robinkirkman.thesaurus;

import org.junit.Test;

public class ThesaurusTest {
	@Test
	public void testHi() {
		System.out.println(Thesaurus.getInstance().get("house").size());
	}
}
