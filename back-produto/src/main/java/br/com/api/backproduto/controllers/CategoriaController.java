package br.com.api.backproduto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.backproduto.entities.Categoria;
import br.com.api.backproduto.entities.ExceptionEntity;
import br.com.api.backproduto.services.CategoriaService;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin("*")
public class CategoriaController {

	@Autowired
	private CategoriaService cs;
	
	@GetMapping("/")
	public List<Categoria> getAll() {
		return cs.getAll();
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id){
		//Categoria categoria = cs.getById(id);
		return cs.getById(id);
	}

	@PostMapping("/nova")
	public ResponseEntity<?> saveCategoria(@RequestBody Categoria categoria){
		return cs.cadastrar(categoria);
	}

	@PutMapping("/editar")
	public ResponseEntity<?> alterar(@RequestBody Categoria categoria){
		return cs.editar(categoria);
	}

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<ExceptionEntity> remover(@PathVariable Long id){
		return cs.deletar(id);
	}

}
