package com.telusko.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.entities.Question;
import com.telusko.entities.QuestionWrapper;
import com.telusko.entities.Response;
import com.telusko.service.QuestionService;
@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    Environment environment;
    
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    // Update a question by ID using PUT
    @PutMapping("update/{id}")
    public ResponseEntity<String> updateQuestion(@PathVariable Integer id, @RequestBody Question question) {
        return questionService.updateQuestion(id, question);
    }

    // Delete a question by name using DELETE
    @DeleteMapping("delete/name/{name}")
    public ResponseEntity<String> deleteQuestionByName(@PathVariable String name) {
        return questionService.deleteQuestionByName(name);
    }

    // Delete a question by ID using DELETE
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuestionById(@PathVariable Integer id) {
        return questionService.deleteQuestionById(id);
    }
    
    //generate 
    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(
    	    @RequestParam String category,
            @RequestParam int numQ) {
    return questionService.getQuestionForQuiz(category, numQ);
    }
    
    //getQuestions
    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
    	System.out.println(environment.getProperty("local.server.port"));
    	return questionService.getQuestionsFromId(questionIds);
    }
    
    
    //getscore
    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
    	return questionService.getScore(responses);
    }
    
}
