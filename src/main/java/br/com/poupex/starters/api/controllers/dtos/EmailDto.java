package br.com.poupex.starters.api.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Email")
public class EmailDto {

    private Long id;

    @NotBlank
    private String email;

    private Long pessoaId;

}
