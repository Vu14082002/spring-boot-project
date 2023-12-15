package vn.edu.iuh.fit.myblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.myblog.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);

}
