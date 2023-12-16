package vn.edu.iuh.fit.myblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.myblog.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}