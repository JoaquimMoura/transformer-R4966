package br.com.poupex.starters.api.commons.support;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.ParameterizedType;
import java.util.Locale;
import java.util.function.Supplier;

public abstract class GenericControllerImpl<M, E, K> implements IGenericController<M, E, K> {

    @Autowired
    protected ModelMapper mapper;

    @Autowired
    protected MessageSource messageSource;

    protected abstract GenericService<E, K> getService();

    private Class<M> dtoType;
    private Class<E> entityType;

    public GenericControllerImpl() {
        this.dtoType = (Class<M>) getParameterizedTypeByPosition(0);
        this.entityType = (Class<E>) getParameterizedTypeByPosition(1);
    }

    @GetMapping
    public ResponseEntity<PageDTO<M>> findAll(Pageable pageable) {
        return ResponseEntity.ok(mapper.map(getService().findAll(pageable), PageDTO.class));
    }

    @GetMapping(value = "/search/findAllByFilter")
    public ResponseEntity<PageDTO<M>> findAllByFilter(M input, Pageable pageable) {
        return ResponseEntity.ok(mapper.map(getService().findByFilter(dtoToEntity(input), pageable), PageDTO.class));
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<M> findById(@PathVariable K id) {
        return getService().findById(id).map(o -> ResponseEntity.ok(entityToDTO(o)))
                           .orElseThrow(resourceNotFoundResponseStatusExceptionSupplier());
    }

    @PostMapping
    public ResponseEntity<M> save(@Validated @RequestBody M input) {
        E entity = getService().save(dtoToEntity(input));
        return new ResponseEntity<>(entityToDTO(entity), HttpStatus.CREATED);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<M> update(@PathVariable K id, M input) {
        return getService().findById(id).map(o -> {
            mapper.map(input, o);
            return ResponseEntity.ok(entityToDTO(getService().save(o)));
        }).orElseThrow(resourceNotFoundResponseStatusExceptionSupplier());
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> delete(@PathVariable K id) {
        return getService().findById(id).map(o -> {
            getService().delete(o);
            return ResponseEntity.noContent().build();
        }).orElseThrow(resourceNotFoundResponseStatusExceptionSupplier());
    }

    protected M entityToDTO(E entityObject) {
        return mapper.map(entityObject, dtoType);
    }

    protected E dtoToEntity(M dtoObject) {
        return mapper.map(dtoObject, entityType);
    }

    private Class<?> getParameterizedTypeByPosition(int position) {
        return ((Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[position]);
    }

    private Supplier<ResponseStatusException> resourceNotFoundResponseStatusExceptionSupplier() {
        return () -> new ResponseStatusException(HttpStatus.NOT_FOUND, getMessage("error.404"));
    }

    protected String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, Locale.getDefault());
    }

}
