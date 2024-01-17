package br.com.poupex.starters.api.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Categoria")
public class CategoriaDto {

    private Long id;

    @NotNull
    private String descricao;

}
