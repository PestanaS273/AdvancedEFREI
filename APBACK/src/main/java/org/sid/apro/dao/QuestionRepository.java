package org.sid.apro.dao;

import org.sid.apro.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
