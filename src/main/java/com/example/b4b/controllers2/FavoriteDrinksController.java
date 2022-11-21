package com.example.restaurant.controllers2;

import com.example.restaurant.models2.Drinks;
import com.example.restaurant.services2.FavoriteDrinksService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@RequestMapping("/api/favdrinks")
@RestController
public class FavoriteDrinksController {


    public FavoriteDrinksService favoriteDrinksService;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public FavoriteDrinksController(FavoriteDrinksService favoriteDrinksService) {
        this.favoriteDrinksService = favoriteDrinksService;
    }

    @GetMapping("/{id}")
    public ArrayList<Drinks> getFavsbyUserID(@PathVariable String id) throws ExecutionException, InterruptedException {
        return favoriteDrinksService.getFavsbyUserID(id);
    }

    @GetMapping("/{id}/{id2}")
    public void addToFavs(@PathVariable String id, String id2) throws ExecutionException, InterruptedException {
        favoriteDrinksService.addToFavs(id, id2);
    }

}
