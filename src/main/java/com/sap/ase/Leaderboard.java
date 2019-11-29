package com.sap.ase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class Leaderboard {
	private Map<Player, Integer> scoresByPlayer = new HashMap<Player, Integer>();

	public void update(Player player, int potSize) {
		if (!scoresByPlayer.containsKey(player) || (potSize > scoresByPlayer.get(player))) {
			scoresByPlayer.put(player, potSize);
		}

		// post message to my facebook wall
		String message = "I've won " + potSize + " chips in ASE POKER!";
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://sapfacebook9999.com/api/wall");
		webTarget.request().post(Entity.entity(message, MediaType.TEXT_PLAIN));
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
