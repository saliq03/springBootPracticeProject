package com.quizes.quiz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quizes.quiz.dao.QuestionDAO;
import com.quizes.quiz.model.Question;


@Service
public class QuestionService {

    @Autowired
    QuestionDAO questionDAO;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
            return new ResponseEntity( questionDAO.findAll(),HttpStatus.OK);
        }
        catch(Exception e){
           return new ResponseEntity(new ArrayList<>(),HttpStatus.BAD_REQUEST); 
        }
      
        
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
    
        try{
            return new ResponseEntity( questionDAO.findByCategory(category),HttpStatus.OK);
        }
        catch(Exception e){
           return new ResponseEntity(new ArrayList<>(),HttpStatus.BAD_REQUEST); 
        }
    }

    public ResponseEntity<String> addQuestion(Question question) {
         try{
             questionDAO.save(question);
            return new ResponseEntity( "Question added sucessfully",HttpStatus.CREATED);
        }
        catch(Exception e){
           return new ResponseEntity(new ArrayList<>(),HttpStatus.BAD_REQUEST); 
        }
       
       
    }

    public ResponseEntity<String> updateQuestion(Question question,int id) {

        try{
        Question q=questionDAO.findById(id).orElse(null);
        if(q==null){
            return new ResponseEntity("No question found",HttpStatus.NOT_FOUND);
        }
        if(question.getQuestion()!=null){
            q.setQuestion(question.getQuestion());
        }
        if(question.getCategory()!=null){
            q.setCategory(question.getCategory());
        }
        if(question.getCorrectAnswer()!=null){
            q.setCorrectAnswer(question.getCorrectAnswer());
        }
        if(question.getOptionA()!=null){
            q.setOptionA(question.getOptionA());
        }
         if(question.getOptionB()!=null){
            q.setOptionB(question.getOptionB());
        }
        if(question.getOptionC()!=null){
            q.setOptionC(question.getOptionC());
        }
        
         if(question.getOptionD()!=null){
            q.setOptionD(question.getOptionD());
        }

         questionDAO.save(q);
         return new ResponseEntity<>("Question updated successfully",HttpStatus.OK);

        }catch(Exception e){
            return new ResponseEntity(e.toString(),HttpStatus.BAD_REQUEST);
        }
       
    }

}
