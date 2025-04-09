package com.example.atividade_crude.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String nomeAventureiro;

    private Classe classe;

    private int level;

    private List<Item> items;

    private int forca;

    private int defesa;

    public Personagem() {

    }

    public Personagem(Long id, String nome, String nomeAventureiro, Classe classe, int level, List<Item> items, int forca, int defesa) {
        this.id = id;
        this.nome = nome;
        this.nomeAventureiro = nomeAventureiro;
        this.classe = classe;
        this.level = level;
        this.items = items;
        this.forca = forca;
        this.defesa = defesa;
    }

    public Personagem(String nome, String nomeAventureiro, Classe classe, int level, List<Item> items, int forca, int defesa) {
        this.nome = nome;
        this.nomeAventureiro = nomeAventureiro;
        this.classe = classe;
        this.level = level;
        this.items = items;
        this.forca = forca;
        this.defesa = defesa;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getNomeAventureiro() {
        return nomeAventureiro;
    }

    public Classe getClasse() {
        return classe;
    }

    public int getLevel() {
        return level;
    }

    public List<Item> getItems() {
        return items;
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

    public void setNomeAventureiro(String nomeAventureiro) {
        this.nomeAventureiro = nomeAventureiro;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setItems(List<Item> items) {
        try {
            if (items.size() > 3) {
                throw new RuntimeException("Personagem não pode ter mais que 3 itens");
            }

            this.items = items;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void setForca(int forca) {
        try {
            if(forca < 0) {
                throw new RuntimeException("Força não pode ser menor que zero");
            }

            if(forca > 10) {
                throw new RuntimeException("Força não pode ser maior que 10");
            }

            if ((forca - getDefesa()) < 0) {
                throw new RuntimeException("A diferença entre Força e Defesa não pode ser menor que zero");
            }

            if ((forca - getDefesa()) > 10) {
                throw new RuntimeException("A diferença entre Força e Defesa não pode ser maior que 10");
            }

            this.forca = forca;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void setDefesa(int defesa) {
        try {
            if(defesa < 0) {
                throw new RuntimeException("Defesa não pode ser menor que zero");
            }

            if(defesa > 10) {
                throw new RuntimeException("Defesa não pode ser maior que 10");
            }

            if ((defesa - getForca()) < 0) {
                throw new RuntimeException("A diferença entre Defesa e Força não pode ser menor que zero");
            }

            if ((defesa - getForca()) > 10) {
                throw new RuntimeException("A diferença entre Defesa e Força não pode ser maior que 10");
            }

            this.defesa = defesa;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
