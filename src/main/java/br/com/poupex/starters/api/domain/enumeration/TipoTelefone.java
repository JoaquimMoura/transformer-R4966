package br.com.poupex.starters.api.domain.enumeration;

import lombok.Getter;

@Getter
public enum TipoTelefone {

    CELULAR('C'),
    FIXO('F');

    private Character codigo;

    TipoTelefone(Character codigo) {
        this.codigo = codigo;
    }
}
