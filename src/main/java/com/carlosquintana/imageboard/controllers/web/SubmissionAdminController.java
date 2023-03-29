package com.carlosquintana.imageboard.controllers.web;

import com.carlosquintana.imageboard.models.dto.SubmissionDTO;
import com.carlosquintana.imageboard.services.data.CategoryDataAccess;
import com.carlosquintana.imageboard.services.data.SubmissionDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/")
public class SubmissionAdminController {
    @Autowired
    private SubmissionDataAccess submissionService;
    @Autowired
    private CategoryDataAccess categoryService;

    @GetMapping({"submissions", "submissions/"})
    public String showListing(Model model) {
        List<SubmissionDTO> allSubmissions = submissionService.findAll();

        // Fix the tags spacing so that the page can be visualized properly
        for (SubmissionDTO submission : allSubmissions) {
            String newTags = "";
            for (String tag : submission.getTags().split(","))
                newTags += tag.trim().toLowerCase() + ", ";
            submission.setTags(newTags);
        }

        model.addAttribute("submissions", allSubmissions);
        return "submissions/submissionsListing";
    }
}
