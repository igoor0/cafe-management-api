package pl.obrona.managementapi.cost.controller;

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
import pl.obrona.managementapi.cost.model.CreateFixedCostCommand;
import pl.obrona.managementapi.cost.model.FixedCost;
import pl.obrona.managementapi.cost.service.FixedCostService;

import java.util.List;

@RestController
@RequestMapping("api/v1/fixed-costs")
@RequiredArgsConstructor
public class FixedCostController {

    private final FixedCostService fixedCostService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FixedCost createFixedCost(@RequestBody CreateFixedCostCommand command) {
        return fixedCostService.create(command);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FixedCost> getAll() {
        return fixedCostService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFixedCost(@PathVariable Long id) {
        fixedCostService.deleteById(id);
    }
}
