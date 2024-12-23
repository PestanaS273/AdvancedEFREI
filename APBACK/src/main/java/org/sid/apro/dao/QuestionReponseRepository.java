package org.sid.apro.dao;

import org.sid.apro.entities.QuestionReponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@RepositoryRestResource
@CrossOrigin("*")


public interface QuestionReponseRepository extends JpaRepository<QuestionReponse, Long> {
    QuestionReponse findByQuestion(String question);
    @Query("SELECT q FROM QuestionReponse q WHERE q.id = :id")
    QuestionReponse findByQuestionId(Long id);
}
