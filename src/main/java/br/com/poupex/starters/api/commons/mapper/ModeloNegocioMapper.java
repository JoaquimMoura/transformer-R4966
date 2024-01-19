package br.com.poupex.starters.api.commons.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.poupex.starters.api.domain.entity.ModeloNegocio;
import br.com.poupex.starters.api.domain.model.input.ModeloNegocioInput;

@Mapper(componentModel = "spring", imports = java.util.Objects.class)
public abstract class ModeloNegocioMapper {
	
	@Mapping(target = "codigo", source = "codigo")
	public abstract ModeloNegocio toEntity(ModeloNegocioInput dto);
	

}
