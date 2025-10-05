package pl.obrona.managementapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.obrona.managementapi.exception.NotFoundException;
import pl.obrona.managementapi.mapper.TransactionMapper;
import pl.obrona.managementapi.model.Transaction;
import pl.obrona.managementapi.model.command.CreateTransactionCommand;
import pl.obrona.managementapi.model.dto.TransactionDto;
import pl.obrona.managementapi.repository.TransactionRepository;

import java.util.List;

import static pl.obrona.managementapi.mapper.TransactionMapper.mapFromCommand;
import static pl.obrona.managementapi.mapper.TransactionMapper.mapToDto;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionDto create(CreateTransactionCommand command) {
        Transaction transaction = transactionRepository.save(mapFromCommand(command));
        return mapToDto(transaction);
    }

    public List<TransactionDto> getAll() {
        return transactionRepository.findAll().stream()
                .map(TransactionMapper::mapToDto)
                .toList();
    }

    public TransactionDto getById(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Transaction not found with id: " + id));
        return mapToDto(transaction);
    }
}
