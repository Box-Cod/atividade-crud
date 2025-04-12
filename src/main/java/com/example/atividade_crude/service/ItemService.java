package com.example.atividade_crude.service;

import com.example.atividade_crude.model.Item;
import com.example.atividade_crude.model.Personagem;
import com.example.atividade_crude.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findById(Long id) {
        Item item = validateOptinal(itemRepository.findById(id));
        return item;
    }

    public Item create(Item item) {
        return itemRepository.save(item);
    }

    public Item update(Item item, Long id) {
        Item updatedItem = findById(id);

        updatedItem.setNome(item.getNome());
        updatedItem.setTipo(item.getTipo());
        updatedItem.setDefesa(item.getDefesa());
        updatedItem.setForca(item.getForca());

        itemRepository.save(updatedItem);

        return updatedItem;
    }

    public Item delete(Long id) {
        Item deletedItem = findById(id);
        itemRepository.delete(deletedItem);
        return deletedItem;
    }


    private Item validateOptinal(Optional<Item> itemOptional) {

        if (itemOptional.isPresent()) {
            return itemOptional.get();
        }

        throw new RuntimeException("Item não encontrado");
    }
}
