package com.adolfo.finance_tracker.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionRequest(
    BigDecimal amount,
    String description,
    LocalDate date,
    String type,
    Long categoryId
) {}
