package br.com.poupex.starters.api.services;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.poupex.starters.api.commons.exceptions.NegocioException;
import br.com.poupex.starters.api.commons.mapper.ModeloNegocioMapper;
import br.com.poupex.starters.api.commons.support.GenericService;
import br.com.poupex.starters.api.domain.entity.ModeloNegocio;
import br.com.poupex.starters.api.domain.entity.Pessoa;
import br.com.poupex.starters.api.domain.enumeration.StatusEmum;
import br.com.poupex.starters.api.domain.model.input.ModeloNegocioInput;
import br.com.poupex.starters.api.domain.model.output.ModeloNegocioOutput;
import br.com.poupex.starters.api.domain.model.output.ResponseModel;
import br.com.poupex.starters.api.repositories.ModeloNegocioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ModeloNegocioService extends GenericService<ModeloNegocio, Long> {

	private final ModeloNegocioMapper mapper;
	private final ModeloNegocioRepository modeloNegocioRepository;

	@Override
	public ModeloNegocioRepository getRepository() {
		return modeloNegocioRepository;
	}

	public ResponseModel execute() {
		return new ResponseModel(LocalDateTime.now(), HttpStatus.OK.value(), null, null, null, null,
				modeloNegocioRepository.findAll(modeloNegocioRepository.status(StatusEmum.ATIVO)).stream()
						.map(i -> new ModeloNegocioOutput(i.getCodigo(), i.getDescricao(), i.getStatus(),
								i.getDataCriacao().toString()))
						.collect(Collectors.toList()));
	}

	public ResponseModel execute(final ModeloNegocioInput input) {

		var objModelo = modeloNegocioRepository.findModeloNegocioByCodigoAndDescricaoIgnoreCase(input.getCodigo(),
				input.getDescricao());

		if (objModelo.isPresent()) {
			throw new NegocioException("Modelo de Negocio já cadastrado",
					String.format("O Modelo de Negocio %s já existe em nossa base de dados. ", input.getCodigo()));
		}
		// TODO buscar usuario da sessao
		var pessoa = new Pessoa(1L);
		var entity = mapper.toEntity(input);
		entity.setStatus(StatusEmum.ATIVO);
		entity.setDataCriacao(LocalDateTime.now());
		entity.setPessoa(pessoa);
		entity = save(entity);
		return new ResponseModel(LocalDateTime.now(), HttpStatus.CREATED.value(), "Cadastro realizado com sucesso",
				String.format("O Modelo de Negocio %s foi cadastrado com sucesso", entity.getCodigo()),
				"Modelo de Negocio cadastrado com sucesso", null, entity);
	}

	public ResponseModel excluir(Long id) {

		var entity = modeloNegocioRepository.findById(id);
		if (entity.isPresent()) {
			// TODO buscar usuario da sessao
			var modeloNegocio = entity.get();
			modeloNegocio.setStatus(StatusEmum.INATIVO);
			modeloNegocio.setDataAlteracao(LocalDateTime.now());
			modeloNegocio.setDataInativacao(LocalDateTime.now());
			modeloNegocio = save(modeloNegocio);
		}
		return new ResponseModel(LocalDateTime.now(), HttpStatus.NO_CONTENT.value(), "Exclusão realizado com sucesso",
				String.format("O Modelo de Negocio %s foi excluido com sucesso", entity.get().getCodigo()),
				"Modelo de Negocio foi excluido com sucesso", null, entity);
	}

	@Override
	public ModeloNegocio save(ModeloNegocio entity) {
		return super.save(entity);
	}

}
