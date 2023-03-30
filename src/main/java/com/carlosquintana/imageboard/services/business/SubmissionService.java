package com.carlosquintana.imageboard.services.business;

import com.carlosquintana.imageboard.models.dto.SubmissionDTO;
import com.carlosquintana.imageboard.services.data.SubmissionDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubmissionService {

    @Autowired
    SubmissionDataAccess dataAccessService;

    public List<SubmissionDTO> findAll() {
        return dataAccessService.findAll();
    }

    public SubmissionDTO findById(long id) {
        return dataAccessService.findById(id);
    }

    public long save(SubmissionDTO newSubmission) {
        newSubmission.setTags(parseInputTags(newSubmission.getTags()));
        return dataAccessService.save(newSubmission);
    }

    public SubmissionDTO update(long id, SubmissionDTO submissionToUpdate) {
        // Validate that the target id is an existing submission, if it doesn't exist it will throw an exception
        dataAccessService.findById(id);
        submissionToUpdate.setTags(parseInputTags(submissionToUpdate.getTags()));
        return dataAccessService.update(id, submissionToUpdate);
    }

    public boolean delete(long id) {
        return dataAccessService.delete(id);
    }

    // With this function we can take the string of tags given by the user and clean it up, making all the words to lowercase and removing any unnecessary spaces in between tags.
    public String parseInputTags(String tags) {
        String newTags = "";
        for (String tag : tags.split(",")) {
            String cleanTag = tag.trim().toLowerCase();
            // If an individual tag contains spaces go through each word to make sure there are no repeating spaces in between them
            if (cleanTag.contains(" ")) {
                String newCleanTag = "";
                for (String word : cleanTag.split(" "))
                    if (word.length() > 0) newCleanTag += word + " ";
                // Remove the last trailing ' '
                newCleanTag = newCleanTag.substring(0, newCleanTag.length() - 1);
                newTags += newCleanTag + ",";
            } else
                newTags += cleanTag + ",";
        }
        // Remove the last trailing ','
        if (newTags.length() > 0)
            newTags = newTags.substring(0, newTags.length() - 1);
        return newTags;
    }

    // This function will take a string of tags separated by commas and split it into the individual substrings
    public List<String> parseOutputTags(String tagsString) {
        List<String> tagsList = new ArrayList<String>();
        for (String tag : tagsString.split(",")) {
            if (tag.trim().length() > 0)
                tagsList.add(tag.trim());
        }
        return tagsList;
    }
}
