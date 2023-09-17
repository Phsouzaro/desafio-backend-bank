package com.br.desafio.picpay.backend.domain;

import com.br.desafio.picpay.backend.domain.enums.UserType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Table(name = "user_domain")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String cpfCnpj;

    @Enumerated(value = EnumType.STRING)
    private UserType userType;

    @OneToMany(mappedBy = "payer")
    private List<Transaction> transactionAsPayer = new ArrayList<>();

    @OneToMany(mappedBy = "payee")
    private List<Transaction> transactionAsPayee = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Wallet wallet;

    public void addTransactionAsPayee(Transaction transaction) {
        this.getTransactionAsPayee().add(transaction);
    }
}
