package com.sap.ase.exercises.decoupling;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class BetsTest {

	@Test(expected = NotEnoughPlayers.class)
	public void noBetPlaced_shouldThrow() {
		Bets bets = new Bets();
		bets.areEven();
	}

	@Test(expected = NotEnoughPlayers.class)
	public void oneBetPlaced_shouldThrow() {
		Bets bets = new Bets();
		bets.bet("john", 1);
		bets.areEven();
	}

	@Test
	public void givenTwoBetsPlaced_sameAmount() {
		Bets bets = new Bets();
		bets.bet("john", 1);
		bets.bet("frank", 1);
		assertThat(bets.areEven(), is(true));
	}

	@Test
	public void givenTwoBetsPlaced_differentAmount() {
		Bets bets = new Bets();
		bets.bet("john", 1);
		bets.bet("frank", 2);
		assertThat(bets.areEven(), is(false));
	}
}
