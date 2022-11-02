package br.com.api.backproduto.entities;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class ExceptionEntity {
    private String mensaje;
    
}
