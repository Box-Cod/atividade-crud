package com.example.atividade_crude.model;

import com.example.atividade_crude.enums.ItemEnum;
import jakarta.persistence.*;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private ItemEnum tipo;

    private int forca;

    private int defesa;

    public Item() {
    }

    public Item(Long id, String nome, ItemEnum tipo, int forca, int defesa) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.forca = forca;
        this.defesa = defesa;
    }

    public Item(String nome, ItemEnum tipo, int forca, int defesa) {
        this.nome = nome;
        this.tipo = tipo;
        this.forca = forca;
        this.defesa = defesa;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public ItemEnum getTipo() {
        return tipo;
    }

    public int getForca() {
        return forca;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(ItemEnum tipo) {
        this.tipo = tipo;
    }

    public void setForca(int forca) {
        try {

            switch (this.tipo) {

                case ARMA:

                    if (forca <= 0) {
                        throw new RuntimeException("A Força do item não pode ser menor ou igual a 0");
                    }

                    if (forca > 10) {
                        throw new RuntimeException("A Força do item não pode ser maior que 10");
                    }

                    this.defesa = 0;
                    this.forca = forca;
                break;

                case AMULETO:

                    if (forca < 0) {
                        throw new RuntimeException("A Força do Amuleto não pode ser menor menor ou igual a 0");
                    }

                    if (forca > 10) {
                        throw new RuntimeException("A Força do Amuleto não pode ser maior que 10");
                    }

                    this.forca = forca;

                break;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void setDefesa(int defesa) {
        try {

            switch (this.tipo) {

                case ARMADURA:

                    if (defesa <= 0) {
                        throw new RuntimeException("A Defesa do item não pode ser menor ou igual a 0");
                    }

                    if (defesa > 10) {
                        throw new RuntimeException("A Defesa do item não pode ser maior que 10");
                    }

                    this.defesa = defesa;
                    this.forca = 0;
                break;

                case AMULETO:

                    if (defesa <= 0) {
                        throw new RuntimeException("A Defesa do Amuleto não pode ser menor ou igual a 0");
                    }

                    if (defesa > 10) {
                        throw new RuntimeException("A Defesa do Amuleto não pode ser maior que 10");
                    }

                    this.defesa = defesa;

                break;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
