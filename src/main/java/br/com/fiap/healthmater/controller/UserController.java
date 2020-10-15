package br.com.fiap.healthmater.controller;

import br.com.fiap.healthmater.entity.User;
import br.com.fiap.healthmater.resource.UserResource;
import br.com.fiap.healthmater.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Expose the API endpoints for {@link User} resources.
 *
 * @author Gabriel Oliveira
 */
@RestController
@Api(value = "Users REST API")
@RequestMapping(value = "users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController implements UserResource {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Gets an User based on it's ID")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "User not found for the given ID"),
            @ApiResponse(code = 500, message = "Something Unexpected Happened")
    })
    @PreAuthorize("hasAuthority('ROLE_SEARCH_USER') and #oauth2.hasScope('read')")
    public User findById(@PathVariable("id") Integer id) {
        return userService.findById(id);
    }

}
