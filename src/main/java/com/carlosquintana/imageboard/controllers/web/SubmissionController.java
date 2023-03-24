package com.carlosquintana.imageboard.controllers.web;

import com.carlosquintana.imageboard.models.dto.SubmissionDTO;
import com.carlosquintana.imageboard.services.data.SubmissionDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/submissions/")
public class SubmissionController {

    @Autowired
    private SubmissionDataAccess service;

    @GetMapping
    public String findAll(Model model) {
        List<SubmissionDTO> allSubmissions = service.findAll();
        // fix the tags spacing so that the page can be visualized properly
        // This can be removed once the dabatabase records are normalized and the create and update forms are working to fix this problem
        for(SubmissionDTO submission: allSubmissions){
            String newTags = "";
            for (String tag: submission.getTags().split(","))
                newTags += tag.trim().toLowerCase()+", ";
            submission.setTags(newTags);
        }
        model.addAttribute("submissions", allSubmissions);
        return "submissionsListing";
    }

    @GetMapping("{id:[0-9]+}")
    public String findById(@PathVariable long id, Model model) {
        SubmissionDTO submission = service.findById(id);
        model.addAttribute("submission", submission);

        List<String> tags = new ArrayList<String>();
        for (String tag: submission.getTags().split(","))
            tags.add(tag.trim());
        model.addAttribute("tags", tags);

        return "submissions/submissionView";
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
