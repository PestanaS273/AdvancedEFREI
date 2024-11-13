package org.sid.apro.dao;

import org.sid.apro.entities.Reponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface ReponseRepository extends JpaRepository<Reponse, Long> {
}
