package vn.iuh.edu.fit.TodoWithRole.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.iuh.edu.fit.TodoWithRole.entities.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {

}
