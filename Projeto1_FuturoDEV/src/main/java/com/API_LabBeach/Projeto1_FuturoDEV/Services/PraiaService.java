package com.API_LabBeach.Projeto1_FuturoDEV.Services;

import com.API_LabBeach.Projeto1_FuturoDEV.Models.PraiaModel;
import com.API_LabBeach.Projeto1_FuturoDEV.Repositories.PraiaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//classe que serve de intermediação para as dependencias
@Service
public class PraiaService {

    //injeçao de dependencia da classe Praia para criação do Bean
    //Maneira de uso: '@AutoWired' ou por construtor, optei pela maneira do construtor

    final PraiaRepository praiaRepository;

    public PraiaService(PraiaRepository praiaRepository){
        this.praiaRepository = praiaRepository;
    }


    //Uso do '@Transactional para garantir rollbacks e prevenir dados quebrados
    @Transactional
    public PraiaModel save(PraiaModel praiaModel) {
        return praiaRepository.save(praiaModel);
    }

    //método para checar se o praia já esta cadastrada
    public boolean existsByNomePraia(String nomePraia) { return praiaRepository.existsByNomePraia(nomePraia);}

    public List<PraiaModel> findAll(){
        return praiaRepository.findAll();
    }

    //função de deletar o Model passado
    @Transactional
    public void delete(PraiaModel praiaModel) {
        praiaRepository.delete(praiaModel);
    }

    //método para achar a praia desejada pelo seu ID único
    public Optional<PraiaModel> findById(UUID id) {
        return praiaRepository.findById(id);
    }

    //Tentativa de filtrar a lista de praias por status (questão bonus)
    public List<PraiaModel> getAllPraiaByStatus(String statusPraia) {
        return praiaRepository.findByStatus0(statusPraia);
    }

    //Tentativa de filtrar lista de praias por acessibilidade
    public List<PraiaModel> getPraiaAcessivel(boolean acessibilidadePraia) {
        return praiaRepository.findByAcessibilidade(acessibilidadePraia);
    }
}
