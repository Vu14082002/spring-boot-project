package vn.edu.iuh.fit.myblog.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.myblog.entity.Comment;
import vn.edu.iuh.fit.myblog.entity.Post;
import vn.edu.iuh.fit.myblog.exception.BlogApiException;
import vn.edu.iuh.fit.myblog.exception.ResourceNotFoundException;
import vn.edu.iuh.fit.myblog.payload.CommentDto;
import vn.edu.iuh.fit.myblog.repository.CommentRepository;
import vn.edu.iuh.fit.myblog.repository.PostRepository;
import vn.edu.iuh.fit.myblog.service.CommentService;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(long id, CommentDto commentDto) {
        // map to entity
        Comment comment = mapper(commentDto);
        // logic code
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
        comment.setPost(post);
        Comment commentSaved = commentRepository.save(comment);
        //map to dto
        return mapper(commentSaved);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        List<Comment> comments = commentRepository.findAllByPostId(postId);
        return comments.stream().map(this::mapper).collect(Collectors.toList());
    }


    @Override
    public CommentDto getCommentById(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("COmment", "id", commentId));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "comment do not belong to post");
        }
        return mapper(comment);
    }

    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto commentDto) {
        // find post by id
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        // find comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("COmment", "id", commentId));

        // check commet of post id === post id ??
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "comment do not belongs to post");
        }
        // update data
        comment.setName(commentDto.getName());
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());
        // data update result
        Comment newComment = commentRepository.save(comment);

        return mapper(newComment);
    }


    private CommentDto mapper(Comment comment) {
        return modelMapper.map(comment, CommentDto.class);
    }

    private Comment mapper(CommentDto commentDto) {
        return modelMapper.map(commentDto, Comment.class);
    }
}
