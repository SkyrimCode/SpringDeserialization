package com.skyrim.customDeserialization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Transaction {
    private BigDecimal amount;
    private Currency currency;
    private LocalDateTime date;
}
