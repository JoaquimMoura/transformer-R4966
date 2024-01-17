package br.com.poupex.starters.api.controllers.dtos;

import br.com.poupex.starters.api.domain.enumeration.TipoTelefone;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Telefone")
public class TelefoneDto {

    private Long id;

    @Pattern(regexp = "[0-9]{10,11}")
    @NotNull
    private String numero;

    @NotNull
    private TipoTelefone tipo;

    private Long pessoaId;

}
