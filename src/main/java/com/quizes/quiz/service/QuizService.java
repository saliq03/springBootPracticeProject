package com.quizes.quiz.service;

import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quizes.quiz.dao.QuestionDAO;
import com.quizes.quiz.dao.QuizDAO;
import com.quizes.quiz.model.Question;
import com.quizes.quiz.model.Quiz;


@Service
public class QuizService {
    @Autowired
    QuizDAO quizDAO;

    @Autowired
    QuestionDAO questionDAO;
    public ResponseEntity<String> createQuiz(String title, int qNs, String category) {
        try{
        Quiz quiz =new Quiz();
        List<Question> questions=questionDAO.findRandomQuestionsByCategory(qNs,category);
        quiz.setQuestions(questions);
        quiz.setTitle(title);
        quizDAO.save(quiz);
        return new ResponseEntity("Quiz created successfully",HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity(e.toString(),HttpStatus.BAD_REQUEST); 
        }
      
    }

}
