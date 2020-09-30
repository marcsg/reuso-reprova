package br.ufmg.reuso.marcelosg.reprova.repository;

import br.ufmg.reuso.marcelosg.reprova.model.Exam;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExamRepository extends MongoRepository<Exam, String> {
}
