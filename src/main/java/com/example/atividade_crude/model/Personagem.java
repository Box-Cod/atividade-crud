package com.example.atividade_crude.model;

import com.example.atividade_crude.enums.ClasseEnum;
import com.example.atividade_crude.enums.ItemEnum;
import jakarta.persistence.*;


import java.util.List;

@Entity
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String nomeAventureiro;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private ClasseEnum classe;

    private int level;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Item.class, cascade = CascadeType.PERSIST)
    private List<Item> items;

    private int forca;

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

    @ElementCollection(targetClass = Item.class)
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
            int totalForcaItens = 0;
            int totalDefesaItens = 0;

            for (Item item : items) {

                if (item.getTipo() == ItemEnum.AMULETO) {
                    countAmuleto++;
                }

                if (countAmuleto > 1) {
                    throw new RuntimeException("Personagem não pode ter mais que um Item do tipo Amuleto");
                }

                if (item.getForca() > 10) {
                    throw new RuntimeException("Personagem não pode um Item com Pontos de Força maior que 10");
                }

                if (item.getDefesa() > 10) {
                    throw new RuntimeException("Personagem não pode um Item com Pontos de Defesa maior que 10");
                }

                if (item.getTipo() == ItemEnum.ARMA) {

                    if (item.getForca() <= 0) {
                        throw new RuntimeException("Personagem não pode um Item com Pontos de Força menor ou igual a 0");
                    }

                    if (item.getForca() > 10) {
                        throw new RuntimeException("Personagem não pode um Item com Pontos de Força maior que 10");
                    }
                }

                if (item.getTipo() == ItemEnum.ARMADURA) {

                    if (item.getDefesa() <= 0) {
                        throw new RuntimeException("Personagem não pode um Item com Pontos de Defesa menor ou igual a 0");
                    }

                    if (item.getDefesa() > 10) {
                        throw new RuntimeException("Personagem não pode um Item com Pontos de Defesa maior que 10");
                    }
                }


                for (Item itens : items) {
                    totalForcaItens += item.getForca();
                    totalDefesaItens += item.getDefesa();
                }

                if (item.getTipo() == ItemEnum.ARMA) {

                    if (totalForcaItens > 10) {
                        throw new RuntimeException("Personagem não pode ter a soma de seus Itens de Força maior que 10");
                    }

                    if (totalForcaItens <= 0) {
                        throw new RuntimeException("Personagem não pode ter a soma de seus Itens de Força menor ou igual a 0");
                    }

                }

                if (item.getTipo() == ItemEnum.ARMADURA) {

                    if (totalDefesaItens > 10) {
                        throw new RuntimeException("Personagem não pode ter a soma de seus Itens de Defesa maior que 10");
                    }

                    if (totalDefesaItens <= 0) {
                        throw new RuntimeException("Personagem não pode ter a soma de seus Itens de Defesa menor ou igual a 0");
                    }
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

            int totalForcaItens = 0;
            int totalForca = 0;

            for (Item item: items) {
                totalForcaItens += item.getForca();
            }

            if (totalForcaItens == 10) {
                throw new RuntimeException("Personagem já possui o maximo de Pontos de Força");
            }

            totalForca = totalForcaItens + forca;

            if (totalForca > 10) {
                throw new RuntimeException("O Personagem possui um total de "+totalForcaItens+" pontos de Força de itens, passe um valor que somado não ultrapasse 10 pontos");
            }

            if (totalForca <= 0) {
                throw new RuntimeException("O Personagem possui um total de "+totalForcaItens+" pontos de Força de itens, passe um valor que somado não seja maior que 0 pontos");
            }

            this.forca = totalForca;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void setDefesa(int valorDefesa) {
        try {
            if(valorDefesa < 0) {
                throw new RuntimeException("Defesa não pode ser menor que zero");
            }

            if(valorDefesa > 10) {
                throw new RuntimeException("Defesa não pode ser maior que 10");
            }

            int totalDefesaItens = 0;
            int totalDefesa = 0;

            for (Item item: this.items) {
                totalDefesaItens += item.getDefesa();
            }

            if (totalDefesaItens == 10) {
                throw new RuntimeException("Personagem já possui o maximo de Pontos de Defesa");
            }

            totalDefesa = totalDefesaItens + valorDefesa;

            if (totalDefesa > 10) {
                throw new RuntimeException("O Personagem possui um total de (itens ->"+totalDefesaItens+") (valor defesa ->"+valorDefesa+") (total ->"+totalDefesa+") pontos de Defesa de itens, passe um valor que somado não ultrapasse 10 pontos");
            }

            if (totalDefesa < 0) {
                throw new RuntimeException("O Personagem possui um total de "+totalDefesaItens+" pontos de Defesa de itens, passe um valor que somado não seja maior que 0 pontos");
            }

            this.defesa = totalDefesa;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
