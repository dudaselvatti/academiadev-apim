package model.repository.impl;

import model.domain.Course;
import model.domain.Enrollment;
import model.domain.Student;
import model.repository.DataStore;
import model.repository.EnrollmentRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryEnrollmentRepositoryImpl implements EnrollmentRepository {

    @Override
    public void save(Enrollment enrollment) {
        DataStore.ENROLLMENTS.add(enrollment);
    }

    @Override
    public boolean deleteByStudentAndCourse(Student student, Course course) {
        return DataStore.ENROLLMENTS.removeIf(
            e -> e.getStudent().equals(student) && e.getCourse().equals(course)
        );
    }

    @Override
    public Optional<Enrollment> findByStudentAndCourse(Student student, Course course) {
        return DataStore.ENROLLMENTS.stream()
                .filter(e -> e.getStudent().equals(student) && e.getCourse().equals(course))
                .findFirst();
    }

    @Override
    public List<Enrollment> findAllByStudent(Student student) {
        return DataStore.ENROLLMENTS.stream()
                .filter(e -> e.getStudent().equals(student))
                .collect(Collectors.toList());
    }

    @Override
    public List<Enrollment> findAll() {
        return List.copyOf(DataStore.ENROLLMENTS);
    }
}