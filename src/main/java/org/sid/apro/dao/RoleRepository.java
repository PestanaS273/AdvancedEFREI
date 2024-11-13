package org.sid.apro.dao;

import org.springframework.context.annotation.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
