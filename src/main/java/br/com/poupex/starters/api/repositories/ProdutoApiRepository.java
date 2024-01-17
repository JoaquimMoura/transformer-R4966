package br.com.poupex.starters.api.repositories;

import br.com.poupex.starters.api.commons.config.feign.OAuth2FeignConfiguration;
import br.com.poupex.starters.api.controllers.dtos.ProdutoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@FeignClient(name = "produtoApiRepository", url = "${starter-api.api.urls.baseCliente}/produtos", decode404 = true,
             configuration = OAuth2FeignConfiguration.class)
public interface ProdutoApiRepository {

    @RequestMapping("/{id}")
    Optional<ProdutoDto> findById(@PathVariable Integer id);
}
