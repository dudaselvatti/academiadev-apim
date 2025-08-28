package model.repository;

import java.util.List;
import java.util.Optional;

import model.domain.Course;
import model.domain.Student;
import model.domain.Enrollment;


public interface EnrollmentRepository {
    
    void save(Enrollment enrollment);

    boolean deleteByStudentAndCourse(Student student, Course course);

    Optional<Enrollment> findByStudentAndCourse(Student student, Course course);

    List<Enrollment> findAllByStudent(Student student);

    List<Enrollment> findAll();
}
