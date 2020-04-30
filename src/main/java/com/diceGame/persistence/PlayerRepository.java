package com.diceGame.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diceGame.models.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
