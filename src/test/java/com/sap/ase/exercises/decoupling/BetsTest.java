package com.sap.ase.exercises.decoupling;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class BetsTest {

	private Bets bets;
	
	@Before
	public void setup() {
		Configuration config = mock(Configuration.class);
		when(config.getBetLimit()).thenReturn(10);
		bets = new Bets(config);
	}
	
	@Test(expected = NotEnoughPlayers.class)
	public void noBetPlaced_shouldThrow() {
		bets.areEven();
	}

	@Test(expected = NotEnoughPlayers.class)
	public void oneBetPlaced_shouldThrow() {
		bets.bet("john", 1);
		bets.areEven();
	}

	@Test
	public void givenTwoBetsPlaced_sameAmount() {
		bets.bet("john", 1);
		bets.bet("frank", 1);
		assertThat(bets.areEven(), is(true));
	}

	@Test
	public void givenTwoBetsPlaced_differentAmount() {
		bets.bet("john", 1);
		bets.bet("frank", 2);
		assertThat(bets.areEven(), is(false));
	}
	
	@Test(expected = ExceedsBetLimit.class)
	public void givenBetLimitExceeded_shouldThrow() {
		Configuration config = mock(Configuration.class);
		when(config.getBetLimit()).thenReturn(1);

		Bets bets = new Bets(config);
		bets.bet("john", 2);
	}
}
