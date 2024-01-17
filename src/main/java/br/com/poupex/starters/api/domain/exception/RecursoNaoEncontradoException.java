package br.com.poupex.starters.api.domain.exception;

import org.springframework.http.HttpStatus;

import br.com.poupex.starters.api.commons.exceptions.NegocioException;

public class RecursoNaoEncontradoException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public RecursoNaoEncontradoException(final String recurso, final String mensagem) {
		super(HttpStatus.NOT_FOUND, recurso, mensagem);
	}

	public RecursoNaoEncontradoException(final String mensagem) {
		super(HttpStatus.NOT_FOUND, "Não foi encontrado", mensagem);
	}

}
