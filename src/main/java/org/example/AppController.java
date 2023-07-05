package org.example;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
    @GetMapping("/public")
    public ResponseEntity<String> getPublicContent() {
        return ResponseEntity.ok("Public content!");
    }
}
