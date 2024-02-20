package br.com.theotrin.apirestjava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.theotrin.apirestjava.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    
}
