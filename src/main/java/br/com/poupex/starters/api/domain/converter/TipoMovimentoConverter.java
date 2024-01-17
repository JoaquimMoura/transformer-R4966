package br.com.poupex.starters.api.domain.converter;

import br.com.poupex.starters.api.domain.enumeration.TipoMovimento;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Objects;

public class TipoMovimentoConverter implements AttributeConverter<TipoMovimento, Character> {

    @Override
    public Character convertToDatabaseColumn(TipoMovimento attribute) {
        return Objects.nonNull(attribute) ? attribute.getCodigo() : null;
    }

    @Override
    public TipoMovimento convertToEntityAttribute(Character dbData) {
        return Arrays.stream(TipoMovimento.values())
            .filter(e -> e.getCodigo().equals(dbData))
            .findFirst().orElse(null);
    }
}
