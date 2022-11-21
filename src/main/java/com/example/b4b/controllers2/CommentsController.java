package com.example.restaurant.controllers2;

import com.example.restaurant.models2.Comments;
import com.example.restaurant.services2.CommentsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@RequestMapping("/api/comments")
@RestController
public class CommentsController {

    public CommentsService commentsService;

    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @GetMapping("/{id}")
    public ArrayList<Comments> retrieveCommentsbyDrink(@PathVariable String id) throws ExecutionException, InterruptedException {
        return commentsService.retrieveCommentsbyDrink(id);
    }

}
