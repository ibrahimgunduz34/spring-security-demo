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

    @GetMapping("/private")
    public ResponseEntity<String> getPrivateContent() {
        return ResponseEntity.ok("Private content!");
    }

    @GetMapping("/admin/action1")
    public ResponseEntity<String> getAdminAction1Content() {
        return ResponseEntity.ok("Admin Action 1 content!");
    }

    @GetMapping("/admin/action2")
    public ResponseEntity<String> getAdminAction1Conten2() {
        return ResponseEntity.ok("Admin Action 2 content!");
    }
}
