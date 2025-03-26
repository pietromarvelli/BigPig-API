package com.bigpig.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
        User user = userService.findByUsernamePassword(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            return ResponseEntity.ok().body("{\"success\": true}");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"success\": false, \"message\": \"Wrong credentials\"}");
        }
    }

    @PostMapping("/addKey")
    public ResponseEntity<?> addKey(RedirectAttributes redirectAttributes, Model model, HttpSession session, @RequestParam("key") String key) {
        Key newKey = new Key();
        newKey.setPublicK(key);
        newKey.setUser((User) session.getAttribute("user"));
        keyService.save(newKey);
        return ResponseEntity.ok().body("{\"message\": \"Chiave Inserita\"}");
    }

    // @GetMapping("/getUsers")
    // public String getUsers(Model model, HttpSession session) {
    //     model.addAttribute("users", userService.findAll());
    //     return "redirect:/users";
    // }

    @GetMapping("/getKeys")
    public ResponseEntity<?> getKeys(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Key> keys = keyService.findByUser(user);
            return ResponseEntity.ok(keys); // Restituisce direttamente l'array di chiavi come JSON
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"User not logged in\"}");
        }
    }
}