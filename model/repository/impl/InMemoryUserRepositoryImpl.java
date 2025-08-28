package model.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import model.domain.Student;
import model.domain.User;
import model.repository.DataStore;
import model.repository.UserRepository;

public class InMemoryUserRepositoryImpl implements UserRepository {
    
    @Override
    public void save(User user) {
        DataStore.USERS.put(user.getEmail(), user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(DataStore.USERS.get(email));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(DataStore.USERS.values());
    }

    @Override
    public List<Student> findAllStudents() {
        return DataStore.USERS.values().stream()
                .filter(user -> user instanceof Student)
                .map(user -> (Student) user)
                .collect(Collectors.toList());
    }

}
