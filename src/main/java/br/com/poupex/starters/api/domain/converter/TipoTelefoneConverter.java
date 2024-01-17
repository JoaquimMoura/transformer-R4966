package br.com.poupex.starters.api.domain.converter;

import br.com.poupex.starters.api.domain.enumeration.TipoTelefone;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Objects;

public class TipoTelefoneConverter implements AttributeConverter<TipoTelefone, Character> {

    @Override
    public Character convertToDatabaseColumn(TipoTelefone attribute) {
        return Objects.nonNull(attribute) ? attribute.getCodigo() : null;
    }

    @Override
    public TipoTelefone convertToEntityAttribute(Character dbData) {
        return Arrays.stream(TipoTelefone.values())
            .filter(e -> e.getCodigo().equals(dbData))
            .findFirst().orElse(null);
    }
}
