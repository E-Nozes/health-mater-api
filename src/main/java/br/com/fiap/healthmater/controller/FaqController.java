package br.com.fiap.healthmater.controller;

import br.com.fiap.healthmater.entity.Faq;
import br.com.fiap.healthmater.resource.FaqResource;
import br.com.fiap.healthmater.service.FaqService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Expose the API endpoints for {@link Faq} resources.
 *
 * @author Gabriel Oliveira
 */
@RestController
@Api(value = "FAQ REST API")
@RequestMapping(value = "faq", produces = MediaType.APPLICATION_JSON_VALUE)
public class FaqController implements FaqResource {

    @Autowired
    private FaqService faqService;

    @ApiOperation(value = "Get all FAQ")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Something Unexpected Happened")
    })
    @PreAuthorize("hasAuthority('ROLE_SEARCH_FAQ') and #oauth2.hasScope('read')")
    public ResponseEntity<List<Faq>> findAll() {
        return new ResponseEntity<>(faqService.findAll(), HttpStatus.OK);
    }

}
