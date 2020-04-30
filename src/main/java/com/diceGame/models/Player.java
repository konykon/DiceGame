package com.diceGame.models;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.NonNull;

@Entity
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "player_id")
	private Long id;

	private String name;

	private LocalDateTime date = LocalDateTime.now();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "player")
	private Set<Game> games;

	@NonNull
	private Integer wins = 0;

	@NonNull
	private double successPct = 0.0;

	public Player() {

	}

	public Player(String name) {
		setName(name);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Set<Game> getGames() {
		return games;
	}

	public void setGames(Set<Game> games) {
		this.games = games;
	}

	public Integer getWins() {
		if (games.size() == 0) {
			wins = 0;
		}
		return wins;
	}

	public void setWins(Integer wins) {
		this.wins = wins;
	}

	public double getSuccessPct() {
		if (games.size() != 0) {
			successPct = (getWins() * 100 / games.size());
		} else {
			successPct = 0.0;
		}
		return successPct;
	}

	public void setSuccessPct(double successPct) {
		this.successPct = successPct;
	}

}
