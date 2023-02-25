package com.API_LabBeach.Projeto1_FuturoDEV.Models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;

//Criacao da tabela dos bairros, com implementação do identificador autogerado
@Entity
@Table(name="TB_PRAIAS")
public class PraiaModel implements Serializable {
    //Identificador autogerado
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idPraia;

    //Relação de praia para algum bairro, com a opção 'optional=false' e 'nullable=false' o usuario não poderá deletar um bairro que estiver ligado a alguma praia
    @ManyToOne(optional = false)
    @JoinColumn(name="idBairro", nullable = false)
    private BairroModel bairroModel;

    //implementação dos outros dados

    @Column(nullable = false, length = 100)
    private String nomePraia;
    @Column(nullable = false)
    private boolean acessibilidadePraia;
    @Column(nullable = false, length = 10)
    private String statusPraia;

    //Getters e Setters
    public UUID getIdPraia() {
        return idPraia;
    }

    public void setIdPraia(UUID idPraia) {
        this.idPraia = idPraia;
    }

    public String getNomePraia() {
        return nomePraia;
    }

    public void setNomePraia(String nomePraia) {
        this.nomePraia = nomePraia;
    }

    public boolean isAcessibilidadePraia() {
        return acessibilidadePraia;
    }

    public void setAcessibilidadePraia(boolean acessibilidadePraia) {
        this.acessibilidadePraia = acessibilidadePraia;
    }

    public String getStatusPraia() {
        return statusPraia;
    }

    public void setStatusPraia(String statusPraia) {
        this.statusPraia = statusPraia;
    }

    public BairroModel getBairroModel() {
        return bairroModel;
    }

    public void setBairroModel(BairroModel bairroModel) {
        this.bairroModel = bairroModel;
    }
}
