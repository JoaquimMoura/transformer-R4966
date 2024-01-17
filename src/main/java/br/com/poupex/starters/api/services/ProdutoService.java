/*
 * Copyright (c) 2024, FHE Poupex and/or its affiliates. All rights reserved.
 * FHE POUPEX PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package br.com.poupex.starters.api.services;

import br.com.poupex.starters.api.controllers.dtos.ProdutoDto;
import br.com.poupex.starters.api.repositories.ProdutoApiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoApiRepository repository;

    public Optional<ProdutoDto> findById(Integer id) {
        return repository.findById(id);
    }
}
