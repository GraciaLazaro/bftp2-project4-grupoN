package com.example.legacygames.controllers;

import com.example.legacygames.repositories.CategoryRepository;
import com.example.legacygames.repositories.Game;
import com.example.legacygames.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
public class GameController {

    private final GameRepository gameRepository;
    private final CategoryRepository categoryRepository;


    @Autowired
    public GameController (GameRepository gameRepository, CategoryRepository categoryRepository){
        this.gameRepository = gameRepository;
        this.categoryRepository = categoryRepository;

    }

    @GetMapping("/games")
    String listGames (Model model, @RequestParam(required = false) String category){
        List<Game> games =  gameRepository.findAll();
        model.addAttribute("title", "Game list");
        model.addAttribute("games", games);
        return "games/all";
    }


    @GetMapping("/games/new")
    String getForm(Model model){

        Game game = new Game();
        model.addAttribute("game", game);
        model.addAttribute("title", "Game list");

        return "games/edit";
    }

    @PostMapping("/games/new")
    String addGame(@ModelAttribute Game game) {
        gameRepository.save(game);
        return "redirect:/games";
    }

    @GetMapping("/games/edit/{id}")
    String editBook(Model model, @PathVariable Long id){
        Game game = gameRepository.findById(id).get();
        model.addAttribute("game", game);
        model.addAttribute("title", "Edit game");
        return "games/edit";
    }

   @GetMapping("/games/delete/{id}")
    String remove(@PathVariable Long id){
        gameRepository.deleteById(id);
        return "redirect:/games";
   }


}



