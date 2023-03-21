package com.carlosquintana.imageboard.controllers.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TestController {

    @GetMapping
    public ResponseEntity test() {
        return ResponseEntity.ok("The API is working just fine :)");
    }
}
