package br.com.poupex.starters.api.domain.model.output;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.poupex.starters.api.domain.enumeration.StatusEmum;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ModeloNegocioOutput {

    private String codigo;
    private String descricao;
    private StatusEmum status;
    private String dataCriacao;

}

