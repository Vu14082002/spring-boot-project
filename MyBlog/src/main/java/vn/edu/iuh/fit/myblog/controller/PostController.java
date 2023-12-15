package vn.edu.iuh.fit.myblog.controller;

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

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {
    private PostService postService;
    private PostRepository postRepository;

    // create blog post
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody @Valid  PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }
//   get all post
    @GetMapping
    public  PostResponse getAllPosts(@RequestParam(name = "pageNo",required = false,defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int pageNo,
                                           @RequestParam(name = "pageSize",required = false,defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
                                           @RequestParam(name = "sortBy",required = false,defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy,
                                           @RequestParam(name = "sortDir",required = false,defaultValue = AppConstants.DEFAULT_SORT_DIRECTION) String sortDir
                                      ){
        return postService.getAllPost(pageNo,pageSize,sortBy,sortDir);
    }
    @GetMapping("/{id}")
    public  ResponseEntity<PostDto> getPostById(@PathVariable("id")  Long id){
        return  ResponseEntity.ok(postService.getPostById(id));
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody @Valid PostDto postDto, @PathVariable("id") long id){
        return  new ResponseEntity<>(postService.updatePost(postDto,id),HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable  long id){
        postService.deletePostById(id);
        return ResponseEntity.ok("Delete success post id: "+id);
    }
}
