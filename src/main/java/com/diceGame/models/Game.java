package com.diceGame.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Game {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long dice1;
	private Long dice2;
	private Long result;
	
	@ManyToOne
    @JoinColumn(name = "player_id")
	@JsonIgnore
	private Player player;
	
	public Game() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDice1() {
		return dice1;
	}

	public void setDice1(Long dice1) {
		this.dice1 = dice1;
	}

	public Long getDice2() {
		return dice2;
	}

	public void setDice2(Long dice2) {
		this.dice2 = dice2;
	}

	public Long getResult() {
		result = dice1 + dice2;
		return result;
	}

	public void setResult(Long result) {
		this.result = result;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	};

	
	
}
