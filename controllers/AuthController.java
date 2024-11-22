package event.SpringBootApp.controllers;

import event.SpringBootApp.Entities.userEntity;
import event.SpringBootApp.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class AuthController {

    @Autowired
    private userRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody userEntity user) {
        // Check if the email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already exists.");
        }

        // Save the new user with plain text password
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody userEntity user) {
        // Find user by email
        Optional<userEntity> existingUserOptional = userRepository.findByEmail(user.getEmail());

        // Check if the user exists and passwords match
        if (existingUserOptional.isPresent()) {
            userEntity existingUser = existingUserOptional.get();
            if (existingUser.getPassword().equals(user.getPassword())) {
                // Include the user's role in the success message
                return ResponseEntity.ok("Login successful. Role: " + existingUser.getRole());
            }
        }

        return ResponseEntity.badRequest().body("Invalid email or password");
    }
}
