package br.com.api.backproduto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import br.com.api.backproduto.entities.Produto;
import br.com.api.backproduto.services.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin
public class ProdutoController {

    @Autowired
    private ProdutoService ps;

    @GetMapping("/")
    public List<Produto> listar(){
        return ps.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> lisatrProdutoId(@PathVariable Long id){
        return ps.listarPorId(id);
    }

    @PostMapping("/novo")
    public ResponseEntity<?> salvar(@RequestBody Produto produto){
        return ps.salvarNovo(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable Long id, @RequestBody Produto produto){
        ps.atualizar(id, produto);
        return new ResponseEntity<Produto>(produto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> apagar(@PathVariable Long id){
        ps.deletar(id);
        return ResponseEntity.noContent().build();
    }
    
}
