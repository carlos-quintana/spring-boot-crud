package com.carlosquintana.imageboard.controllers.api;

import com.carlosquintana.imageboard.models.dto.PostDTO;
import com.carlosquintana.imageboard.services.data.PostDataAccessData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts/")
public class PostController {

    @Autowired
    private PostDataAccessData service;

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("{id:[0-9]+}")
    public ResponseEntity findById(@PathVariable long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity save(@RequestBody PostDTO submittedPost) {
        return ResponseEntity.ok(service.save(submittedPost));
    }

    @PutMapping("{id:[0-9]+}")
    public ResponseEntity update(@PathVariable long id, @RequestBody PostDTO postToUpdate) {
        System.out.println("Received PUT");
        System.out.println(id);
        System.out.println(postToUpdate);
        return ResponseEntity.ok(service.update(id, postToUpdate));
    }

    @DeleteMapping("{id:[0-9]+}")
    public ResponseEntity delete(@PathVariable long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
