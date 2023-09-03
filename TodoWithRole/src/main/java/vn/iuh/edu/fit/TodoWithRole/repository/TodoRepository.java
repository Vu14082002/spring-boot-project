package vn.iuh.edu.fit.TodoWithRole.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.iuh.edu.fit.TodoWithRole.entities.Todo;

public interface TodoRepository extends JpaRepository<Todo,Long> {
}
