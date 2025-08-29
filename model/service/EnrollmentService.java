package model.service;

import model.domain.*;
import dto.EnrollmentSummaryDTO;
import dto.StudentProfileDTO;
import exceptions.EnrollmentException;
import exceptions.ResourceNotFoundException;
import model.repository.CourseRepository;
import model.repository.EnrollmentRepository;
import model.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository, UserRepository userRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public void enrollStudent(String studentEmail, String courseTitle) {

        Student student = findStudentByEmail(studentEmail);
        Course course = findCourseByTitle(courseTitle);

        if (course.getStatus() == CourseStatus.INACTIVE) {
            throw new EnrollmentException("Não é possível se matricular em um curso inativo.");
        }

        if (enrollmentRepository.findByStudentAndCourse(student, course).isPresent()) {
            throw new EnrollmentException("Aluno já está matriculado neste curso.");
        }

        List<Enrollment> studentEnrollments = enrollmentRepository.findAllByStudent(student);

        if (!student.getSubscriptionPlan().canEnroll(student, studentEnrollments)) {
             throw new EnrollmentException("Limite do plano de assinatura do aluno foi atingido.");
        }
        
        enrollmentRepository.save(new Enrollment(student, course));
    }

    public void cancelEnrollment(String studentEmail, String courseTitle) {
        Student student = findStudentByEmail(studentEmail);
        Course course = findCourseByTitle(courseTitle);

        boolean wasDeleted = enrollmentRepository.deleteByStudentAndCourse(student, course);
        if (!wasDeleted) {
            throw new EnrollmentException("Matrícula não encontrada para ser cancelada.");
        }
    }

    public void updateProgress(String studentEmail, String courseTitle, int progress) {
        Student student = findStudentByEmail(studentEmail);
        Course course = findCourseByTitle(courseTitle);

        Enrollment enrollment = enrollmentRepository.findByStudentAndCourse(student, course)
            .orElseThrow(() -> new EnrollmentException("Matrícula não encontrada para atualizar o progresso."));
        
        enrollment.updateProgress(progress);
    }

    public StudentProfileDTO getStudentProfile(String studentEmail) {
        Student student = findStudentByEmail(studentEmail);
        List<Enrollment> enrollments = enrollmentRepository.findAllByStudent(student);
        return toStudentProfileDto(student, enrollments);
    }


    private Student findStudentByEmail(String email) {
        return userRepository.findByEmail(email)
                .filter(u -> u instanceof Student)
                .map(u -> (Student) u)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com o e-mail: " + email));
    }

    private Course findCourseByTitle(String title) {
        return courseRepository.findByTitle(title)
                .orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado: " + title));
    }
    
    private StudentProfileDTO toStudentProfileDto(Student student, List<Enrollment> enrollments) {
        
        StudentProfileDTO profileDto = new StudentProfileDTO();
        
        profileDto.setName(student.getName());
        profileDto.setEmail(student.getEmail());
        profileDto.setSubscriptionPlanName(student.getSubscriptionPlan().getPlanName());

        List<EnrollmentSummaryDTO> enrollmentSummaries = enrollments.stream()
                .map(this::toEnrollmentSummaryDto)
                .collect(Collectors.toList());

        profileDto.setEnrollments(enrollmentSummaries);
        return profileDto;
    }

    private EnrollmentSummaryDTO toEnrollmentSummaryDto(Enrollment enrollment) {
        EnrollmentSummaryDTO summaryDto = new EnrollmentSummaryDTO();
        summaryDto.setCourseTitle(enrollment.getCourse().getTitle());
        summaryDto.setProgress(enrollment.getProgress());
        return summaryDto;
    }
}