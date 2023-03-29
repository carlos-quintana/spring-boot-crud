package com.carlosquintana.imageboard.controllers.web;

import com.carlosquintana.imageboard.models.dto.SubmissionDTO;
import com.carlosquintana.imageboard.models.entities.CategoryEntity;
import com.carlosquintana.imageboard.services.data.CategoryDataAccess;
import com.carlosquintana.imageboard.services.data.SubmissionDataAccess;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/submissions/")
public class SubmissionController {

    @Autowired
    private SubmissionDataAccess service;
    @Autowired
    private CategoryDataAccess categoryService;


    @GetMapping
    public String showListing(Model model) {
        List<SubmissionDTO> allSubmissions = service.findAll();
        // fix the tags spacing so that the page can be visualized properly
        // This can be removed once the database records are normalized and the create and update forms are working to fix this problem
        for (SubmissionDTO submission : allSubmissions) {
            String newTags = "";
            for (String tag : submission.getTags().split(","))
                newTags += tag.trim().toLowerCase() + ", ";
            submission.setTags(newTags);
        }
        model.addAttribute("submissions", allSubmissions);
        return "submissions/submissionsListing";
    }


    @GetMapping("{id:[0-9]+}")
    public String findById(@PathVariable long id, Model model) {
        SubmissionDTO submission = service.findById(id);
        model.addAttribute("submission", submission);

        List<String> tags = new ArrayList<String>();
        for (String tag : submission.getTags().split(","))
            tags.add(tag.trim());
        model.addAttribute("tags", tags);

        return "submissions/submissionView";
    }

    @GetMapping("new")
    public String showCreateForm(Model model) {
        List<CategoryEntity> categories = categoryService.findAll();
        model.addAttribute("submission", new SubmissionDTO());
        model.addAttribute("categories", categories);
        return "submissions/newForm";
    }

    @PostMapping("new")
    public String createNewSubmission(
            @Valid
            @ModelAttribute("submission")
            SubmissionDTO submissionDTO,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            // Send the user input back to the form
            model.addAttribute("submission", submissionDTO);
            // To fill the categories dropdown
            model.addAttribute("categories", categoryService.findAll());
            return "submissions/newForm";
        }
        long newId = service.save(submissionDTO);
        return "redirect:/submissions/" + newId;
    }

    @GetMapping("edit/{id:[0-9]+}")
    public String showUpdateForm(@PathVariable long id, Model model) {
        // TODO - validate the submission even exists
        SubmissionDTO submissionToUpdate = service.findById(id);
        model.addAttribute("submission", submissionToUpdate);
        List<CategoryEntity> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "submissions/updateForm";
    }

    @PutMapping("edit/{id:[0-9]+}")
    public String update(
            @PathVariable long id,
            @Valid
            @ModelAttribute("submission")
            SubmissionDTO submissionDTO,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            // Send the user input back to the form
            model.addAttribute("submission", submissionDTO);
            // To fill the categories dropdown
            model.addAttribute("categories", categoryService.findAll());
            return "submissions/updateForm";
        }
        service.update(id, submissionDTO);
        return "redirect:/submissions/" + id;
    }

    @DeleteMapping("{id:[0-9]+}")
    public String delete(@PathVariable long id, Model model) {
        service.delete(id);
        return "redirect:/submissions/";
    }
}
