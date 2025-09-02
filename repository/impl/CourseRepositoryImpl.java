package repository.impl;

import model.Course;
import model.CourseStatus;
import model.DifficultyLevel;
import repository.CourseRepository;
import repository.DataStore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class CourseRepositoryImpl implements CourseRepository {

    @Override
    public void save(Course course) {
        DataStore.COURSES.put(course.getTitle(), course);
    }

    @Override
    public Optional<Course> findByTitle(String title) {
        return Optional.ofNullable(DataStore.COURSES.get(title));
    }

    @Override
    public Collection<Course> findAll() {
        return new ArrayList<>(DataStore.COURSES.values());
    }

    @Override
    public Collection<Course> findByDifficultyLevel(DifficultyLevel level) {
        return DataStore.COURSES.values().stream()
                .filter(c -> c.getDifficultyLevel() == level)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Course> findActiveCourses() {
        return DataStore.COURSES.values().stream()
                .filter(c -> c.getStatus() == CourseStatus.ACTIVE)
                .collect(Collectors.toList());
    }
}