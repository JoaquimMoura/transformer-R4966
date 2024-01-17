package br.com.poupex.starters.api.controllers.dtos;

import br.com.poupex.starters.api.domain.enumeration.UF;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Endereço")
public class EnderecoDto {

    private Long id;

    @NotBlank
    private String endereco;

    private String bairro;

    private String cidade;

    private Integer numero;

    private String cep;

    private UF uf;

    private Long pessoaId;

}
