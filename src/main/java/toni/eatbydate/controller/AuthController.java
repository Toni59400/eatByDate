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
                response.put("jwt", jwt);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }


    @CrossOrigin("*")
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterDTO registerRequest) {
        String username = registerRequest.getUsername();
        String password = registerRequest.getPassword();
        String email = registerRequest.getEmail();


        User user = authService.createUser(username, password, email);
        if (user != null) {
            Optional<User> optionalUser = this.userService.getByEmail(email);
            if (optionalUser.isPresent()) {
                User user2 = optionalUser.get();
                String jwt = generateJwt(user2);
                Map<String, String> response = new HashMap<>();
                response.put("jwt", jwt);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private String generateJwt(User username) {

        long expirationTime = 3600000; // 1 heure en millisecondes

        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        String jwt = Jwts.builder()
                .setSubject(username.getUsername())
                .claim("email",username.getEmailAddress())
                .claim("role", "ROLE_USER")
                .claim("id", username.getUserId())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
        return jwt;
    }
}

