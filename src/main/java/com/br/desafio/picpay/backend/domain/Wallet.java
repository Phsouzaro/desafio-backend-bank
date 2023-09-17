package com.br.desafio.picpay.backend.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Table(name = "wallet_domain")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    private BigDecimal balance;

    public void addBalance(BigDecimal value) {
        this.balance = this.balance.add(value);
    }

    public void removeBalance(BigDecimal value) {
        this.balance = this.balance.subtract(value);
    }
}
