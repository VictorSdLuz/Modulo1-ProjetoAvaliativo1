package com.API_LabBeach.Projeto1_FuturoDEV.Controllers;
import com.API_LabBeach.Projeto1_FuturoDEV.DTOS.PraiaDto;
import com.API_LabBeach.Projeto1_FuturoDEV.Models.PraiaModel;
import com.API_LabBeach.Projeto1_FuturoDEV.Services.PraiaService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//Camadas Controllers recebem e acionam as solicitações(CRUD), acionando as classes Services e Repositories
//Bean da injeção
// RequestMapping para acesso na url
@RestController
//Criada noss API Rest com o @RestController, agora vamos configurar a URI da nossa classe com @ResquestMapping
@RequestMapping("/praias")
public class PraiaController {

    //ponto de injeção

    final PraiaService praiaService;

    public PraiaController(PraiaService praiaService){
        this.praiaService = praiaService;
    }

    //Método POST, recebe o DTO com todos os campos solicitados em JSON, usando o '@RequestBody' e usando '@Valid' para
    // se  assegurar que os dados vieram como foram solicitados
    @PostMapping
    public ResponseEntity<Object> savePraia(@RequestBody @Valid PraiaDto praiaDto){
        //Verificação para saber se o nome da praia já existe
        if(praiaService.existsByNomePraia(praiaDto.getNomePraia())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Praia já cadastrada");
        }
        //Recebemos os dados da classe DTO e convertemos   ela em uma classe 'Model'
        var praiaModel = new PraiaModel();
        BeanUtils.copyProperties(praiaDto, praiaModel);
        //como o método 'save' não existe, vamos importar e criar o método
        return ResponseEntity.status(HttpStatus.CREATED).body(praiaService.save(praiaModel));
    }

    //método GET, para listar todos os cadastros
    @GetMapping
    public ResponseEntity<List<PraiaModel>> getAllPraia(){
        //devemos construir o método 'findAll()' na classe PraiaService para retornar todos os cadastros
        return ResponseEntity.status(HttpStatus.OK).body(praiaService.findAll());
    }

    //método DELETE: usando o ID o método irá deletar o objeto cadastrado que tenha o ID inserido pelo usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePraia(@PathVariable(value= "id") UUID id){
        Optional<PraiaModel> praiaModelOptional = praiaService.findById(id);
        if(!praiaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Praia desejada não foi encontrada");
        }
        String nomePraiaDeletada = praiaModelOptional.get().getNomePraia();
        praiaService.delete(praiaModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Praia "+nomePraiaDeletada+" "+"foi deletada");
    }

    //Método de edição de um objeto cadastrado
    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePraia(@PathVariable(value="id") UUID id, @RequestBody @Valid PraiaDto praiaDto){
        Optional<PraiaModel> praiaModelOptional = praiaService.findById(id);
        //Em caso do objeto não ser encontrado
        if(!praiaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Praia desejada não foi encontrada");
        }
        var praiaModel = new PraiaModel();
        //Usando a ferramenta BeanUtils, podemos passar os dados editados do DTO recebido para o novo model
        BeanUtils.copyProperties(praiaDto, praiaModel);
        praiaModel.setIdPraia(praiaModelOptional.get().getIdPraia());
        return ResponseEntity.status(HttpStatus.OK).body(praiaService.save(praiaModel));
    }

    //Tentativa de filtrar a lista de praias por status (questão bonus)
    @GetMapping("/praiaPropria")
    public List<PraiaModel> getAllPraiaByStatus0(@PathVariable String statusPraia){
        //Listar praias por status
        return praiaService.getAllPraiaByStatus(statusPraia);
    }

    //Tentativa de filtrar lista de praias por acessibilidade
    @GetMapping("/acessibilidadePraia")
    public List<PraiaModel> getPraiaAcessiveis(){
        return praiaService.getPraiaAcessivel(true);
    }
}
