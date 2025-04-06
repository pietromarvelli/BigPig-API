package com.bigpig.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bigpig.api.model.User;
import com.bigpig.api.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
   @Autowired
   private UserRepository userRepository;

   public List<User> findAll() {
      return userRepository.findAll();
   }

   public User findById(Integer id) {
      return userRepository.findById(id).orElse(null);
   }

   public User save(User user) {
      return userRepository.save(user);
   }

   public User findByUsernamePassword(String username, String password) {
      return userRepository.findByUsernameAndPassword(username, password);
   }

   public User findByUserId(Integer userId) {
      return userRepository.findByIdUser(userId);
   }

   public User findByUsername(String username) {
   return userRepository.findByUsername(username);
}

}