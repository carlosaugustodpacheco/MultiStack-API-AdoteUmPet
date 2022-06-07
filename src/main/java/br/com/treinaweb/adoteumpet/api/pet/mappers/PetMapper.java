package br.com.treinaweb.adoteumpet.api.pet.mappers;

import org.springframework.stereotype.Component;

import br.com.treinaweb.adoteumpet.api.pet.dtos.PetRequest;
import br.com.treinaweb.adoteumpet.api.pet.dtos.PetResponse;
import br.com.treinaweb.adoteumpet.core.models.Pet;

@Component
public class PetMapper {

    public PetResponse toResponse(Pet pet) {
        return PetResponse.builder()
            .id(pet.getId())
            .nome(pet.getNome())
            .historia(pet.getHistoria())
            .foto(pet.getFoto())
            .build();
    }

    public Pet toModel(PetRequest PetRequest) {
        return Pet.builder()
        .nome(PetRequest.getNome())
        .historia(PetRequest.getHistoria())
        .foto(PetRequest.getFoto())
        .build();
    }
    
}
