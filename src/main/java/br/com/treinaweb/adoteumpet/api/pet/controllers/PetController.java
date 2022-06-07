package br.com.treinaweb.adoteumpet.api.pet.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.adoteumpet.api.pet.dtos.PetResponse;
import br.com.treinaweb.adoteumpet.api.pet.mappers.PetMapper;
import br.com.treinaweb.adoteumpet.core.models.Pet;
import br.com.treinaweb.adoteumpet.core.repositories.PetRepository;

@RestController
public class PetController {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PetMapper petMapper;

    @GetMapping("/pets")
    public List<PetResponse> findAll() {
        var pets = petRepository.findAll(); 
        var PetResponses = new ArrayList<PetResponse>();
        for (Pet pet : pets) {
            PetResponses.add(petMapper.toResponse(pet));
        }
        return PetResponses;

    }
    
}
