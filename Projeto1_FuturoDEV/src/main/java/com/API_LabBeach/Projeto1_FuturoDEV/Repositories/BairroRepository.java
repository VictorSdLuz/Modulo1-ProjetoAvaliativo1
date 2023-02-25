package com.API_LabBeach.Projeto1_FuturoDEV.Repositories;

import com.API_LabBeach.Projeto1_FuturoDEV.Models.BairroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

//Essa interface será um Bean
//Extends para o JpaRepository pela variedade de métodos prontos para transição do banco de dados, especialmente para simplificar a criação do CRUD
@Repository
public interface BairroRepository extends JpaRepository<BairroModel, UUID> {

    boolean existsByNomeBairro(String nomeBairro);
}
