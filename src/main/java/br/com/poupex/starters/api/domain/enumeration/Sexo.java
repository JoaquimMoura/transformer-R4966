package br.com.poupex.starters.api.domain.enumeration;

import lombok.Getter;

@Getter
public enum Sexo {
    MASCULINO('M'),
    FEMININO('F');

    private Character codigo;

    Sexo(Character codigo) {
        this.codigo = codigo;
    }
}
