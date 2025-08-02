package com.quizes.quiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizes.quiz.dao.QuestionDAO;
import com.quizes.quiz.model.Question;


@Service
public class QuestionService {

    @Autowired
    QuestionDAO questionDAO;

    public List<Question> getAllQuestions() {
      
        return  questionDAO.findAll();
    }

    public List<Question> getQuestionsByCategory(String category) {
       return questionDAO.findByCategory(category);
    }

    public String addQuestion(Question question) {
        questionDAO.save(question);
        return "Question added sucessfully";
    }

}
