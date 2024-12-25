package com.example.video_games.controller;

import com.example.video_games.exception.InvalidGameDataException;
import com.example.video_games.exception.RessourceNotFoundException;
import com.example.video_games.model.Game;
import com.example.video_games.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @GetMapping
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @PostMapping
    public Game createGame(@RequestBody Game game) {
        validatePrice(game.getPrice(), "Price");
        validateCategory(game.getCategory(), "Category");
        return gameRepository.save(game);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable int id) {
        Game game = gameRepository.findById(id).orElseThrow(() -> new RessourceNotFoundException("Game not found with id: " + id));
        return ResponseEntity.ok(game);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable int id, @RequestBody Game gameDetails) {
        Game updateGame = gameRepository.findById(id).orElseThrow(() -> new RessourceNotFoundException("Game not found with id: " + id));

        validateCategory(gameDetails.getCategory(), "Category");
        validatePrice(gameDetails.getPrice(), "Price");

        updateGame.setCategory(gameDetails.getCategory());
        updateGame.setPrice(gameDetails.getPrice());
        updateGame.setId(gameDetails.getId());

        return ResponseEntity.ok(gameRepository.save(updateGame));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteGame(@PathVariable int id) {
        Game game = gameRepository.findById(id).orElseThrow(() -> new RessourceNotFoundException("Game not found with id: " + id));
        gameRepository.delete(game);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void validatePrice(Integer price, String fieldName) {
        if (price == null || price <= 0) {
            throw new InvalidGameDataException(fieldName + " must be a positive number");
        }
    }

    private void validateCategory(String category, String fieldName) {
        if (category == null || category.trim().isEmpty()) {
            throw new InvalidGameDataException(fieldName + " cannot be empty");
        }
    }
}
