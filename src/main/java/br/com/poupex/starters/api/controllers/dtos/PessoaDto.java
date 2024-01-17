package br.com.poupex.starters.api.controllers.dtos;

import br.com.poupex.starters.api.domain.enumeration.Sexo;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Pessoa")
public class PessoaDto {

    private Long id;

    @NotBlank
    @Pattern(regexp = "[a-zA-Z ]{1,200}")
    private String nome;

    @CPF
    private String cpf;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

    @NotNull
    private Sexo sexo;

    @NotNull
    private EnderecoDto endereco;

    @NotNull
    private Set<TelefoneDto> telefones;

    @NotNull
    private Set<EmailDto> emails;

}
