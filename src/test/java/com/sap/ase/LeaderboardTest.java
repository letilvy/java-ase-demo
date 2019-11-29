package com.sap.ase;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class LeaderboardTest {

	private Leaderboard lb;
	private Player peter;
	private Player cindy;
	private Player dave;
	
	@Before
	public void setup() {
		lb = new Leaderboard(mock(SocialMedia.class), mock(Logger.class));
		peter = new Player(0, "Peter");
		cindy = new Player(1, "Cindy");
		dave = new Player(2, "Dave");
	}

	@Test
	public void onePlayer() {
		lb.update(peter, 1000);
		assertThat(lb.getPositionOfPlayer(peter), is(1));
	}

	@Test
	public void twoPlayers() {
		lb.update(peter, 1000);
		lb.update(cindy, 2000);
		assertThat(lb.getPositionOfPlayer(peter), is(2));
		assertThat(lb.getPositionOfPlayer(cindy), is(1));
	}

	@Test
	public void twoPlayers_multipleUpdatesForOnePlayer() {
		lb.update(peter, 1000);
		lb.update(cindy, 2000);
		lb.update(peter, 3000);
		assertThat(lb.getPositionOfPlayer(peter), is(1));
		assertThat(lb.getPositionOfPlayer(cindy), is(2));
	}
	
	@Test
	public void twoPlayers_oneNoScore() {
		lb.update(peter, 1000);
		assertThat(lb.getPositionOfPlayer(cindy), is(2));
	}
	
	@Test
	public void threePlayers_bottomTwoWithSameScore() {
		lb.update(peter, 1000);
		lb.update(cindy, 2000);
		lb.update(dave, 1000);
		assertThat(lb.getPositionOfPlayer(peter), is(2));
		assertThat(lb.getPositionOfPlayer(cindy), is(1));
		assertThat(lb.getPositionOfPlayer(dave), is(2));
	}
	
	@Test
	public void threePlayers_topTwoWithSameScore() {
		lb.update(peter, 2000);
		lb.update(cindy, 1000);
		lb.update(dave, 2000);
		assertThat(lb.getPositionOfPlayer(peter), is(1));
		assertThat(lb.getPositionOfPlayer(cindy), is(3));
		assertThat(lb.getPositionOfPlayer(dave), is(1));
	}
	
	@Test
	public void threePlayers_twoWithIdenticalScore_thirdNoScore() {
		lb.update(peter, 1000);
		lb.update(cindy, 1000);
		assertThat(lb.getPositionOfPlayer(dave), is(3));
	}
	
	@Test
	public void socialMediaUpdateFails_shouldLog() {
		Logger logger = mock(Logger.class);
		
		SocialMedia socialMedia = mock(SocialMedia.class);
		RuntimeException error = new RuntimeException("connection refused");
		when(socialMedia.post(anyInt())).thenThrow(error);
		
		lb = new Leaderboard(socialMedia, logger);
		lb.update(peter, 1000);
		
		verify(logger).log(error);
	}
}
