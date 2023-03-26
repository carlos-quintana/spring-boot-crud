package com.carlosquintana.imageboard.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SubmissionDTO {

    private Long id;

    private String title;

    private String description;

    private String tags;

    private Long category;

    private String categoryName;

    private String img_source;

    private Date createdAt;
}
