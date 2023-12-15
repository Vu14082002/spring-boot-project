package vn.edu.iuh.fit.myblog.service;

import vn.edu.iuh.fit.myblog.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long id, CommentDto commentDto);
    List<CommentDto> getCommentsByPostId(long postId);


    CommentDto getCommentById(long postId, long commentId);

    CommentDto updateComment(long postId, long commentId, CommentDto commentDto);
}
