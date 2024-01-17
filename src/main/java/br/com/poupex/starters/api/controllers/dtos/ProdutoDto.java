package br.com.poupex.starters.api.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProdutoDto(
    int id,
    String nome,
    String sigla,
    boolean ativo){
}
