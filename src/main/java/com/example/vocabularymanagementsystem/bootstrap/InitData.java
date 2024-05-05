package com.example.vocabularymanagementsystem.bootstrap;

import com.example.vocabularymanagementsystem.entity.Learner;
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
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Component
@Builder
public class InitData {

    private final WordRepository wordRepository;

    @Autowired
    public InitData(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @PostConstruct
    public void init() {
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

                words.add(word);
            }

            wordRepository.saveAll(words);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
