package com.diceGame.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diceGame.models.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

	List<Game> findByPlayerId(Long player_id);

}
