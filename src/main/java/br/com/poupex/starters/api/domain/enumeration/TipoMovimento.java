package br.com.poupex.starters.api.domain.enumeration;

import lombok.Getter;

@Getter
public enum TipoMovimento {

    CREDITO('C'),
    DEBITO('D');

    private final Character codigo;

    TipoMovimento(Character codigo){
        this.codigo = codigo;
    }
}
