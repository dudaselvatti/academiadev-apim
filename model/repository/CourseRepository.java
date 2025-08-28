package model.repository;


import java.util.List;
import java.util.Optional;

import model.domain.Course;
import model.domain.DifficultyLevel;

public interface CourseRepository {
    
    void save(Course course);

    Optional<Course> findByTitle(String title);

    List<Course> findAll();

    List<Course> findByDifficultyLevel(DifficultyLevel level);

    List<Course> findAllActive();
}
