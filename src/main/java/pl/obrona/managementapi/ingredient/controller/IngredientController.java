package pl.obrona.managementapi.ingredient.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.obrona.managementapi.ingredient.model.command.CreateIngredientCommand;
import pl.obrona.managementapi.ingredient.model.command.RestockCommand;
import pl.obrona.managementapi.ingredient.model.dto.IngredientDto;
import pl.obrona.managementapi.ingredient.service.IngredientService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ingredients")
@RequiredArgsConstructor
public class IngredientController {
    private final IngredientService ingredientService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IngredientDto create(@RequestBody @Valid CreateIngredientCommand command) {
        return ingredientService.create(command);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<IngredientDto> getAll(@RequestParam(required = false) Boolean lowStock) {
        return ingredientService.getAll(lowStock);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public IngredientDto getById(@PathVariable Long id) {
        return ingredientService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        ingredientService.deleteById(id);
    }

    @PatchMapping("/restock/{id}")
    @ResponseStatus(HttpStatus.OK)
    public IngredientDto restockById(
            @PathVariable Long id,
            @RequestBody @Valid RestockCommand command) {
        return ingredientService.restock(id, command);
    }
}
