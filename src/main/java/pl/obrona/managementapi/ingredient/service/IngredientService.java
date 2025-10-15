package pl.obrona.managementapi.ingredient.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.obrona.managementapi.common.exception.NotFoundException;
import pl.obrona.managementapi.cost.model.CostType;
import pl.obrona.managementapi.cost.model.FixedCost;
import pl.obrona.managementapi.cost.repository.FixedCostRepository;
import pl.obrona.managementapi.ingredient.mapper.IngredientMapper;
import pl.obrona.managementapi.ingredient.model.Ingredient;
import pl.obrona.managementapi.ingredient.model.command.CreateIngredientCommand;
import pl.obrona.managementapi.ingredient.model.command.RestockCommand;
import pl.obrona.managementapi.ingredient.model.dto.IngredientDto;
import pl.obrona.managementapi.ingredient.repository.IngredientRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static pl.obrona.managementapi.ingredient.mapper.IngredientMapper.mapFromCommand;
import static pl.obrona.managementapi.ingredient.mapper.IngredientMapper.mapToDto;

@Service
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    private final FixedCostRepository fixedCostRepository;

    public IngredientDto create(CreateIngredientCommand command) {
        return mapToDto(ingredientRepository.save(mapFromCommand(command)));
    }

    public List<IngredientDto> getAll(Boolean lowStock) {
        return ingredientRepository.findAllByLowStock(lowStock).stream()
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

        BigDecimal newQuantity = ingredient.getStockQuantity().add(command.getQuantity());
        ingredient.setStockQuantity(newQuantity);
        ingredientRepository.save(ingredient);

        BigDecimal costAmount = command.getQuantity().multiply(ingredient.getUnitCost());

        fixedCostRepository.save(FixedCost.builder()
                .description("Ingredient restock: " + ingredient.getName())
                .cost(costAmount)
                .costType(CostType.RESTOCK)
                .createdAt(LocalDateTime.now())
                .build()
        );

        return mapToDto(ingredient);
    }

}
