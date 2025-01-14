package com.personal_project.spring_ai.services;

import com.personal_project.spring_ai.models.Answer;
import com.personal_project.spring_ai.models.Question;

public interface OpenAIService {

    Answer getAswer(Question quention);

    Answer getCapital(Question question);
}
