package com.adolfo.finance_tracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionRequest(
        @NotNull @Positive
        BigDecimal amount,
        @NotBlank
        String description,
        @NotNull @PastOrPresent
        LocalDate date,
        @NotBlank
        String type,
        @NotNull
        Long categoryId
) {}
