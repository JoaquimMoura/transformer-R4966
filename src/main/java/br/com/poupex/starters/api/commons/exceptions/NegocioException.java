package br.com.poupex.starters.api.commons.exceptions;

import java.util.List;

import org.springframework.http.HttpStatus;

import br.com.poupex.starters.api.domain.model.output.ValidacaoModel;
import lombok.Getter;

@Getter
public class NegocioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final HttpStatus status;
	private final String titulo;
	private final String mensagem;
	private final List<ValidacaoModel> validacoes;
	private final Object conteudo;

	public NegocioException(final HttpStatus status, final String titulo, final String mensagem) {
		super(titulo);
		this.status = status;
		this.titulo = titulo;
		this.mensagem = mensagem;
		this.validacoes = null;
		this.conteudo = null;
	}

	public NegocioException(final String titulo, final String mensagem) {
		super(titulo);
		this.status = HttpStatus.BAD_REQUEST;
		this.titulo = titulo;
		this.mensagem = mensagem;
		this.validacoes = null;
		this.conteudo = null;
	}

	public NegocioException(final String titulo, final String mensagem, final List<ValidacaoModel> validacoes,
			final Object conteudo) {
		super(titulo);
		this.status = HttpStatus.BAD_REQUEST;
		this.titulo = titulo;
		this.mensagem = mensagem;
		this.validacoes = validacoes;
		this.conteudo = conteudo;
	}
}
