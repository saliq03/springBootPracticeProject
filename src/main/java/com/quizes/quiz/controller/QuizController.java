package com.quizes.quiz.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quizes.quiz.model.Response;
import com.quizes.quiz.service.QuizService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;





@RestController
@RequestMapping("Quiz")
public class QuizController {

    @Autowired
    QuizService QuizService;
   
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String title, @RequestParam int qNs,@RequestParam String category) {
        return QuizService.createQuiz(title,qNs,category);     
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Map<String,Object>> getQuizById(@PathVariable int id) {
        return QuizService.getQuizById(id);
    }
    @PostMapping("submit/{id}")
     public ResponseEntity<Map<String,Object>> submit(@PathVariable int id,@RequestBody List<Response> responses) {
        return QuizService.submit(id,responses);
    }
    

    
    

}
