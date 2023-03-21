package com.carlosquintana.imageboard.controllers.api;

import com.carlosquintana.imageboard.models.dto.SubmissionDTO;
import com.carlosquintana.imageboard.services.data.SubmissionDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/submissions/")
public class SubmissionController {

    @Autowired
    private SubmissionDataAccess service;

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("{id:[0-9]+}")
    public ResponseEntity findById(@PathVariable long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity save(@RequestBody SubmissionDTO newSubmission) {
        return ResponseEntity.ok(service.save(newSubmission));
    }

    @PutMapping("{id:[0-9]+}")
    public ResponseEntity update(@PathVariable long id, @RequestBody SubmissionDTO submissionToUpdate) {
        System.out.println("Received PUT");
        System.out.println(id);
        System.out.println(submissionToUpdate);
        return ResponseEntity.ok(service.update(id, submissionToUpdate));
    }

    @DeleteMapping("{id:[0-9]+}")
    public ResponseEntity delete(@PathVariable long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
