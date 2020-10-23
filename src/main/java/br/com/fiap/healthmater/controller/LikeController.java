package br.com.fiap.healthmater.controller;

import br.com.fiap.healthmater.entity.Like;
import br.com.fiap.healthmater.resource.LikeResource;
import br.com.fiap.healthmater.service.LikeService;
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
 * Expose the API endpoints for {@link Like} resources.
 *
 * @author Gabriel Oliveira
 */
@RestController
@Api(value = "Likes REST API")
@RequestMapping(value = "likes", produces = MediaType.APPLICATION_JSON_VALUE)
public class LikeController implements LikeResource {

    @Autowired
    private LikeService likeService;

    @ApiOperation(value = "Associate a like to a Post")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid content provided"),
            @ApiResponse(code = 500, message = "Something Unexpected Happened")
    })
    @PreAuthorize("hasAuthority('ROLE_REGISTER_LIKE') and #oauth2.hasScope('write')")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Like like) {
        likeService.create(like);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
