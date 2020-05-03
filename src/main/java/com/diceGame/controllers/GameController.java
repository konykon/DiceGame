package com.diceGame.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.diceGame.models.Game;
import com.diceGame.models.Player;
import com.diceGame.persistence.GameRepository;
import com.diceGame.persistence.PlayerRepository;

@RestController
public class GameController {

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private GameRepository gameRepository;
	

	@GetMapping("/players")
	List<Player> allWithSuccessPct() {
		for (Player player : playerRepository.findAll()) {
			int countWin = 0;
			int countLoss = 0;
			int totalGames = 0;
			for (Game game : gameRepository.findAll()) {
				if (player.getId() == game.getPlayer().getId()) {
					if (game.getWin()) {
						countWin++;
					} else {
						countLoss++;
					}
					totalGames = countWin + countLoss;
					player.setSuccessPct((double) ((countWin * 100) / totalGames));
				}
			}
			playerRepository.save(player);
		}
		return playerRepository.findAll();
	}

	@GetMapping("/players/{player_id}/games")
	public List<Game> getAllGamesByPlayerId(@PathVariable Long player_id) {
		return gameRepository.findByPlayerId(player_id);
	}

	@PostMapping("/players/{player_id}/games")
	public Optional<Object> addGame(@PathVariable Long player_id, @Valid @RequestBody Game game) {
		return playerRepository.findById(player_id).map(player -> {
			if (game.getResult() == 7) {
				game.setWin(true);
			} 
			game.setPlayer(player);
			return gameRepository.save(game);
		});
	}

	@DeleteMapping("/players/{player_id}/games")
	public Stream<Object> deleteGames(@PathVariable Long player_id) {
		return gameRepository.findByPlayerId(player_id).stream().map(game -> {
			gameRepository.delete((Game) game);
			return ResponseEntity.ok().build();
		});
	}
	
	
}


