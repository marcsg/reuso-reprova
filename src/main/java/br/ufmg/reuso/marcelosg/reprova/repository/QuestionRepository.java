package br.ufmg.reuso.marcelosg.reprova.repository;

import br.ufmg.reuso.marcelosg.reprova.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
