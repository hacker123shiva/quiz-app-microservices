package com.telusko.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

 
import com.telusko.dao.QuizRepository;
import com.telusko.entities.QuestionWrapper;
import com.telusko.entities.Quiz;
import com.telusko.entities.Response;
import com.telusko.exception.ResourceNotFoundException;
import com.telusko.feign.QuizInterface;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuizInterface quizInterface;

    // Create a new quiz using the specified category, number of questions, and title
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
     try {
        
           List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numQ).getBody() ; //call generate - RestTemplate http://localhost:8080/question/generate
////            
//            // Check if enough questions are retrieved
            if (questions.isEmpty()) {
                return new ResponseEntity<>("Not enough questions available for the given category", HttpStatus.BAD_REQUEST);
            }

            // Create a new Quiz object
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestionsIds(questions);
//
//            // Save the quiz
            quizRepository.save(quiz);

            return new ResponseEntity<>("Quiz created successfully with title: " + title, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error occurred while creating the quiz", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    	
 
    }

    // Fetch all quizzes
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        try {
            return new ResponseEntity<>(quizRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // Method to get the questions by quiz ID
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        // Fetch the quiz from the database
        Optional<Quiz> quiz = quizRepository.findById(id);

      //   Check if the quiz is present
        if (quiz.isPresent()) {
      // Get the questions from the quiz object
            List<Integer> questionsIds = quiz.get().getQuestionsIds();
            ResponseEntity<List<QuestionWrapper>> questions=quizInterface.getQuestionsFromId(questionsIds);
           return questions;
        }else {
        	List<QuestionWrapper> ls=new ArrayList<>();
        	 return new ResponseEntity<>(ls,HttpStatus.NOT_FOUND);
       
        }
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
     return quizInterface.getScore(responses);
    	 
    }

}



