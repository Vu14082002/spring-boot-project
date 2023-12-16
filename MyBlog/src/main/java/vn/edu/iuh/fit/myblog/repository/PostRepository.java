package vn.edu.iuh.fit.myblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.myblog.entity.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCategoryId(Long categoryId);
}
