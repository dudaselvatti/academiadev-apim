package model.service;

import model.domain.Student;
import model.domain.SubscriptionPlan;
import model.domain.User;
import model.repository.UserRepository;

import java.util.Optional;

import exceptions.ResourceNotFoundException;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public Optional<User> login(String email) {
        return userRepository.findByEmail(email);
    }

    public void changeSubscriptionPlan(String studentEmail, SubscriptionPlan newPlan) {
        User user = userRepository.findByEmail(studentEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado..."));

        if (!(user instanceof Student)) {
            throw new IllegalArgumentException("O usuário encontrado não é um aluno.");
        }

        Student student = (Student) user;
        student.setSubscriptionPlan(newPlan);
        userRepository.save(student);
    }
}
