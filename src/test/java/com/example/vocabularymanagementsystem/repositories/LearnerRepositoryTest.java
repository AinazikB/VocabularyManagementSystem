/*package com.example.vocabularymanagementsystem.repositories;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.vocabularymanagementsystem.entity.Learner;
import com.example.vocabularymanagementsystem.repositories.LearnerRepository;
import java.util.Optional;

@SpringBootTest
public class LearnerRepositoryTest {

    @Autowired
    private LearnerRepository learnerRepository;

    @Test
    public void testFindLearnerByEmail() {
        String emailToSearch = "admin1@example.com";
        Optional<Learner> learnerOptional = learnerRepository.findLearnerByUsername(em);

        if (learnerOptional.isPresent()) {
            Learner learner = learnerOptional.get();
            System.out.println("Found learner with email: " + emailToSearch);
            System.out.println("Username: " + learner.getUsername());
            // Добавьте другие проверки по вашему усмотрению
        } else {
            System.out.println("Learner not found with email: " + emailToSearch);
        }
    }
}*/
