package br.com.poupex.starters.api.domain.enumeration;

import lombok.Getter;

@Getter
public enum StatusEmum {
	ATIVO('A'), 
	INATIVO('I');

	private Character codigo;

	StatusEmum(Character codigo) {
		this.codigo = codigo;
	}
}
