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

    @GetMapping("new")
    public String showCreateForm() {
        return "submissions/newForm";
    }

    @PostMapping("new")
    public String createNewSubmission(SubmissionDTO submissionDTO, Model model) {
        long newId = service.save(submissionDTO);
        return "redirect:/submissions/"+newId;
    }

    @GetMapping("edit/{id:[0-9]+}")
    public String showUpdateForm(@PathVariable long id, Model model) {
        // TODO - validate the submission even exists
        SubmissionDTO submissionToUpdate = service.findById(id);
        model.addAttribute("submission", submissionToUpdate);
        return "submissions/updateForm";
    }

    @PutMapping("edit/{id:[0-9]+}")
    public String update(@PathVariable long id, SubmissionDTO submissionToUpdate) {
        service.update(id, submissionToUpdate);
        return "redirect:/submissions/"+id;
    }

    @DeleteMapping("{id:[0-9]+}")
    public String delete(@PathVariable long id, Model model) {
        service.delete(id);
        return "redirect:/submissions/";
    }
}
