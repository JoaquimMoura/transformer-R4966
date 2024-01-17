package br.com.poupex.starters.api.domain.model.output;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TipoInstrumentoFinanceiroInputOutput {
	
	private Long codigo;
	private String nome;
	private String sigla;
	private Boolean habilitado;

}

