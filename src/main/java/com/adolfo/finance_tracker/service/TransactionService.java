package com.adolfo.finance_tracker.service;

import com.adolfo.finance_tracker.dto.TransactionRequest;
import com.adolfo.finance_tracker.dto.TransactionResponse;
import com.adolfo.finance_tracker.model.Category;
import com.adolfo.finance_tracker.model.Transaction;
import com.adolfo.finance_tracker.repository.CategoryRepository;
import com.adolfo.finance_tracker.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;

    public TransactionResponse create(TransactionRequest transactionRequest){
        Category category = categoryRepository.findById(transactionRequest.categoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Transaction transaction = new Transaction();
        transaction.setAmount(transactionRequest.amount());
        transaction.setDescription(transactionRequest.description());
        transaction.setDate(transactionRequest.date());
        transaction.setType(transactionRequest.type());
        transaction.setCategory(category);

        transaction = transactionRepository.save(transaction);

        return new TransactionResponse(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getDescription(),
                transaction.getDate(),
                transaction.getType(),
                category.getName()
        );

    }
}
