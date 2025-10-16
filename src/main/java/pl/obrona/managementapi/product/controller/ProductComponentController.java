package pl.obrona.managementapi.product.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.obrona.managementapi.product.model.dto.ProductComponentDto;
import pl.obrona.managementapi.product.service.ProductComponentService;

import java.util.List;

@RestController
@RequestMapping("api/v1/product-components")
@RequiredArgsConstructor
public class ProductComponentController {

    private final ProductComponentService productComponentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductComponentDto> getAll() {
        return productComponentService.getAll();
    }

}
