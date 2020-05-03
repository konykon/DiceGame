package com.diceGame.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.diceGame.models.Player;
import com.diceGame.persistence.PlayerRepository;

@RestController
public class PlayerController {
	
	
	@Autowired
	private PlayerRepository repository;
	
	@PostMapping("/players")
	Player player(@RequestBody Player player) {
		if (player.getName().isEmpty()) {
			player.setName("Anonymous");
		}
		return repository.save(player);
	}
	
	@PutMapping("/players/{id}")
	Player replacePlayer(@RequestBody Player newPlayer, @PathVariable Long id) {
		return repository.findById(id)
			.map(player -> {
				player.setName(newPlayer.getName());
				return repository.save(player);
			})
			.orElseGet(() -> {
				newPlayer.setId(id);
				return repository.save(newPlayer);
			});
	}
	
	@GetMapping("/players/{id}")
	Optional<Player> one(@PathVariable Long id) {
		return repository.findById(id);
	}

	@GetMapping("players/ranking")
	List<Player> orderPlayersbySuccessPct() {
		return repository.findAllByOrderBySuccesspctDesc();
	}
	
	@GetMapping("players/ranking/winner")
	Player findWinner() {
		return repository.findFirstByOrderBySuccesspctDesc();
	}
	
	@GetMapping("players/ranking/loser")
	Player findLoser() {
		return repository.findFirstByOrderBySuccesspctAsc();
	}
	
}