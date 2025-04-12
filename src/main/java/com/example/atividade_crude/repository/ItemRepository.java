package com.example.atividade_crude.repository;

import com.example.atividade_crude.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
