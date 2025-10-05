package pl.obrona.managementapi.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.obrona.managementapi.exception.NotFoundException;
import pl.obrona.managementapi.mapper.IngredientMapper;
import pl.obrona.managementapi.model.Ingredient;
import pl.obrona.managementapi.model.command.CreateIngredientCommand;
import pl.obrona.managementapi.model.command.RestockCommand;
import pl.obrona.managementapi.model.dto.IngredientDto;
import pl.obrona.managementapi.repository.IngredientRepository;

import java.util.List;

import static pl.obrona.managementapi.mapper.IngredientMapper.mapFromCommand;
import static pl.obrona.managementapi.mapper.IngredientMapper.mapToDto;

@Service
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientDto create(CreateIngredientCommand command) {
        Ingredient ingredient = ingredientRepository.save(mapFromCommand(command));
        return mapToDto(ingredient);
    }

    public List<IngredientDto> getAll() {
        return ingredientRepository.findAll().stream()
                .map(IngredientMapper::mapToDto)
                .toList();
    }

    public IngredientDto getById(Long id) {
        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ingredient not found with id: " + id));
        return mapToDto(ingredient);
    }

    public void deleteById(Long id) {
        ingredientRepository.deleteById(id);
    }

    @Transactional
    public IngredientDto restock(Long id, RestockCommand command) {
        Ingredient ingredient = ingredientRepository.findWithLockingById(id)
                .orElseThrow(() -> new NotFoundException("Ingredient not found with id: " + id));

        ingredient.restock(command.getQuantity());

        return mapToDto(ingredient);
    }
}
