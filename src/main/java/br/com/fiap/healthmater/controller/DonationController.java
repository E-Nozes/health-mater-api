package br.com.fiap.healthmater.controller;

import br.com.fiap.healthmater.entity.Donation;
import br.com.fiap.healthmater.resource.DonationResource;
import br.com.fiap.healthmater.service.DonationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Expose the API endpoints for {@link Donation} resources.
 *
 * @author Gabriel Oliveira
 */
@RestController
@Api(value = "Donations REST API")
@RequestMapping(value = "donations", produces = MediaType.APPLICATION_JSON_VALUE)
public class DonationController implements DonationResource {

    @Autowired
    private DonationService donationService;

    @ApiOperation(value = "Make a donation")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid payload information"),
            @ApiResponse(code = 500, message = "Something Unexpected Happened")
    })
    @PreAuthorize("hasAuthority('ROLE_REGISTER_DONATION') and #oauth2.hasScope('write')")
    public ResponseEntity<Donation> donate(@RequestBody @Valid Donation donation) {
        return new ResponseEntity<>(donationService.create(donation), HttpStatus.OK);
    }

}
