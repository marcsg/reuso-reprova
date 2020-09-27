package br.ufmg.reuso.marcelosg.reprova.repository;

import br.ufmg.reuso.marcelosg.reprova.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {
}
