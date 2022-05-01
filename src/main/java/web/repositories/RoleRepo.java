package web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.model.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

}
