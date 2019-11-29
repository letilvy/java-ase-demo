package com.sap.ase;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IsomorphicStringTest {

	private IsomorphicChecker checker = new IsomorphicChecker();

	@Test
	public void empty_strings_is_isomorphic() {
		assertTrue(checker.isIsomorphic("", ""));
	}

	@Test
	public void a_b_is_isomorphic() {
		assertTrue(checker.isIsomorphic("a", "b"));
	}

	@Test
	public void a_and_empty_is_NOT_isomorphic() {
		assertFalse(checker.isIsomorphic("a", ""));
	}

	@Test
	public void ab_ee_is_NOT_isomorphic() {
		assertFalse(checker.isIsomorphic("ab", "ee"));
		assertFalse(checker.isIsomorphic("ee", "ab"));
	}

	@Test
	public void abc_bee_is_NOT_isomorphic() {
		assertFalse(checker.isIsomorphic("abc", "bee"));
	}

	@Test
	public void abc_lol_is_NOT_isomorphic() {
		assertFalse(checker.isIsomorphic("abc", "lol"));
	}
}
