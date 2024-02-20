package br.com.theotrin.apirestjava.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Product {
    @Getter
    @Id //gera um ID
    @GeneratedValue(strategy = GenerationType.AUTO) //gera um valor para um ID
    private Integer id;

    @Getter
    @Setter
    @Column(nullable = false) //torna obrigatório esse campo ser preenchido
    private String name;

    @Getter
    @Setter
    @Column(nullable = false) //campos nao pode ser nulo
    private Integer price;

    @Getter
    @Setter
    @Column() 
    private String description;

   
}
