package com.example.atividade_crude.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ItemEnum tipo;

    public Item() {
    }

    public Item(Long id, ItemEnum tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public Item(ItemEnum tipo) {
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setTipo(ItemEnum tipo) {
        this.tipo = tipo;
    }

    public ItemEnum getTipo() {
        return tipo;
    }
}
