package br.com.api.backproduto.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.backproduto.entities.Categoria;
import br.com.api.backproduto.entities.ExceptionEntity;
import br.com.api.backproduto.repositories.CategoriaRepository;

@Service
@Transactional
public class CategoriaService {

	@Autowired
	private ExceptionEntity exceptionEntity;

	@Autowired
	private CategoriaRepository cr;
	

	public List<Categoria> getAll(){
		return cr.findAll();
	}

	public ResponseEntity<?> getById(Long id){
		if(cr.findById(id).isPresent()){
			Categoria categoria = cr.findById(id).get();
			return  new ResponseEntity<Categoria>(categoria, HttpStatus.OK);
		}else{
			exceptionEntity.setMensaje("O codigo informado não existe!!");
			return new ResponseEntity<ExceptionEntity>(exceptionEntity, HttpStatus.BAD_REQUEST);
		}
		
	}

	public ResponseEntity<?> cadastrar(Categoria categoria){

		if(categoria.getNome().equals("")){
			exceptionEntity.setMensaje("O nome da categiroa é obrigatorio");
		return new ResponseEntity<ExceptionEntity>(exceptionEntity, HttpStatus.BAD_REQUEST);
		}else{
			Categoria nova = cr.save(categoria);
			return new ResponseEntity<Categoria>(nova, HttpStatus.CREATED);
		}

	}

	public ResponseEntity<?> editar(Categoria categoria){

		if(categoria.getNome().equals(" ")){
			exceptionEntity.setMensaje("O nome da categiroa é obrigatorio");
		return new ResponseEntity<ExceptionEntity>(exceptionEntity, HttpStatus.BAD_REQUEST);
		}else{
			Categoria nova = cr.saveAndFlush(categoria);
			return new ResponseEntity<Categoria>(nova, HttpStatus.CREATED);
		}

	}

	public ResponseEntity<ExceptionEntity> deletar(Long id){
		if(cr.findById(id).isPresent()){
			cr.deleteById(id);
			exceptionEntity.setMensaje("Categoria removida com sucesso");
			return new ResponseEntity<ExceptionEntity>(exceptionEntity, HttpStatus.OK);
		}else{
			exceptionEntity.setMensaje("O codigo da categoria informado não existe");
			return new ResponseEntity<ExceptionEntity>(exceptionEntity, HttpStatus.BAD_REQUEST);
		}
	}


}
