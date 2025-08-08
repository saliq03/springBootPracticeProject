package com.quizes.quiz.dao;
import org.springframework.stereotype.Repository;
import com.quizes.quiz.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface QuizDAO extends JpaRepository<Quiz,Integer>{

}