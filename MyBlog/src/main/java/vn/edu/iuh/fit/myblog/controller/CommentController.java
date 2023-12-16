package vn.edu.iuh.fit.myblog.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.myblog.payload.CommentDto;
import vn.edu.iuh.fit.myblog.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor

public class CommentController {
    private CommentService commentService;
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody @Valid CommentDto commentDto, @PathVariable("postId") long postId) {
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable("postId")long postId){
        return commentService.getCommentsByPostId(postId);
    }
    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("postId")long postId,@PathVariable("commentId")long commentId ){
        return new ResponseEntity<>(commentService.getCommentById(postId, commentId),HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("postId")long postId,
                                                    @PathVariable("commentId")long commentId,
                                                    @RequestBody @Valid CommentDto commentDto){
        return new ResponseEntity<>(commentService.updateComment(postId, commentId,commentDto),HttpStatus.OK);
    }
}
