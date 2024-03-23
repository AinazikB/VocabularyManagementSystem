package com.example.vocabularymanagementsystem.bootstrap;

import com.example.vocabularymanagementsystem.entity.Learner;
import com.example.vocabularymanagementsystem.entity.UserRole;
import com.example.vocabularymanagementsystem.entity.Word;
import com.example.vocabularymanagementsystem.enums.WordStatus;
import com.example.vocabularymanagementsystem.repositories.LearnerRepository;
import com.example.vocabularymanagementsystem.repositories.WordRepository;
import lombok.Builder;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@Builder
public class InitData {

    private final WordRepository wordRepository;
    private final LearnerRepository learnerRepository;
   // private final PasswordEncoder passwordEncoder;

    @Autowired
    public InitData(WordRepository wordRepository, LearnerRepository learnerRepository) {
        this.wordRepository = wordRepository;
        this.learnerRepository = learnerRepository;
    }

    @PostConstruct
    public void init() {

      /*  if (learnerRepository.count() == 0) {
            initDefaultUsers();
        }*/
        initWords();
    }

    private void initWords() {
        try {
            Reader reader = new FileReader(new ClassPathResource("words.csv").getFile());
            CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);

            List<Word> words = new ArrayList<>();
            for (CSVRecord record : csvParser) {
                Word word = new Word();
                word.setOriginalWord(record.get("translation"));
                word.setTranslation(record.get("originalWord"));
                word.setDifficultyLevel(record.get("difficultyLevel"));
                Long learnerId = Long.parseLong(record.get("learnerId"));
                word.setStatus(WordStatus.NEW);
                Learner learner = learnerRepository.findById(learnerId)
                        .orElseThrow(() -> new RuntimeException("Learner not found with ID: " + learnerId));
                word.setLearner(learner);

                words.add(word);
            }

            wordRepository.saveAll(words);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 /*   private void initDefaultUsers() {
        Learner learner = Learner.builder()
                .username("user")
                .password(passwordEncoder.encode("password"))
                .email("user@example.com")
                .roles(Set.of(UserRole.user))
                .build();

        Learner staff = Learner.builder()
                .username("staff")
                .password(passwordEncoder.encode("password"))
                .email("staff@example.com")
                .roles(Set.of(UserRole.staff))
                .build();

        Learner admin = Learner.builder()
                .username("admin")
                .password(passwordEncoder.encode("password"))
                .email("admin@example.com")
                .roles(Set.of(UserRole.admin, UserRole.staff))
                .build();

        learnerRepository.saveAll(List.of(user, staff, admin));
    }*/
}
