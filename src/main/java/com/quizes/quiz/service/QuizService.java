package com.quizes.quiz.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quizes.quiz.dao.QuestionDAO;
import com.quizes.quiz.dao.QuizDAO;
import com.quizes.quiz.model.Question;
import com.quizes.quiz.model.QuestionWrapper;
import com.quizes.quiz.model.Quiz;
import com.quizes.quiz.model.Response;


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
    public ResponseEntity<Map<String, Object>> getQuizById(int id) {
        try{
             Map<String, Object> body = new HashMap<>();
             Optional<Quiz> quiz=quizDAO.findById(id);
             if (quiz.isEmpty()) {
                 return new ResponseEntity<>(Map.of("error", "Quiz not found"), HttpStatus.NOT_FOUND);
                 }
              body.put("title", quiz.get().getTitle());
              body.put("id", quiz.get().getId());
              List<Question> questions=quiz.get().getQuestions();
              List<QuestionWrapper> questionsForUser=new ArrayList<>();
              for (Question q : questions) {
                QuestionWrapper userQuestion=new QuestionWrapper(q.getId(), q.getCategory(), q.getQuestion(), q.getOptionA(),q.getOptionB(), q.getOptionC(), q.getOptionD());
                questionsForUser.add(userQuestion);
            }
            body.put("data",questionsForUser);

         return new ResponseEntity(body,HttpStatus.OK); 
        }
        catch(Exception e){
            return new ResponseEntity(e.toString(),HttpStatus.BAD_REQUEST); 
        }
    }
    public ResponseEntity<Map<String, Object>> submit(int id, List<Response> responses) {
        try{
            Optional<Quiz> quiz=quizDAO.findById(id);
            if (quiz.isEmpty()) {
                 return new ResponseEntity(Map.of("error", "Quiz not found"), HttpStatus.NOT_FOUND);
                 }
                 
            List<Question> questions= quiz.get().getQuestions();
            Map<Integer,Question> questionMap=new HashMap<>();

            // put quiz questions in the map acc to their id
            for(Question q : questions){
                questionMap.put(q.getId(), q);
            }
            int result=0;
            // match answers of question with same id
            for(Response response: responses){
                Question question=questionMap.get(response.getId());
                  if(response.getResponse().equals(question.getCorrectAnswer())){
                    result++;
                  }
                }
                
            return new ResponseEntity(result,HttpStatus.OK);

        }
        catch(Exception e){
            return new ResponseEntity(e.toString(),HttpStatus.BAD_REQUEST);
        }
    }

}
