package br.com.poupex.starters.api.domain.model.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusInstrumentoFinanceiroOutput {
	
	private Long codigo;
	private String descricao;

}
