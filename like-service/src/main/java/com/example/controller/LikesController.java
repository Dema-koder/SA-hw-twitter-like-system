package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/likes")
public class LikesController {
    private final Map<String, Set<String>> likes = new HashMap<>();

    @Autowired
    private RestTemplate restTemplate;

    private final String userServiceUrl = "http://user-service:8080/users/";
    private final String messageServiceUrl = "http://message-service:8081/messages/";

    @PostMapping("/{messageId}")
    public ResponseEntity<String> likeMessage(@PathVariable String messageId, @RequestParam("username") String username) {
        ResponseEntity<String> userResponse = restTemplate.getForEntity(userServiceUrl + username, String.class);
        if (!userResponse.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.status(404).body("User not found");
        }
        ResponseEntity<String> messageResponse = restTemplate.getForEntity(messageServiceUrl + messageId, String.class);
        if (!messageResponse.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.status(404).body("Message not found");
        }
        likes.putIfAbsent(messageId, new HashSet<>());
        likes.get(messageId).add(username);
        return ResponseEntity.ok("Liked successfully");
    }

    @GetMapping("/{messageId}")
    public ResponseEntity<Set<String>> getLikes(@PathVariable String messageId) {
        Set<String> userLikes = likes.getOrDefault(messageId, Collections.emptySet());
        return ResponseEntity.ok(userLikes);
    }
}
