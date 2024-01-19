package br.com.poupex.starters.api.controllers.dtos;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.poupex.starters.api.domain.enumeration.StatusEmum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "ModeloNegocio")
public class ModeloNegocioDto {

	@NotNull
	String codigo;

	@NotNull
	String descricao;

	@JsonProperty(value = "status")
	StatusEmum status;

	@NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataCriacao;

    @NotNull
	@JsonProperty(value = "pessoa")
	PessoaDto pessoa;
}
