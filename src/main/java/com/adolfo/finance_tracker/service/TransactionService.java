package com.adolfo.finance_tracker.service;

import com.adolfo.finance_tracker.dto.TransactionRequest;
import com.adolfo.finance_tracker.dto.TransactionResponse;
import com.adolfo.finance_tracker.model.Category;
import com.adolfo.finance_tracker.model.Transaction;
import com.adolfo.finance_tracker.repository.CategoryRepository;
import com.adolfo.finance_tracker.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

        return toResponse(transaction);
    }

    public List<TransactionResponse> findAll(){
        List<Transaction> transactions = transactionRepository.findAll();
        List<TransactionResponse> transactionResponseList = new ArrayList<>();

        for(Transaction transaction : transactions){
            transactionResponseList.add(toResponse(transaction));
        }

        return transactionResponseList;
    }

    public TransactionResponse findById(Long id){
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        return toResponse(transaction);
    }

    public TransactionResponse update(Long id, TransactionRequest transactionRequest){
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        Category category = categoryRepository.findById(transactionRequest.categoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        transaction.setAmount(transactionRequest.amount());
        transaction.setDescription(transactionRequest.description());
        transaction.setDate(transactionRequest.date());
        transaction.setType(transactionRequest.type());

        transaction.setCategory(category);

        return toResponse(transactionRepository.save(transaction));
    }

    public void delete(Long id){
        transactionRepository.deleteById(id);
    }

    private TransactionResponse toResponse(Transaction transaction){
        return new TransactionResponse(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getDescription(),
                transaction.getDate(),
                transaction.getType(),
                transaction.getCategory().getName()
        );
    }
}
