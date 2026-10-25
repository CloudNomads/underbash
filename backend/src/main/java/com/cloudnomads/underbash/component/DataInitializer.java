package com.cloudnomads.underbash.component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.cloudnomads.underbash.model.Question;
import com.cloudnomads.underbash.repository.QuestionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final QuestionRepository questionRepository;

    public DataInitializer(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
      // Check if database has any entries
        if (questionRepository.count() == 0) {
            ObjectMapper mapper = new ObjectMapper();
            ClassPathResource resource = new ClassPathResource("questions.json"); // Path referred to resources
            try (InputStream inputStream = resource.getInputStream()) {
                List<Question> questions = mapper.readValue(
                    inputStream, 
                    new TypeReference<List<Question>>() {}
                );
                questionRepository.saveAll(questions);
                System.out.println(questions.size() + " questions loaded");
                
            } catch (Exception e) {
                System.err.println("Error " + e.getMessage());
            }
        } else {
            System.out.println("Database already populated.");
        }
    }
}