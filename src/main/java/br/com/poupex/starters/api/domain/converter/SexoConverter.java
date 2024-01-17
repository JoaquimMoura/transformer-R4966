package br.com.poupex.starters.api.domain.converter;

import br.com.poupex.starters.api.domain.enumeration.Sexo;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Objects;

public class SexoConverter implements AttributeConverter<Sexo, Character> {

    @Override
    public Character convertToDatabaseColumn(Sexo attribute) {
        return Objects.nonNull(attribute) ? attribute.getCodigo() : null;
    }

    @Override
    public Sexo convertToEntityAttribute(Character dbData) {
        return Arrays.stream(Sexo.values())
            .filter(e -> e.getCodigo().equals(dbData))
            .findFirst().orElse(null);
    }
}
