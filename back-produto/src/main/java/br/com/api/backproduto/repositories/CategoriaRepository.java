package br.com.api.backproduto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.backproduto.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
