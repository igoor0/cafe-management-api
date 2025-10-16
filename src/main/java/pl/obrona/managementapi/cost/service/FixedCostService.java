package pl.obrona.managementapi.cost.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.obrona.managementapi.cost.model.CostType;
import pl.obrona.managementapi.cost.model.CreateFixedCostCommand;
import pl.obrona.managementapi.cost.model.FixedCost;
import pl.obrona.managementapi.cost.repository.FixedCostRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FixedCostService {

    private final FixedCostRepository fixedCostRepository;

    public List<FixedCost> getAll() {
        return fixedCostRepository.findAll();
    }

    public FixedCost create(CreateFixedCostCommand command) {
        return fixedCostRepository.save(FixedCost.builder()
                .description(command.getDescription())
                .cost(command.getCost())
                .costType(CostType.BILLING)
                .createdAt(LocalDateTime.now())
                .build());
    }

    public void deleteById(Long id) {
        fixedCostRepository.deleteById(id);
    }
}
