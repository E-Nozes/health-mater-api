package br.com.fiap.healthmater.controller;

import br.com.fiap.healthmater.entity.Post;
import br.com.fiap.healthmater.resource.PostResource;
import br.com.fiap.healthmater.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Expose the API endpoints for {@link Post} resources.
 *
 * @author Gabriel Oliveira
 */
@RestController
@Api(value = "Posts REST API")
@RequestMapping(value = "posts", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostController implements PostResource {

    @Autowired
    private PostService postService;

    @ApiOperation(value = "Get all Posts")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Something Unexpected Happened")
    })
    @PreAuthorize("hasAuthority('ROLE_SEARCH_POST') and #oauth2.hasScope('read')")
    public ResponseEntity<Page<Post>> findAll(Pageable pageable) {
        return new ResponseEntity<>(postService.findAll(pageable), HttpStatus.OK);
    }

}
