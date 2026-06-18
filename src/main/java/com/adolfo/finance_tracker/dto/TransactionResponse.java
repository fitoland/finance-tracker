package com.adolfo.finance_tracker.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionResponse(
        Long id,
        BigDecimal amount,
        String description,
        LocalDate date,
        String type,
        String categoryName
) {}
