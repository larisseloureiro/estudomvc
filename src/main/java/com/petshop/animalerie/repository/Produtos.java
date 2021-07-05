package com.petshop.animalerie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petshop.animalerie.model.Produto;

public interface Produtos extends JpaRepository<Produto, Long > {

}
