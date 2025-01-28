package com.personal_project.spring_ai.controller;

import com.personal_project.spring_ai.models.Answer;
import com.personal_project.spring_ai.models.Question;
import com.personal_project.spring_ai.services.OpenAIService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ask")
public class QuestionController {

    OpenAIService openAIService;

    private QuestionController(OpenAIService openAIService){
        this.openAIService = openAIService;
    }

    @PostMapping("/capital-with-info")
    public Answer postCapitalWithInfo(@RequestBody Question question){

        return openAIService.getCapitalWithInfo(question);
    }

    @PostMapping("/capital")
    public Answer postCapital(@RequestBody Question question){

        return openAIService.getCapital(question);
    }

    @PostMapping
    public Answer postQuestion(@RequestBody Question question){

        return openAIService.getAswer(question);
    }

    @PostMapping("/temperature")
    public Answer postQuestionWithTemperature(@RequestBody Question question){

        return openAIService.getAnswerWithTemperatures(question);
    }
}
