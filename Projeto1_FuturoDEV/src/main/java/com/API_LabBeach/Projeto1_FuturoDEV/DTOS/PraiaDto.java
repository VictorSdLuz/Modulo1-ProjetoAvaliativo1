package com.API_LabBeach.Projeto1_FuturoDEV.DTOS;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

//Utilização de DTO's para passagem de dados entre processos, para reduzir a quantidade de chamadas de metódos
//Não é necessário configurar o ID aqui pois ele é autogerado, sem necessidade de input do usuario
public class PraiaDto {

    @NotBlank
    @Size(max = 100)
    private String nomePraia;

    @NotBlank
    private boolean acessibilidadePraia;

    @NotBlank
    @Size(min=4, max=10)
    private String statusPraia;

    //Getters e Setters
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
}
