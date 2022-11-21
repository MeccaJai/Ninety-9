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

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public DrinksController(DrinksService drinksService) {
        this.drinksService = drinksService;
    }

    @GetMapping("/")
    public ArrayList<Drinks> getAllDrinks() throws ExecutionException, InterruptedException {
        return drinksService.getAllDrinks();
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
    public void approveDrink(@PathVariable String id) throws  ExecutionException, InterruptedException {
        drinksService.approveDrink(id);
    }

    @GetMapping("/remove/{id}")
    public void removeDrink(@PathVariable String id) throws ExecutionException, InterruptedException {
        drinksService.removeDrink(id);
    }

    @GetMapping("/edit/{id}/{name}/{info}")
    public void editRecipe(@PathVariable String id, @PathVariable String name, @PathVariable String info) throws  ExecutionException, InterruptedException {
        drinksService.editRecipe(id, name, info);
    }

}
