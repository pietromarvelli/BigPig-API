package com.bigpig.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bigpig.api.model.Key;
@Repository
public interface KeyRepository extends JpaRepository<Key, TipoId> {}