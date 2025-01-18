package org.sid.apro.dao;

import org.sid.apro.entities.Forme;
import org.sid.apro.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
@CrossOrigin("*")
public interface FormRepository extends JpaRepository<Forme, Long> {
    Forme findById(long id);
    List<Forme> findByQuestion(Question question);
    @Query("SELECT f FROM Forme f WHERE f.id= :id AND f.question.id = :questionId")
    Forme findByIdAndQuestion(long id, long questionId);
    @Query("SELECT f FROM Forme f WHERE f.etudiant.id = :idEtudiant")
    List<Forme> findByEtudiantId(@Param("idEtudiant") long idEtudiant);




}
