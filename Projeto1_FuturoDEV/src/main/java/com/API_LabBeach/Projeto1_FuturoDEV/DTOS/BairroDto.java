package com.API_LabBeach.Projeto1_FuturoDEV.DTOS;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

//Utilização de DTO's para passagem de dados entre processos, para reduzir a quantidade de chamadas de metódos
//Não é necessário configurar o ID aqui pois ele é autogerado, sem necessidade de input do usuario
public class BairroDto {

    @NotBlank
    @Size(max=100)
    private String nomeBairro;
    @Size(max = 500)
    private String descricaoBairro;

    private int populacaoBairro;

    //Getters e Setters
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
