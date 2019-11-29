package com.sap.ase;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class FizzBuzzTest {
	FizzBuzz fizzBuzz = new FizzBuzz();
	@Test
	public void test_count_to_15() {
		String expect =
				"     _   _    __ __            __ __   _    __ __   _   _   _    __ __        __ __          _    __ __      _          _    __ __       __ __ \n" +
                "|    _| |_  |  /  /  |_| |_ | | /  /  |_  |  /  /    | |_| |_  |  /  /  |_ | | /  /  |  |   |_  |  /  /  |   _| |  |_| |_  |  /  / |_ | | /  / \n" +
                "|  ,|_ ,|   | /_ /_ ,  |,|_||_|/_ /_ ,|   | /_ /_ ,  |,|_|,|   | /_ /_ ,|_||_|/_ /_ ,|  |  ,|   | /_ /_ ,|   _|,|    |,|   | /_ /_ |_||_|/_ /_ ";

		Assertions.assertEquals(expect, fizzBuzz.countTo(15));
	}

}
