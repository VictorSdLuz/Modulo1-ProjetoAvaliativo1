package com.API_LabBeach.Projeto1_FuturoDEV.Services;

import com.API_LabBeach.Projeto1_FuturoDEV.Models.BairroModel;
import com.API_LabBeach.Projeto1_FuturoDEV.Repositories.BairroRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BairroService {

    //injeçao de dependencia da classe Bairro para criação do Bean
    //Maneira de uso: '@AutoWired' ou por construtor, optei pela maneira do construtor

    final BairroRepository bairroRepository;

    public BairroService(BairroRepository bairroRepository){
        this.bairroRepository = bairroRepository;
    }


    //Uso do '@Transactional para garantir rollbacks e prevenir dados quebrados
    @Transactional
    public BairroModel save(BairroModel bairroModel) {
        return bairroRepository.save(bairroModel);
    }

    //método para checar se o bairro já esta cadastrado
    public boolean existsByNomeBairro(String nomeBairro) {
        return bairroRepository.existsByNomeBairro(nomeBairro);
    }

    public List<BairroModel> findAll(){
        return bairroRepository.findAll();
    }

    //função de deletar o Model passado
    @Transactional
    public void delete(BairroModel bairroModel) {
        bairroRepository.delete(bairroModel);
    }

    //método para achar o bairro desejado pelo seu ID único
    public Optional<BairroModel> findById(UUID id) {
        return bairroRepository.findById(id);
    }
}
