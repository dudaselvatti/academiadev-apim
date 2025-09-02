package config;

import controller.CliController;
import repository.*;
import repository.impl.*;
import service.*;
import view.MenuView;

public class AppConfig {

    public static CliController build() {

        UserRepository userRepository = new UserRepositoryImpl();
        CourseRepository courseRepository = new CourseRepositoryImpl();
        EnrollmentRepository enrollmentRepository = new EnrollmentRepositoryImpl();
        SupportTicketRepository supportTicketRepository = new SupportTicketRepositoryImpl();

        EnrollmentService enrollmentService = new EnrollmentService(enrollmentRepository, courseRepository, userRepository);
        UserService userService = new UserService(userRepository, enrollmentService);
        CourseService courseService = new CourseService(courseRepository);
        ReportService reportService = new ReportService(courseRepository, enrollmentRepository, userRepository);
        SupportTicketService supportService = new SupportTicketService(supportTicketRepository);

        MenuView menuView = new MenuView();

        return new CliController(
        userService, 
        courseService,
        enrollmentService,
        reportService,
        supportService,
        menuView);
    }
}