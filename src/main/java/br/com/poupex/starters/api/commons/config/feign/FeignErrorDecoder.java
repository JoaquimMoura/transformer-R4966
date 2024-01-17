/*
 * Copyright (c) 2024, FHE Poupex and/or its affiliates. All rights reserved.
 * FHE POUPEX PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package br.com.poupex.starters.api.commons.config.feign;

import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.cert.crmf.CRMFException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import static java.util.Objects.isNull;

@Slf4j
@Component
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        log.error("Status code " + response.status() + ", methodKey = " + methodKey);
        switch (response.status()) {
            case 400:
                return new RetryableException(400, isNull(response.reason()) ? "Bad Request" : response.reason(),
                                              response.request().httpMethod(), null, response.request());

            case 404:
                return new ResponseStatusException(HttpStatus.valueOf(response.status()), "Registro não encontrado");
            default:
                return new ResponseStatusException(HttpStatus.BAD_REQUEST, response.reason());
        }
    }
}
