package com.sap.ase.exercises.decoupling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Bets {

	private final Map<String, Integer> bets = new HashMap<String, Integer>();

	public void bet(String player, int amount) {
		try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5740/poker");
				Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("select value from configuration where key = 'bet_limit'");
			rs.next();
			int limit = Integer.parseInt(rs.getString("bet_limit"));
			if (amount > limit) {
				throw new ExceedsBetLimit(amount + " exceeds maximum bet limit of " + limit);
			} else {
				bets.put(player, amount);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean areEven() throws NotEnoughPlayers {
		if (bets.keySet().size() < 2) {
			throw new NotEnoughPlayers();
		}

		return (new HashSet<>(bets.values()).size() == 1);
	}
}
