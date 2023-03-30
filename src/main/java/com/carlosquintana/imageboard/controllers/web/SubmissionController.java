package com.carlosquintana.imageboard.controllers.web;

import com.carlosquintana.imageboard.models.dto.SubmissionDTO;
import com.carlosquintana.imageboard.models.entities.CategoryEntity;
import com.carlosquintana.imageboard.services.business.SubmissionService;
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
    private SubmissionService submissionService;
    @Autowired
    private CategoryDataAccess categoryService;


    @GetMapping
    public String showListing(Model model) {
        List<SubmissionDTO> allSubmissions = submissionService.findAll();
        model.addAttribute("submissions", allSubmissions);
        return "submissions/submissionsGrid";
    }

    @GetMapping("{id:[0-9]+}")
    public String findById(@PathVariable long id, Model model) {

        SubmissionDTO submission = submissionService.findById(id);
        model.addAttribute("submission", submission);

        // Extract the tags into a list to give to the view to make the pills
        List<String> tags = submissionService.parseOutputTags(submission.getTags());
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
        long newId = submissionService.save(submissionDTO);
        return "redirect:/submissions/" + newId;
    }

    @GetMapping("edit/{id:[0-9]+}")
    public String showUpdateForm(@PathVariable long id, Model model) {
        // TODO - validate the submission even exists
        SubmissionDTO submissionToUpdate = submissionService.findById(id);
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
        submissionService.update(id, submissionDTO);
        return "redirect:/submissions/" + id;
    }

    @DeleteMapping("{id:[0-9]+}")
    public String delete(@PathVariable long id, Model model) {
        submissionService.delete(id);
        return "redirect:/submissions/";
    }
}
