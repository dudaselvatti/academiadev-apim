package model.repository.impl;

import model.domain.Course;
import model.domain.CourseStatus;
import model.domain.DifficultyLevel;
import model.repository.CourseRepository;
import model.repository.DataStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryCourseRepositoryImpl implements CourseRepository {
    
    @Override
    public void save(Course course) {
        DataStore.COURSES.put(course.getTitle(), course);
    }

    @Override
    public Optional<Course> findByTitle(String title) {
        return Optional.ofNullable(DataStore.COURSES.get(title));
    }

    @Override
    public List<Course> findAll() {
        return new ArrayList<>(DataStore.COURSES.values());
    }

    @Override
    public List<Course> findByDifficultyLevel(DifficultyLevel level) {
        return DataStore.COURSES.values().stream()
                .filter(course -> course.getDifficultyLevel() == level)
                .collect(Collectors.toList());
    }

    @Override
    public List<Course> findAllActive() {
        return DataStore.COURSES.values().stream()
                .filter(course -> course.getStatus() == CourseStatus.ACTIVE)
                .collect(Collectors.toList());
    }

}
