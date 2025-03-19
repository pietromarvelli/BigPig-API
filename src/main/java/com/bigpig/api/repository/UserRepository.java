package com.bigpig.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bigpig.api.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsernameAndPassword(String username, String password);

    User findByIdUser(Integer idUser);
    User findByUsername(String username);
}