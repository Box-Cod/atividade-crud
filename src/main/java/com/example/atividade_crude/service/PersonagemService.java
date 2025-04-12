package com.example.atividade_crude.service;

import com.example.atividade_crude.model.Personagem;
import com.example.atividade_crude.repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonagemService {

    @Autowired
    PersonagemRepository personagemRepository;

    public List<Personagem> findAll() {
        return personagemRepository.findAll();
    }

    public Personagem findById(Long id) {
        Personagem personagem = validateOptinal(personagemRepository.findById(id));
        return personagem;
    }

    public Personagem create(Personagem personagem) {
        return personagemRepository.save(personagem);
    }

    public Personagem update(Personagem personagem, Long id) {
        Personagem updatedPersonagem = findById(id);

        updatedPersonagem.setNome(personagem.getNome());
        updatedPersonagem.setNomeAventureiro(personagem.getNomeAventureiro());
        updatedPersonagem.setClasse(personagem.getClasse());
        updatedPersonagem.setLevel(personagem.getLevel());
        updatedPersonagem.setDefesa(personagem.getDefesa());
        updatedPersonagem.setForca(personagem.getForca());
        updatedPersonagem.setItems(personagem.getItems());

        personagemRepository.save(updatedPersonagem);

        return updatedPersonagem;
    }

    public Personagem delete(Long id) {
        Personagem deletedPersonagem = findById(id);
        personagemRepository.delete(deletedPersonagem);
        return deletedPersonagem;
    }

    private Personagem validateOptinal(Optional<Personagem> personagemOptional) {

        if (personagemOptional.isPresent()) {
            return personagemOptional.get();
        }

        throw new RuntimeException("Personagem não encontrado");
    }
}
