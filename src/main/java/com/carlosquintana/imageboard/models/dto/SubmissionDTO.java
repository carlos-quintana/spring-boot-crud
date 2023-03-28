package com.carlosquintana.imageboard.models.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Data
public class SubmissionDTO {

    private Long id;

    @NotBlank(message = "The title cannot be blank")
    @Size(min=5, max=100, message = "The title must have between 5 and 100 characters")
    private String title;

    private String description;

    @NotBlank(message = "Please include at least one tag")
    private String tags;

    @NotNull(message = "Please select a category")
    private Long category;

    private String categoryName;

    private String img_source;

    private Date createdAt;
}
