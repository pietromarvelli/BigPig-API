package com.bigpig.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bigpig.api.model.Key;
import com.bigpig.api.model.User;

@Repository
public interface KeyRepository extends JpaRepository<Key, String> {
    List<Key> findByUser(User user);
}