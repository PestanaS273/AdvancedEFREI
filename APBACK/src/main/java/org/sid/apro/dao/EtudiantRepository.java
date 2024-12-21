package org.sid.apro.dao;

import org.sid.apro.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@RepositoryRestResource
@CrossOrigin("*")
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    @Query("SELECT e FROM Etudiant e WHERE e.email = :email")
    Etudiant findByEmail(@Param("email") String email);

    Etudiant findById(long id);
}


