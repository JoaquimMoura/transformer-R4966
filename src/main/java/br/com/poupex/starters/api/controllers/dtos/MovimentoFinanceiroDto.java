package br.com.poupex.starters.api.controllers.dtos;

import br.com.poupex.starters.api.domain.enumeration.TipoMovimento;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Movimento Financeiro")
public class MovimentoFinanceiroDto {

    private Long id;

    @NotNull
    private Date data;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private TipoMovimento tipo;

    private CategoriaDto categoria;

    private Long pessoaId;

}
