package com.projeto.controller;

import com.projeto.entity.PessoaEntity;
import com.projeto.repository.EntityPessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class PessoaController {

    private EntityPessoaRepository entityPessoaRepository;

    @RequestMapping(value = "/pessoa", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<PessoaEntity> Get() {
        return entityPessoaRepository.findAll();
    }

    @RequestMapping(value = "/pessoa/{id}", method = RequestMethod.GET)
    public ResponseEntity<PessoaEntity> GetById(@PathVariable(value = "id") long id) {
        Optional<PessoaEntity> pessoa = entityPessoaRepository.findById(id);
        if (pessoa.isPresent())
            return new ResponseEntity<PessoaEntity>(pessoa.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/pessoa", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public PessoaEntity Post(@Validated @RequestBody PessoaEntity pessoa) {
        return entityPessoaRepository.save(pessoa);
    }

    @RequestMapping(value = "/pessoa/{id}", method = RequestMethod.PUT)
    public ResponseEntity<PessoaEntity> Put(@PathVariable(value = "id") Long id, @Validated @RequestBody PessoaEntity newPessoa) {
        Optional<PessoaEntity> oldPessoaEntity = entityPessoaRepository.findById(id);
        if (oldPessoaEntity.isPresent()) {
            PessoaEntity pessoa = oldPessoaEntity.get();
            pessoa.setNome(newPessoa.getNome());
            entityPessoaRepository.save(pessoa);
            return new ResponseEntity<PessoaEntity>(pessoa, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/pessoa/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") Long id) {
        Optional<PessoaEntity> pessoa = entityPessoaRepository.findById(id);
        if (pessoa.isPresent()) {
            entityPessoaRepository.delete(pessoa.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}