package com.example.atividade_crude.controller;

import com.example.atividade_crude.model.Personagem;
import com.example.atividade_crude.service.PersonagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/personagens")
public class PersonagemController {

    @Autowired
    public PersonagemService personagemService;

    @GetMapping
    public ResponseEntity<List<Personagem>> findAll() {
        return ResponseEntity.ok().body(personagemService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Personagem> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(personagemService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Personagem> create(@RequestBody Personagem personagem) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(personagem.getId()).toUri();

        return ResponseEntity.created(uri).body(personagem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personagem> update(@RequestBody Personagem personagem, @PathVariable Long id) {
        return ResponseEntity.ok().body(personagemService.update(personagem, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Personagem> delete(@PathVariable Long id) {
        return ResponseEntity.ok().body(personagemService.delete(id));
    }


}
