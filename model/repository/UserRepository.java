package model.repository;

import java.util.Collection;
import java.util.Optional;
import java.util.List;
import model.domain.User;
import model.domain.Student;

public interface UserRepository {
    
    void save(User user);
    Optional<User> findByEmail(String email);
    Collection<User> findAll();
    List<Student> findAllStudents();
}