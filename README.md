# 🖼 Image Board Site
Image board site created with Java and Spring Boot

## Table of Contents
1. [Introduction](#introduction)
2. [Description](#description)
3. [Features](#features)
4. [Instructions](#instructions)
5. [Showcase](#showcase)
6. [Technologies Used](#technologies-used)
7. [Contact](#get-in-touch)

## Introduction

This repository will hold a Spring Boot application that works as an **Image Board site**.

The purpose of this is to solidify my knowledge in MVC frameworks and the **Spring Boot** workflow. 

The inspiration for this application comes from the intricate nature of these kind of websites, which poses a formidable challenge to further advance and display my skills.

## Description

(*See the next section to know which features are implemented yet*)

This application will simulate a social media application focused on images where different **Users** can make **Submissions**. 
Submissions will contain information given by the user, like a title, a description and **Tags**. Submissions can also belong to a series of pre established **Categories**.

Users will have their own profile page where they can see their **Gallery** containing their own submissions, and visit other users' galleries to see theirs.
Users can interact with other users' submissions by giving a **Like** or leaving a **Comment**.

### Features

**MVP:**
- [X] API Endpoint for CRUD operations on the main entity (A submission).
- [ ] Views for CRUD operations on the main entity (A submission).
  - [X] A listing view made of a table.
  - [X] Create and update forms.
  - [ ] Server side input validation (With bean validation).
  - [X] A listing view made of cards.
  - [ ] Implement pagination.
- [X] Bootstrap styling.
- [X] Submissions are assigned to a Category.
- [ ] User authentication.
- [ ] User's template sites.
  - [ ] User dashboard.
  - [ ] User profile.
- [ ] Support for file upload and download.
- - - -
- [ ] User authorization.
  - [ ] Permission over actions on other users submissions.
  - [ ] Guest visualization.
  - [ ] Admin role for a super user.
- [ ] Search feature.
  - [ ] Search by user.
  - [ ] Search by name.
  - [ ] Search by tags.
  - [ ] Search by dates.
- [ ] Users interactions over others' submission (Likes).
- [ ] Posts have a comments section.
- [ ] Move away from Bootstrap styling into Tailwind CSS.

## Instructions

This is a Spring Boot application built using Maven. You can use your IDE of choice to build and run the project, or you can build a jar file and run it from the command line:

```bash
git clone https://github.com/carlos-quintana/spring-boot-crud.git

cd spring-boot-crud

./mvnw package

java -jar target/*.jar
```

You can then access the site at http://localhost:8080/

![Landing page](https://user-images.githubusercontent.com/102340968/227805569-c65b4b82-b1aa-4acc-a799-ccbab43fcd33.png)

Note: Make sure your machine meets the minimum requirements, show in the [Technologies used](#technologies-used) section.

## Showcase

### Main Submissions listings site:

![Grid view](https://user-images.githubusercontent.com/102340968/227805863-c43b1da6-e5f4-4727-8eaf-488d66739c09.png)

***Note:** None of these images were made by me. All of the example images were taken from sites where they were posted as having a CC 1.0 license or otherwise marked as Free to use in personal projects without comercial gain. All of the fields presented in this project, including usernames, titles, descriptions and tags are made up and the original authors of the images have no relation with the project. I also plan to compose a list of all of the original sources as to give credit to each one of them. If any of the original authors would like to have their work taken down I encourage them to get in contact with me.*

- - - -
### Individual submission view

![Individual submission](https://user-images.githubusercontent.com/102340968/227805521-a852c30a-8f7a-4333-a23e-d330dc90a851.png)

- - - -
### Create and update forms

![Create and Update forms](https://user-images.githubusercontent.com/102340968/227806002-cf416368-4c70-456f-8f65-54c760e08cb4.png)

- - - -
### Admin Submissions view

![Table view](https://user-images.githubusercontent.com/102340968/227805627-07dd8b7d-c145-4f87-b35e-19022d8e626d.png)


### Database model

The following is the Entity-Relationship Model of the database:

![Entity-Relationship Model](https://user-images.githubusercontent.com/102340968/227805340-e61205d2-44f7-405e-9cc9-00d519fe38f5.png)

Submissions are assigned to one Category. The next step will be to create the users table that will come with the implementation of the authentication system.

## Technologies Used

- Java 11
- Spring Boot 3.0
- Maven
- MariaDB 10.4
- Bootstrap 5

#### Dependencies

- Spring Core
- Spring Web (Spring MVC)
- Spring Data JPA
- JDBC API
- MySQL JDBC Driver
- ModelMapper
- Thymeleaf & Thymeleaf layout dialect
- Lombok
- Spring Dev Tools

## Get in touch

Let's get in touch through:

[💼 LinkedIn](https://linkedin.com/in/carlos-quintana-dev)

*Last update: March of 2023*
