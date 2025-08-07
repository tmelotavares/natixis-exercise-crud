package com.natixis.exercise.repository;

import com.natixis.exercise.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {}
