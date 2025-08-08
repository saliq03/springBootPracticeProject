package com.quizes.quiz.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quizes.quiz.model.Question;


@Repository
public interface QuestionDAO extends JpaRepository<Question,Integer>{

    List<Question> findByCategory(String category);

    @Query(value = "SELECT q FROM Question q WHERE category =:category ORDER BY RAND() LIMIT :qNs")
    List<Question> findRandomQuestionsByCategory(int qNs,@Param("category") String category);

}
