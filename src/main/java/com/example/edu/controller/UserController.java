package com.example.edu.controller;

import com.example.edu.domain.user.User;
import com.example.edu.dto.UserDto;
import com.example.edu.repo.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository repo;

    public UserController(UserRepository repo) { this.repo = repo; }

    @GetMapping
    public List<User> all() { return repo.findAll(); }

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody UserDto req) {
        if (repo.findByEmail(req.email()).isPresent()) {
            throw new IllegalStateException("User with this email already exists");
        }
        User u = new User();
        u.setName(req.name());
        u.setEmail(req.email());
        u.setRole(req.role());
        return ResponseEntity.ok(repo.save(u));
    }


    @GetMapping("/{id}")
    public User get(@PathVariable Long id) { return repo.findById(id).orElseThrow(); }

    public record CreateUserRequest(
            @NotBlank String name,
            @Email String email,
            @NotBlank com.example.edu.domain.user.UserRole role
    ) {}
}
