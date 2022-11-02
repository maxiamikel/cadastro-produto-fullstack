package br.com.api.backproduto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.backproduto.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
