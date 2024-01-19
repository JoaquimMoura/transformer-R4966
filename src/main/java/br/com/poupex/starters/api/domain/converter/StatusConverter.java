package br.com.poupex.starters.api.domain.converter;

import java.util.Arrays;
import java.util.Objects;

import javax.persistence.AttributeConverter;

import br.com.poupex.starters.api.domain.enumeration.StatusEmum;

public class StatusConverter implements AttributeConverter<StatusEmum, Character> {

    @Override
    public Character convertToDatabaseColumn(StatusEmum attribute) {
        return Objects.nonNull(attribute) ? attribute.getCodigo() : null;
    }

    @Override
    public StatusEmum convertToEntityAttribute(Character dbData) {
        return Arrays.stream(StatusEmum.values())
            .filter(e -> e.getCodigo().equals(dbData))
            .findFirst().orElse(null);
    }
}
