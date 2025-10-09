package pl.obrona.managementapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.obrona.managementapi.model.command.CreateProductComponentCommand;
import pl.obrona.managementapi.model.dto.ProductComponentDto;
import pl.obrona.managementapi.service.ProductComponentService;

import java.util.List;

@RestController
@RequestMapping("api/v1/product-components")
@RequiredArgsConstructor
public class ProductComponentController {

    private final ProductComponentService productComponentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductComponentDto create(@RequestBody @Valid CreateProductComponentCommand command) {
        return productComponentService.create(command);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductComponentDto> getAll() {
        return productComponentService.getAll();
    }

    @GetMapping("/{id}")
    public ProductComponentDto getById(@PathVariable Long id) {
        return productComponentService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productComponentService.deleteById(id);
    }
}
