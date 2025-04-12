package com.example.atividade_crude.controller;

import com.example.atividade_crude.model.Item;
import com.example.atividade_crude.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/itens")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<List<Item>> findAll() {
        return new ResponseEntity<>(itemService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Item> create(@RequestBody Item item) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(item.getId()).toUri();
        Item resposta = itemService.create(item);
        return ResponseEntity.created(uri).body(resposta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> update(@RequestBody Item item, @PathVariable Long id) {
        return ResponseEntity.ok().body(itemService.update(item, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Item> delete(@PathVariable Long id) {
        return ResponseEntity.ok().body(itemService.delete(id));
    }
}
