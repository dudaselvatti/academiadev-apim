package repository.impl;

import model.Course;
import model.Enrollment;
import model.Student;
import repository.DataStore;
import repository.EnrollmentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EnrollmentRepositoryImpl implements EnrollmentRepository {

    @Override
    public void save(Enrollment enrollment) {
        // Remove uma possível matrícula antiga para garantir a atualização
        DataStore.ENROLLMENTS.removeIf(e -> e.getStudent().equals(enrollment.getStudent()) && e.getCourse().equals(enrollment.getCourse()));
        DataStore.ENROLLMENTS.add(enrollment);
    }

    @Override
    public void delete(Enrollment enrollment) {
        DataStore.ENROLLMENTS.remove(enrollment);
    }

    @Override
    public Optional<Enrollment> findByStudentAndCourse(Student student, String courseTitle) {
        return DataStore.ENROLLMENTS.stream()
                .filter(e -> e.getStudent().equals(student) && e.getCourse().getTitle().equals(courseTitle))
                .findFirst();
    }

    @Override
    public List<Enrollment> findByStudent(Student student) {
        return DataStore.ENROLLMENTS.stream()
                .filter(e -> e.getStudent().equals(student))
                .collect(Collectors.toList());
    }

    @Override
    public List<Enrollment> findByCourse(Course course) {
        return DataStore.ENROLLMENTS.stream()
                .filter(e -> e.getCourse().equals(course))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Enrollment> findAll() {
        return new ArrayList<>(DataStore.ENROLLMENTS);
    }

    @Override
    public boolean existsByStudentAndCourse(Student student, String courseTitle) {
        return findByStudentAndCourse(student, courseTitle).isPresent();
    }
}