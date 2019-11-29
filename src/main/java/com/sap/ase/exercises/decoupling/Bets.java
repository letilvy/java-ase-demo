package com.sap.ase.exercises.decoupling;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Bets {

	private final Map<String, Integer> bets = new HashMap<String, Integer>();
	private Configuration config;
	
	public Bets(Configuration config) {
		this.config = config;
	}
	
	public void bet(String player, int amount) {
		
		int limit = config.getBetLimit();
		
		if (amount > limit) {
			throw new ExceedsBetLimit(amount + " exceeds maximum bet limit of " + limit);
		} else {
			bets.put(player, amount);
		}
	}

	public boolean areEven() throws NotEnoughPlayers {
		if (bets.keySet().size() < 2) {
			throw new NotEnoughPlayers();
		}

		return (new HashSet<>(bets.values()).size() == 1);
	}
}
