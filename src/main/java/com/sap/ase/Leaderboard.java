package com.sap.ase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leaderboard {
	private Map<Player, Integer> scoresByPlayer = new HashMap<Player, Integer>();

	private SocialMedia socialMedia;
	private Logger logger;
	
	public Leaderboard(SocialMedia socialMedia, Logger logger) {
		this.socialMedia = socialMedia;
		this.logger = logger;
	}
	
	public void update(Player player, int potSize) {
		if (!scoresByPlayer.containsKey(player) || (potSize > scoresByPlayer.get(player))) {
			scoresByPlayer.put(player, potSize);
		}

		try {
			socialMedia.post(potSize);
		} catch(RuntimeException e) {
			logger.log(e);
		}
	}

	public int getPositionOfPlayer(Player player) {
		Integer playerScore = scoresByPlayer.get(player);
		playerScore = (playerScore != null) ? playerScore : Integer.MIN_VALUE;
		
		List<Integer> scores = new ArrayList<Integer>(scoresByPlayer.values());
		Collections.sort(scores, Collections.reverseOrder());

		int position = 1;
			
		for (int i = 0; i < scores.size(); i++) {
			int score = scores.get(i);
			if (i == 0 || scores.get(i) < scores.get(i - 1)) {
				position = i + 1;
			}
			if (playerScore == score) {
				return position;
			}
		}
		return scoresByPlayer.size() + 1;
	}
}
