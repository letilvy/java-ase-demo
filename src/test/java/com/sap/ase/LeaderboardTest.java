package com.sap.ase;

// Mockito home page: https://site.mockito.org/
/**
 * import mockito as static then you can call method of Mockito directly without prefix "Mockito".
 * Or if you import using "import org.mockito.Mockito;", you have to call method like "Mockito.mock", "Mockito.spy"
*/
import static org.mockito.Mockito.*;  //"import static org.mockito.Mockito.*;" is different from "import static org.mockito.Mockito;" 

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LeaderboardTest {
	/*
	 * Solution 1: Directly override the method needed to be stub
	 *
		public class LeaderboardForTest extends Leaderboard {
			@Override
			public boolean publishHighScoreOfPlayer(Player player, int potSize){
				return true;
			}
		}
		private lb = new LeaderboardForTest();
	*/

	private Leaderboard lb;
	private Player peter;
	private Player cindy;
	private Player dave;
	
	@Before
	public void setup(){
		//lb = new Leaderboard();
		/**
		 * Solution 2: Use Mockito to mock
		 
			lb = spy(Leaderboard.class);
			doReturn(true).when(lb).publishHighScoreOfPlayer(any(Player.class), anyInt()); // Only mock a special method of a class

			 *
			 * The following way will mock the whole class, all methods in the class will be mocked
				lb = mock(Leaderboard.class);
				when(lb.publishHighScoreOfPlayer(any(Player.class), anyInt())).thenReturn(true);

			 * when you call lb.update after the above two statements, you just call an empty method  
			 *
		*/

		/**
		 * 
		 * Solution 3: Law of demeter

		   Both Solution 1 and Solution 2 exposed the implement detail of class 'Leaderboard', 
		   you should know there is a method call 'publishHighScoreOfPlayer' within the class.
		   Solution 3 is to create a new class to handle operation of method 'publishHighScoreOfPlayer'.
		   The operation in 'publishHighScoreOfPlayer' is all about interacting with social media channel,
		   So we can create a new class "SocialMedia" with method "postMessageToFacebook",
		   and mock this class. 

		   Best practice for test stub:
		     Inheritance like solution 1 may still have many dependency on the parent class.
		     So create a new class to move out not-relevant logic and use an instance of this new class
		     in the tested class is better option. 
		 * 
		*/
		//mock(SocialMedia.class).when((any(Player.class), anyInt())).thenReturn(true);
		SocialMedia socialMedia = new SocialMedia();
		socialMedia = mock(SocialMedia.class);
		when(socialMedia.postMessageToFacebook(any(Player.class), anyInt())).thenReturn(true);

		lb = new Leaderboard(socialMedia);

		/**
		 * Solution 4: Make "SocialMedia" as an interface but a class
	
		*/

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
}
