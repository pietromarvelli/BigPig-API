package com.bigpig.api.controller;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;
import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bigpig.api.model.Key;
import com.bigpig.api.model.User;
import com.bigpig.api.service.KeyService;
import com.bigpig.api.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private KeyService keyService;

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpSession session, @RequestParam("username") String username, @RequestParam("password") String password) {
        password = hashPassword(password);
        User user = userService.findByUsernamePassword(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            return ResponseEntity.ok().body("{\"success\": true}");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"success\": false, \"message\": \"Wrong credentials\"}");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(HttpSession session, @RequestParam("name") String nome, @RequestParam("surname") String cognome, @RequestParam("username") String username, @RequestParam("password") String password) {
        User existingUser = userService.findByUsername(username);
        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"success\": false, \"message\": \"Username already exists\"}");
        } else {
            User newUser = new User();
            newUser.setName(nome);
            newUser.setSurname(cognome);
            newUser.setUsername(username);
            newUser.setPassword(hashPassword(password));
            userService.save(newUser);
            return ResponseEntity.ok().body("{\"success\": true}");
        }
    }


    @PostMapping("/addKey")
    public ResponseEntity<?> addKey(RedirectAttributes redirectAttributes, Model model, HttpSession session, @RequestParam("key") String key) {
        Key newKey = new Key();
        newKey.setPublicK(key);
        newKey.setValidazioni(true);
        newKey.setUser((User) session.getAttribute("user"));
        keyService.save(newKey);
        return ResponseEntity.ok().body("{\"message\": \"Chiave Inserita\"}");
    }

    @PutMapping("/disableKey")
    public ResponseEntity<?> disableKey(@RequestParam("key") String key) {
        Key existingKey = keyService.findById(key);
        if (existingKey != null) {
            existingKey.setValidazioni(false);
            keyService.save(existingKey);
            return ResponseEntity.ok().body("{\"message\": \"Chiave Disabilitata\"}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Chiave non trovata\"}");
        }
    }

    @GetMapping("/getKeys")
    public ResponseEntity<?> getKeys(HttpSession session) {
        //User user = (User) session.getAttribute("user");
       // if (user != null) {
            List<Key> keys = keyService.findAll();
            return ResponseEntity.ok().body(keys);
      /*  } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"User not logged in\"}");
        }*/
    }



    @GetMapping("/getKeysByUsername")
    public ResponseEntity<?> getKeysByUsername(@RequestParam("username") String username, Model model) {
        User user = userService.findByUsername(username);
        if (user != null) {
            List<Key> keys = keyService.findByUser(user);
            model.addAttribute("keys", keys);
            return ResponseEntity.ok().body(keys);
        } else {
            model.addAttribute("msg", "User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"success\": false, \"message\": \"User not found\"}");
        }
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(encodedhash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
//ciao