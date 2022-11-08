package com.example.restaurant.controllers2;

import com.example.restaurant.models2.Messages;
import com.example.restaurant.services2.MessagesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@RequestMapping("/api/messages")
@RestController
public class MessagesController {
    public MessagesService messagesService;

    public MessagesController(MessagesService messagesService) {
        this.messagesService = messagesService;
    }

    @GetMapping("/{id}")
    public ArrayList<Messages> retrieveMessagesbyAdmin(@PathVariable String id) throws ExecutionException, InterruptedException {
        return messagesService.retrieveMessagesbyAdmin(id);
    }
}
