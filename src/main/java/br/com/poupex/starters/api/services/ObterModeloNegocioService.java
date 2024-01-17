package br.com.poupex.starters.api.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.poupex.starters.api.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.starters.api.domain.model.output.ResponseModel;
import br.com.poupex.starters.api.repositories.ModeloNegocioRepository;

import javax.transaction.Transactional;

@Service
public class ObterModeloNegocioService {

	private final ModeloNegocioRepository modeloNegocioRepository;

	public ObterModeloNegocioService(ModeloNegocioRepository modeloNegocioRepository) {
		this.modeloNegocioRepository = modeloNegocioRepository;
	}

    public ResponseModel listar() {
        return modeloNegocioRepository.
            .map(modeloNegocioOutput -> ResponseModel.builder()
                .codigo(HttpStatus.OK.value())
                .datahora(LocalDateTime.now())
                .conteudo(modeloNegocioOutput)
                .build())
            .orElseThrow(() -> new RecursoNaoEncontradoException("Modelo de Negócio",
                "Não foi possível encontrar o modelo de negocio pela descricao: " + descricao));
    }
    public ResponseModel byDescricao(String descricao) {
        return modeloNegocioRepository.findModeloNegocioByDescricaoIgnoreCase(descricao)
            .map(modeloNegocioOutput -> ResponseModel.builder()
                .codigo(HttpStatus.OK.value())
                .datahora(LocalDateTime.now())
                .conteudo(modeloNegocioOutput)
                .build())
            .orElseThrow(() -> new RecursoNaoEncontradoException("Modelo de Negócio",
                "Não foi possível encontrar o modelo de negocio pela descricao: " + descricao));
    }

	public ResponseModel getModeloNegocioPorCodigo(final String codigo) {
		return Optional.ofNullable(modeloNegocioRepository.findModeloNegocioByCodigoIgnoreCase(codigo)
				.map(modeloNegocioOutput -> ResponseModel.builder()
															.codigo(HttpStatus.OK.value())
															.datahora(LocalDateTime.now())
															.conteudo(modeloNegocioOutput)
															.build())
				.orElseThrow(() -> new RecursoNaoEncontradoException("Modelo de Negócio",
						"Não foi possível encontrar o modelo de negocio."));
	}


    @Transactional
    public ResponseModel execute(Pageable pageable) {
        return new ResponseModel(
            LocalDateTime.now(),
            HttpStatus.OK.value(),
            "Cadastro",
            null,
            null,
            null,
            mapper.convert(this.getModeloNegocioPorCodigo(empresa))
        );
    }

}
