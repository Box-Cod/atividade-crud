package com.example.atividade_crude.model;

import com.example.atividade_crude.enums.ClasseEnum;
import com.example.atividade_crude.enums.ItemEnum;
import jakarta.persistence.*;


import java.util.List;

@Entity
@Table (name = "tb_personagem")
public class Personagem {

    @Id
    @Column(name="personagem_id")
    @SequenceGenerator(
            name = "personagem_sequence",
            sequenceName = "personagem_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "personagem_sequence"
    )
    private Long id;

    @Column(
            name = "nome",
            nullable = false
    )
    private String nome;

    @Column(
            name = "nome_aventureiro",
            nullable = false
    )
    private String nomeAventureiro;

    @Enumerated(EnumType.STRING)
    @PrimaryKeyJoinColumn(name = "fk_classe")
    @OneToOne
    @JoinColumn(
            name = "fk_classe",
            referencedColumnName = "classe_id"
    )
    private ClasseEnum classe;

    @Column(
            name = "level",
            nullable = false
    )
    private int level;

    @OneToMany
    @JoinColumn(
            name = "item_id",
            referencedColumnName = "item_id"
    )
    private List<Item> items;

    @Column(
            name = "forca",
            nullable = false
    )
    private int forca;

    @Column(
            name = "defesa",
            nullable = false
    )
    private int defesa;

    public Personagem() {

    }

    public Personagem(Long id, String nome, String nomeAventureiro, ClasseEnum classe, int level, List<Item> items, int forca, int defesa) {
        this.id = id;
        this.nome = nome;
        this.nomeAventureiro = nomeAventureiro;
        this.classe = classe;
        this.level = level;
        this.items = items;
        this.forca = forca;
        this.defesa = defesa;
    }

    public Personagem(String nome, String nomeAventureiro, ClasseEnum classe, int level, List<Item> items, int forca, int defesa) {
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

    public ClasseEnum getClasse() {
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
        try {
            if (nome.length() <= 1) {
                throw new RuntimeException("Nome não pode ter menos que um caractere");
            }

            if (nome.isEmpty() || nome.isBlank()) {
                throw new RuntimeException("Nome não pode ser vazio conter sómente um espaço em branco");
            }

            this.nome = nome;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setNomeAventureiro(String nomeAventureiro) {
        try {
            if (nomeAventureiro.length() <= 1) {
                throw new RuntimeException("Nome do aventureiro não pode ter menos que um caractere");
            }

            if (nomeAventureiro.isEmpty() || nomeAventureiro.isBlank()) {
                throw new RuntimeException("Nome do Aventureiro não pode ser vazio conter sómente um espaço em branco");
            }

            this.nomeAventureiro = nomeAventureiro;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setClasse(ClasseEnum classe) {
        this.classe = classe;
    }

    public void setLevel(int level) {
        try {

            if (level < 0) {
                throw new RuntimeException("Level não pode ser menor que zero");
            }

            this.level = level;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setItems(List<Item> items) {
        try {

            if (items.size() < 0) {
                throw new RuntimeException("Personagem não pode ter menos que 0 itens");
            }

            if (items.size() > 3) {
                throw new RuntimeException("Personagem não pode ter mais que 3 itens");
            }

            int countAmuleto = 0;
            for (Item item : items) {
                if (item.getTipo() == ItemEnum.AMULETO) {
                    countAmuleto++;
                }
                if (countAmuleto > 1) {
                    throw new RuntimeException("Personagem não pode ter mais que um Item do tipo Amuleto");
                }
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
