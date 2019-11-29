package com.sap.ase;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

public class LeaderboardTest {

	private Leaderboard lb;
	private Player peter;
	private Player cindy;
	private Player dave;
	
	@Before
	public void setup() {
		lb = new Leaderboard(new DummySocialMedia(), new DummyLogger());
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
		SpyLogger spyLogger = new SpyLogger();
		lb = new Leaderboard(new FailingSocialMedia(), spyLogger);
		lb.update(peter, 1000);
		assertThat(spyLogger.loggedException.getMessage(), is("connection refused"));
	}
	
	private class DummySocialMedia implements SocialMedia {
		@Override
		public Response post(int potSize) {
			return null;
		}
	}
	
	private class FailingSocialMedia implements SocialMedia {
		@Override
		public Response post(int potSize) {
			throw new RuntimeException("connection refused");
		}
	}
	
	private class DummyLogger implements Logger {
		@Override
		public void log(RuntimeException e) {
		}
	}
	
	private class SpyLogger implements Logger {
		private RuntimeException loggedException;
		
		@Override
		public void log(RuntimeException e) {
			loggedException = e;
		}
	}
}
