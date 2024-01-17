package br.com.poupex.starters.api.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/teste")
@Tag(name = "TesteController")
public class TesteController {

    @GetMapping("/header")
    public void testarHeader(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String header = headerNames.nextElement();
            log.info(header + ": " + request.getHeader(header));
        }
    }
}
