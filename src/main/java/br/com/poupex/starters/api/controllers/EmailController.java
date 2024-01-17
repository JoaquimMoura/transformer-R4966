package br.com.poupex.starters.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.poupex.starters.api.commons.support.GenericControllerImpl;
import br.com.poupex.starters.api.controllers.dtos.EmailDto;
import br.com.poupex.starters.api.domain.entity.Email;
import br.com.poupex.starters.api.services.EmailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/emails")
@RequiredArgsConstructor
@Tag(name = "EmailController")
public class EmailController extends GenericControllerImpl<EmailDto, Email, Long> {

    private final EmailService service;


    @Override
    protected EmailService getService() {
        return service;
    }
}
