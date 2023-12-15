package vn.edu.iuh.fit.myblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.myblog.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByPostId(long postId);
}
