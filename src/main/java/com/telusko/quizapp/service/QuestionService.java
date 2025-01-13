package com.telusko.quizapp.service;

import com.telusko.quizapp.dao.QuestionDao;
import com.telusko.quizapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


@Service
public class QuestionService {

    private static final Logger logger = LoggerFactory.getLogger(QuestionService.class);

    @Autowired
    QuestionDao questionDao;

    //Fetching all the Questions
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e){
            logger.error("Error fetching questions", e);
        }
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }


    //Fetching the Questions by category
    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        } catch (Exception e){
            logger.error("Error fetching questions", e);
        }
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
        //here findByCategory is not there, so created a method in Dao layer(same like repo).
    }



    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionDao.save(question);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);}
        catch (Exception e){logger.error("Error Adding question", e);}
            return new ResponseEntity<>("Error While adding the question ", HttpStatus.INTERNAL_SERVER_ERROR);
    }





    public String updateProduct(Question question) {
        questionDao.save(question);
        return "Success";
    }

    public String deleteProduct(Question question) {
        questionDao.delete(question);
        return "Success";
    }

    public void deleteProductByCategory(String category) {
         questionDao.deleteProductByCategory(category);
    //here deleteByCategory is not there, so created a method in Dao layer(same like repo).
    }
}

