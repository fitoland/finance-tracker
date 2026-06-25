package com.adolfo.finance_tracker.controller;

import com.adolfo.finance_tracker.dto.TransactionRequest;
import com.adolfo.finance_tracker.dto.TransactionResponse;
import com.adolfo.finance_tracker.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public TransactionResponse create(@RequestBody TransactionRequest transactionRequest){
        return transactionService.create(transactionRequest);
    }

    @GetMapping
    public List<TransactionResponse> findAll(){
        return transactionService.findAll();
    }

    @GetMapping("/{id}")
    public TransactionResponse findById(@PathVariable Long id){
        return transactionService.findById(id);
    }

    @PutMapping("/{id}")
    public TransactionResponse update(@PathVariable Long id, @RequestBody TransactionRequest transactionRequest){
        return transactionService.update(id, transactionRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        transactionService.delete(id);
    }



}
