package br.com.poupex.starters.api.commons.support;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Erro")
public class ResponseError {

    private int code;
    private String description;
    private Map<String, String> fields = new HashMap<>();
}
