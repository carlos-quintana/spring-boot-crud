package com.carlosquintana.imageboard.controllers.web;

import com.carlosquintana.imageboard.models.dto.SubmissionDTO;
import com.carlosquintana.imageboard.services.data.SubmissionDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/submissions/")
public class SubmissionController {

    @Autowired
    private SubmissionDataAccess service;

    @GetMapping
    public String findAll(Model model) {
        List<SubmissionDTO> allSubmissions = service.findAll();
        model.addAttribute("submissions", allSubmissions);
        return "submissionsListing";
    }

    // TODO - implement
    @GetMapping("{id:[0-9]+}")
    public ResponseEntity findById(@PathVariable long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    // TODO - implement
    @PostMapping
    public ResponseEntity save(@RequestBody SubmissionDTO newSubmission) {
        return ResponseEntity.ok(service.save(newSubmission));
    }

    // TODO - implement
    @PutMapping("{id:[0-9]+}")
    public ResponseEntity update(@PathVariable long id, @RequestBody SubmissionDTO submissionToUpdate) {
        System.out.println("Received PUT");
        System.out.println(id);
        System.out.println(submissionToUpdate);
        return ResponseEntity.ok(service.update(id, submissionToUpdate));
    }

    // TODO - implement
    @DeleteMapping("{id:[0-9]+}")
    public ResponseEntity delete(@PathVariable long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
