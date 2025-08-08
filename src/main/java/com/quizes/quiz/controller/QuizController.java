package com.quizes.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quizes.quiz.service.QuizService;



@RestController
@RequestMapping("Quiz")
public class QuizController {

    @Autowired
    QuizService QuizService;
   
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String title, @RequestParam int qNs,@RequestParam String category) {
        return QuizService.createQuiz(title,qNs,category);
        
    }
    

}
