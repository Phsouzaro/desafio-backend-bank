package com.br.desafio.picpay.backend.domain;

import com.br.desafio.picpay.backend.domain.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Table(name = "transaction_domain")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "payer_id")
    private User payer;

    @ManyToOne
    @JoinColumn(name = "payee_id")
    private User payee;

    @Column(name = "transaction_value")
    private BigDecimal value;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus status;
}
