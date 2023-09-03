package com.br.desafio.picpay.backend.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Table(name = "users")
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

    @OneToMany(mappedBy = "payer")
    private List<Transactions> transactionsASPayer;
    @OneToMany(mappedBy = "payee")
    private List<Transactions> transactionsASPayee;

}
