package br.com.api.backproduto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.api.backproduto.entities.ExceptionEntity;
import br.com.api.backproduto.entities.Produto;
import br.com.api.backproduto.repositories.ProdutoRepository;

@Service
@Transactional
public class ProdutoService {

    @Autowired
    private ProdutoRepository repo;

    @Autowired
    private ExceptionEntity exceptionEntity;

    public List<Produto> listar(){
        return repo.findAll();
    }

    public ResponseEntity<?> listarPorId(Long id){
        
        if(repo.findById(id).isPresent()){
            Produto produto = repo.findById(id).get();
            return new ResponseEntity<Produto>(produto, HttpStatus.OK);
        }else{
            exceptionEntity.setMensaje("O codigo do produto informado não existe");
            return new ResponseEntity<ExceptionEntity>(exceptionEntity, HttpStatus.BAD_REQUEST);

        }
    }

    public ResponseEntity<?> salvarNovo(Produto produto){
        Produto novo = repo.saveAndFlush(produto);
        return new ResponseEntity<Produto>(novo, HttpStatus.CREATED);
    }
    /**
     * private String nome;
    private String descricao;
    private float precoCompra;
    private float precoVenda;
    private Integer quantidade;
    private Date validade;
     * @param id
     * @param produto
     * @return
     */

    public ResponseEntity<?> atualizar(Long id,Produto produto){
        if(repo.findById(id).isPresent()){
            Produto obj = repo.findById(id).get();
            obj.setNome(produto.getNome());
            obj.setDescricao(produto.getDescricao());
            obj.setPrecoCompra(produto.getPrecoCompra());
            obj.setPrecoVenda(produto.getPrecoVenda());
            obj.setQuantidade(produto.getQuantidade());
            obj.setValidade(produto.getValidade());
            repo.save(obj);
            return new ResponseEntity<Produto>(obj, HttpStatus.OK);
        }else{
            exceptionEntity.setMensaje("O código do produto informado não existe");
            return new ResponseEntity<ExceptionEntity>(exceptionEntity, HttpStatus.BAD_REQUEST);
        }
       
    }

    public ResponseEntity<?> deletar(Long id){
        if(repo.findById(id).isPresent()){
            repo.deleteById(id);
            exceptionEntity.setMensaje("O produto com código ["+id+"] foi excluido com sucesso");
            return new ResponseEntity<ExceptionEntity>(exceptionEntity, HttpStatus.OK);
        }else{
            exceptionEntity.setMensaje("O código do produto informado não existe");
            return new ResponseEntity<ExceptionEntity>(exceptionEntity, HttpStatus.BAD_REQUEST);
        }
    }

}
