/*
 * Copyright (c) 2024, FHE Poupex and/or its affiliates. All rights reserved.
 * FHE POUPEX PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package br.com.poupex.starters.api.controllers;

import br.com.poupex.starters.api.controllers.dtos.ProdutoDto;
import br.com.poupex.starters.api.services.ProdutoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Locale;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
@Tag(name = "ProdutoController")
public class ProdutoController {

    private final MessageSource messageSource;
    private final ProdutoService service;

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id)
                                           .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                                          messageSource.getMessage(
                                                                                              "error.404",
                                                                                              new Object[] {},
                                                                                              Locale.getDefault()))));
    }

}
