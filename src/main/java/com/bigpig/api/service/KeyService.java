package com.bigpig.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bigpig.api.model.Key;
import com.bigpig.api.repository.KeyRepository;

import java.util.List;

@Service
public class KeyService {
   @Autowired
   private KeyRepository keyRepository;

   public List<Key> findAll() {
      return keyRepository.findAll();
   }

   public Key findById(Tipo id) {
      return keyRepository.findById(id).orElse(null);
   }

}