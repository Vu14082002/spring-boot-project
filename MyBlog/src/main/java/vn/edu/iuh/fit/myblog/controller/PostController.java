package vn.edu.iuh.fit.myblog.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.myblog.payload.PostDto;
import vn.edu.iuh.fit.myblog.payload.PostResponse;
import vn.edu.iuh.fit.myblog.repository.PostRepository;
import vn.edu.iuh.fit.myblog.service.PostService;
import vn.edu.iuh.fit.myblog.utils.AppConstants;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
@Tag(name = "CRUD REST API FOR POST RESOURCE")
public class PostController {
    private PostService postService;
    private PostRepository postRepository;

    // create blog post
    @Operation(
            summary = "CREATE POST REST API",
            description = "Create Post Rest api an save into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP STATUS 201 Create"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody @Valid PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    //   get all post
    @GetMapping
    public PostResponse getAllPosts(@RequestParam(name = "pageNo", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int pageNo,
                                    @RequestParam(name = "pageSize", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
                                    @RequestParam(name = "sortBy", required = false, defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy,
                                    @RequestParam(name = "sortDir", required = false, defaultValue = AppConstants.DEFAULT_SORT_DIRECTION) String sortDir
    ) {
        return postService.getAllPost(pageNo, pageSize, sortBy, sortDir);
    }
    @Operation(
            summary = "GET POST REST API",
            description = "GET Post Rest api from Database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 Create"
    )
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @Operation(
            summary = "PUT POST REST API",
            description = "Put Post Rest api an save into database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 Create"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody @Valid PostDto postDto, @PathVariable("id") long id) {
        return new ResponseEntity<>(postService.updatePost(postDto, id), HttpStatus.OK);
    }
    @Operation(
            summary = "DELETE POST REST API",
            description = "Delete Post Rest api "
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 Create"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable long id) {
        postService.deletePostById(id);
        return ResponseEntity.ok("Delete success post id: " + id);
    }
    @Operation(
            summary = "GET POST BY CATEGORY ID REST API",
            description = "GET Post by category id Rest api"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 Create"
    )
    @GetMapping("/category/{id}")
    public ResponseEntity<List<PostDto>> findPostByCategory(@PathVariable("id") Long categoryId) {
        List<PostDto> postByCategory = postService.findPostByCategory(categoryId);
        return ResponseEntity.ok(postByCategory);
    }
}
