package toni.eatbydate.controller;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import toni.eatbydate.dto.LoginDTO;
import toni.eatbydate.dto.RegisterDTO;
import toni.eatbydate.entity.User;
import toni.eatbydate.service.AuthService;
import toni.eatbydate.service.UserService;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RestController
public class AuthController {

    private Key secretKey;
    private final AuthService authService;


    private final UserService userService;

    @Autowired
    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }


    @CrossOrigin("*")
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        if (authService.authenticate(username, password)) {
            Optional<User> optionalUser = this.userService.getByEmail(username);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                String jwt = generateJwt(user);
                Map<String, String> response = new HashMap<>();
                response.put("jwt", jwt); // Ajoutez le jeton JWT dans la réponse
                return ResponseEntity.ok(response);
            }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }


    @CrossOrigin("*")
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerRequest) {
        String username = registerRequest.getUsername();
        String password = registerRequest.getPassword();
        String email = registerRequest.getEmail();


        User user = authService.createUser(username, password, email);
        if (user != null) {
            return ResponseEntity.ok("User created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create user");
        }
    }
    private String generateJwt(User username) {

        long expirationTime = 3600000; // 1 heure en millisecondes

        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        String jwt = Jwts.builder()
                .setSubject(username.getUsername())
                .claim("email",username.getEmailAddress())
                .claim("role", "ROLE_USER")
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();

        return jwt;
    }
}

