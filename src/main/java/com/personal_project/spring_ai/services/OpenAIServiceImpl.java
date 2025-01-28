package com.personal_project.spring_ai.services;

import com.personal_project.spring_ai.models.Answer;
import com.personal_project.spring_ai.models.Question;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OpenAIServiceImpl implements OpenAIService {

    private final ChatModel chatModel;

    public OpenAIServiceImpl(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Value("classpath:templates/get-capital-prompt.st")
    private Resource getCapitalPrompt;

    @Value("classpath:templates/get-capital-prompt-with-info.st")
    private Resource getCapitalPromptWithInfo;

    //common use, just with a question and a response
    @Override
    public Answer getAswer(Question question) {
        PromptTemplate promptTemplate = new PromptTemplate(question.getQuestion());
        Prompt prompt = promptTemplate.create();

        ChatResponse response = chatModel.call(prompt);

        return new Answer(response.getResult().getOutput().getContent());
    }


    //Question with a template
    @Override
    public Answer getAnswerWithTemperatures(Question question) {

      var openAi =   OpenAiChatOptions.builder().withTemperature(0.1).build(); //default is 0.7
        //less value, more focusing is the response
        //more value, more creative response
        // https://community.openai.com/t/cheat-sheet-mastering-temperature-and-top-p-in-chatgpt-api/172683

        PromptTemplate promptTemplate = new PromptTemplate(question.getQuestion());

        Prompt prompt = new Prompt(promptTemplate.createMessage(), openAi);

        ChatResponse response = chatModel.call(prompt);

        return new Answer(response.getResult().getOutput().getContent());

    }

    //Question with a template
    @Override
    public Answer getCapital(Question question) {
        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
        Prompt prompt = promptTemplate.create(Map.of("estadoOuPais", question.getQuestion()));

        ChatResponse response = chatModel.call(prompt);

        return new Answer(response.getResult().getOutput().getContent());
    }

    //Answer with a template
    @Override
    public Answer getCapitalWithInfo(Question question) {
        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPromptWithInfo);
        Prompt prompt = promptTemplate.create(Map.of("estadoOuPais", question.getQuestion()));

        ChatResponse response = chatModel.call(prompt);

        return new Answer(response.getResult().getOutput().getContent());
    }
}
