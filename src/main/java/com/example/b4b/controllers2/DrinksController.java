package com.example.restaurant.controllers2;

import com.example.restaurant.models2.Drinks;
import com.example.restaurant.services2.DrinksService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RequestMapping("/api/drinks")
@RestController
public class DrinksController {

    public DrinksService drinksService;

    public DrinksController(DrinksService drinksService) {
        this.drinksService = drinksService;
    }

    @GetMapping("/")
    public ArrayList<Drinks> getAllDrinks() throws ExecutionException, InterruptedException {
        return drinksService.getAllDrinks();
    }

    @GetMapping("/{query}")
    public ArrayList<Drinks> getAllForSearch(@PathVariable String query) throws  ExecutionException, InterruptedException {
        return drinksService.getAllForSearch(query);
    }

    @GetMapping("/{id}")
    public Drinks getDrinkByID(@PathVariable Integer id) throws ExecutionException, InterruptedException {
        return drinksService.getDrinkbyID(id);
    }

    @GetMapping("/{name}")
    public Drinks getDrinkbyName(@PathVariable String name) throws ExecutionException, InterruptedException {
        return drinksService.getDrinkbyName(name);
    }

    @GetMapping("/catcount")
    public Map<String, Integer> getCategoryCount() throws ExecutionException, InterruptedException {
        return drinksService.getCategoryCount();
    }

    @GetMapping("/approve/{id}")
    public void approveDrink(@PathVariable Integer id) throws  ExecutionException, InterruptedException {
        drinksService.approveDrink(id);
    }

    @GetMapping("/remove/{id}")
    public void removeDrink(@PathVariable Integer id) throws ExecutionException, InterruptedException {
        drinksService.removeDrink(id);
    }

    @GetMapping("/edit/{id}/{name}/{info}")
    public void editRecipe(@PathVariable Integer id, @PathVariable String name, @PathVariable String info) throws  ExecutionException, InterruptedException {
        drinksService.editRecipe(id, name, info);
    }

    @GetMapping("/history/{id}")
    public ArrayList<Drinks> drinkHistory(@PathVariable String username) throws ExecutionException, InterruptedException {
        return drinksService.drinkHistory(username);
    }
    @GetMapping("/{id}/{newRate}")
    public void updateRating(@PathVariable String id, @PathVariable Double newRate) throws ExecutionException, InterruptedException {
        drinksService.updateRating(id, newRate);
    }
    @GetMapping("/unnaproved")
     public ArrayList<Drinks> displayUNPROV() throws ExecutionException, InterruptedException {
        return drinksService.displayUNPROV();
     }
}
