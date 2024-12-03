package org.sid.apro.dao;

import org.sid.apro.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@RepositoryRestResource
@CrossOrigin("*")
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByEmail(String email);
}
