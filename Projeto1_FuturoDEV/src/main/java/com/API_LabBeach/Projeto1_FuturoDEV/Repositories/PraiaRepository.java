package com.API_LabBeach.Projeto1_FuturoDEV.Repositories;

import com.API_LabBeach.Projeto1_FuturoDEV.Models.PraiaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

//Essa interface será um Bean
//Extends para o JpaRepository pela variedade de métodos prontos para transição do banco de dados, especialmente para simplificar a criação do CRUD
@Repository
public interface PraiaRepository extends JpaRepository<PraiaModel, UUID> {

    boolean existsByNomePraia(String nomePraia);

    //Tentativa de filtrar a lista de praias por status (questão bonus)
    List<PraiaModel> findByStatus0(String statusPraia);

    //Tentativa de filtrar lista de praias por acessibilidade
    List<PraiaModel> findByAcessibilidade(boolean acessibilidadePraia);
}
