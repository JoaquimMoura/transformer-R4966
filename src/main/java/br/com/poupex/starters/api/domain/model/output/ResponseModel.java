package br.com.poupex.starters.api.domain.model.output;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Builder;

@Builder
public record ResponseModel(LocalDateTime datahora, Integer codigo, String titulo, String detalhe, String mensagem,
		List<ValidacaoModel> validacoes, Object conteudo) {
	public ResponseModel(final Object conteudo) {
		this(LocalDateTime.now(), HttpStatus.OK.value(), null, null, null, null, conteudo);
	}

	public ResponseModel(final String mensagem, final Object conteudo) {
		this(LocalDateTime.now(), HttpStatus.OK.value(), null, null, mensagem, null, conteudo);
	}

}
