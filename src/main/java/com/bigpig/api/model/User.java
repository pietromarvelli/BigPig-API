package com.bigpig.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")

public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer idUser;
   private String username;
   private String name;
   private String surname;
   private String password;
}