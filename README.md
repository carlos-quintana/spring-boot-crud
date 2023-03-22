# 🖼 Image Board Site
Image board site created with Java and Spring Boot

## Table of Contents
1. [Introduction](#introduction)
2. [Description](#description)
3. [Features](#features)
4. [Showcase](#showcase)
5. [Technologies Used](#technologies-used)
6. [Contact](#get-in-touch)

## Introduction

This repository will hold a Spring Boot application that works as an **Image Board site**.

The purpose of this is to solidify my knowledge in MVC frameworks and the **Spring Boot** workflow. 

The inspiration for this application comes from the intricate nature of these kind of websites, which poses a formidable challenge to further advance and display my skills.

## Description

(*See the next section to know which features are implemented yet*)

This application will simulate a social media application where different **Users** can make **Submissions**. 
Submissions will contain information given by the user, like a title, a description and **Tags**. Submissions can also belong to a series of pre established **Categories**.

Users will have their own profile page where they can see their **Gallery** containing their own submissions, and visit other users' galleries to see theirs.
Users can interact with other users' submissions by giving a **Like** or leaving a **Comment**.

### Features

**MVP:**
- [X] API Endpoint for CRUD operations on the main entity (A submission).
- [ ] Website templates for CRUD operations on the main entity (A submission).
  - [ ] Main listing site.
  - [ ] Create and update forms.
  - [ ] Input validation (With bean validation).
- [X] Bootstrap styling.
- [ ] Submissions are assigned to a Category.
- [ ] User authentication.
- [ ] User profiles.
  - [ ] Profile info.
  - [ ] Gallery component.
  - [ ] User dashboard.
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
- [ ] Support for file upload and download.
- [ ] Submissions will have images assigned to display.
- [ ] Users interactions over others' submission (Likes).
- [ ] Posts have a comments section.
- [ ] Move away from Bootstrap styling into Tailwind CSS.

### Showcase

*TO DO*

### Database model

The following is the Entity-Relationship Model of the database:

![image](https://user-images.githubusercontent.com/102340968/226977317-c0b3c8a5-50c7-4893-be1e-f20720ccd43d.png)

Currently there is only one table representing the main entity of the application. The next step will be to create a categories table to assign to a submission and the users table that will come with the implementation of the authentication system.

## Instructions

This is a Spring Boot application built using Maven. You can use your IDE of choice to build and run the project, or you can build a jar file and run it from the command line:

```bash
git clone https://github.com/carlos-quintana/spring-boot-crud.git

cd spring-boot-crud

./mvnw package

java -jar target/*.jar
```

You can then access the site at http://localhost:8080/

Note: Make sure your machine meets the minimum requirements, show in the [Technologies used](#technologies-used) section.

## Technologies Used

- Java 11
- Spring Boot 3.0
- Maven
- MariaDB 10.4

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
