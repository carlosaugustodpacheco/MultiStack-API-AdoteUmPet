package br.com.treinaweb.adoteumpet.api.pet.mappers;

import org.springframework.stereotype.Component;

import br.com.treinaweb.adoteumpet.api.pet.dtos.PetResponse;
import br.com.treinaweb.adoteumpet.core.models.Pet;

@Component
public class PetMapper {

    public PetResponse toResponse(Pet pet) {
        var PetResponse = new PetResponse();
        PetResponse.setId(pet.getId());
        PetResponse.setNome(pet.getNome());
        PetResponse.setHistoria(pet.getHistoria());
        PetResponse.setNome(pet.getFoto());
        return PetResponse;
    }
    
}
