package com.personal_project.spring_ai.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OpenAIServiceImplTest {

    @Autowired
    OpenAIService openAIService;

    @Test
    void getAswer() {
        String answer = openAIService.getAswer("Tell me what you are looking for");
        System.out.println("Got the answer");
        System.out.println(answer);
    }
}