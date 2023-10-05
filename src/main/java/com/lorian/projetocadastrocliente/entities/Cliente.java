package com.lorian.projetocadastrocliente.entities;

import jakarta.persistence.*;


import java.time.Instant;

@Entity
@Table(name = "tb_clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String cpf;
    private Double income;
    private Instant birthDate;
    private Integer children;

}
