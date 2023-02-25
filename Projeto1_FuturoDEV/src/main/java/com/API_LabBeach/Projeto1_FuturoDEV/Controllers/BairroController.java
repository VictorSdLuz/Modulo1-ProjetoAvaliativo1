package com.API_LabBeach.Projeto1_FuturoDEV.Controllers;
import com.API_LabBeach.Projeto1_FuturoDEV.DTOS.BairroDto;
import com.API_LabBeach.Projeto1_FuturoDEV.Models.BairroModel;
import com.API_LabBeach.Projeto1_FuturoDEV.Services.BairroService;
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
@RequestMapping("/bairros")
public class BairroController {

    //ponto de injeção

    final BairroService bairroService;

    public BairroController(BairroService bairroService){
        this.bairroService = bairroService;
    }

    //Método POST, recebe o DTO com todos os campos solicitados em JSON, usando o '@RequestBody' e usando '@Valid' para
    // se  assegurar que os dados vieram como foram solicitados
    @PostMapping
    public ResponseEntity<Object> saveBairro(@RequestBody @Valid BairroDto bairroDto){
        //Verificação para saber se o nome do bairro já existe
        if(bairroService.existsByNomeBairro(bairroDto.getNomeBairro())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: nome do bairro já cadastrado");
        }

        //Recebemos os dados da classe DTO e convertemos   ela em uma classe 'Model'
        var bairroModel = new BairroModel();
        BeanUtils.copyProperties(bairroDto, bairroModel);
        //como o método 'save' não existe, vamos importar e criar o método
        return ResponseEntity.status(HttpStatus.CREATED).body(bairroService.save(bairroModel));
    }

    //método GET, para listar todos os cadastros
    @GetMapping
    public ResponseEntity<List<BairroModel>> getAllBairro(){
        //devemos construir o método 'findAll()' na classe BairroService para retornar todos os cadastros
        return ResponseEntity.status(HttpStatus.OK).body(bairroService.findAll());
    }

    //método DELETE: usando o ID o método irá deletar o objeto cadastrado que tenha o ID inserido pelo usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBairro(@PathVariable(value= "id") UUID id){
        Optional<BairroModel> bairroModelOptional = bairroService.findById(id);
        if(!bairroModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bairro desejado não foi encontrado");
        }
        String nomeBairroDeletado = bairroModelOptional.get().getNomeBairro();
        bairroService.delete(bairroModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Bairro "+nomeBairroDeletado+" "+"foi deletado");
    }

    //Método de EDIT: Edição de um objeto cadastrado
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBairro(@PathVariable(value="id") UUID id, @RequestBody @Valid BairroDto bairroDto){
        Optional<BairroModel> bairroModelOptional = bairroService.findById(id);
        //Em caso do objeto não ser encontrado
        if(!bairroModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bairro desejado não foi encontrado");
        }
        var bairroModel = new BairroModel();
        //Usando a ferramenta BeanUtils, podemos passar os dados editados do DTO recebido para o novo model
        BeanUtils.copyProperties(bairroDto, bairroModel);
        bairroModel.setIdBairro(bairroModelOptional.get().getIdBairro());
        return ResponseEntity.status(HttpStatus.OK).body(bairroService.save(bairroModel));
    }
}
