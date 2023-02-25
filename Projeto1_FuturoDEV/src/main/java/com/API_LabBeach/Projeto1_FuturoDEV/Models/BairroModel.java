package com.API_LabBeach.Projeto1_FuturoDEV.Models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


//Criacao da tabela dos bairros, com implementação do identificador autogerado
@Entity
@Table(name="TB_BAIRROS")
public class BairroModel implements Serializable {
    //Identificador autogerado
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idBairro;

    //Relação um pra muitos entre Bairro e praias
    @OneToMany(mappedBy = "bairroModel")
    private Set<PraiaModel> tb_praias = new HashSet<>();

    //implementação dos outros dados
    @Column(nullable = false, unique = true, length = 100)
    private String nomeBairro;
    @Column(nullable = true, length = 500)
    private String descricaoBairro;
    @Column(nullable = true)
    private int populacaoBairro;

    //Getters e Setters
    public UUID getIdBairro() {
        return idBairro;
    }

    public void setIdBairro(UUID idBairro) {
        this.idBairro = idBairro;
    }

    public String getNomeBairro() {
        return nomeBairro;
    }

    public void setNomeBairro(String nomeBairro) {
        this.nomeBairro = nomeBairro;
    }

    public String getDescricaoBairro() {
        return descricaoBairro;
    }

    public void setDescricaoBairro(String descricaoBairro) {
        this.descricaoBairro = descricaoBairro;
    }

    public Integer getPopulacaoBairro() {
        return populacaoBairro;
    }

    public void setPopulacaoBairro(Integer populacaoBairro) {
        this.populacaoBairro = populacaoBairro;
    }
}
