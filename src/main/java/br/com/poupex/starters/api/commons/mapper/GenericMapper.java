package br.com.poupex.starters.api.commons.mapper;

import br.com.poupex.starters.api.domain.entity.AbstractEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GenericMapper <E extends AbstractEntity, DTO> {

    DTO convert(E entity);

    E convert(DTO dto);

    List<DTO> convert(List<E> lista);

    default Page<DTO> convert(Page<E> pageEntity) {
        return pageEntity.map(this::convert);
    }

}
