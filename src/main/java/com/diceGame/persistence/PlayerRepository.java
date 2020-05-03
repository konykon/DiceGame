package com.diceGame.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diceGame.models.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

	List<Player> findAllByOrderBySuccesspctDesc();

	Player findFirstByOrderBySuccesspctDesc();

	Player findFirstByOrderBySuccesspctAsc();



}
