package com.example.kabantop.repository;

import com.example.kabantop.model.Tarefamodel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface kanbanreposy extends JpaRepository<Tarefamodel, Long> {}
