package util;

import model.*;
import repository.DataStore;

public class InitialData {

    private InitialData() {}

    public static void populate() {

        SubscriptionPlan basicPlan = new BasicPlan();
        SubscriptionPlan premiumPlan = new PremiumPlan();

        User admin = new Admin("Administrador Geral", "admin@sistemadev.com");
        Student alice = new Student("Marina Souza", "marina@email.com", basicPlan);
        Student bob = new Student("Lucas Pereira", "lucas@email.com", premiumPlan);
        Student charlie = new Student("Fernanda Lima", "fernanda@email.com", basicPlan);
        Student daniela = new Student("Rafael Costa", "rafael@email.com", basicPlan); // Usuário sem cursos

        DataStore.USERS.put(admin.getEmail(), admin);
        DataStore.USERS.put(alice.getEmail(), alice);
        DataStore.USERS.put(bob.getEmail(), bob);
        DataStore.USERS.put(charlie.getEmail(), charlie);
        DataStore.USERS.put(daniela.getEmail(), daniela);

        Course javaBasics = new Course("Introducao ao Java", "Aprenda os conceitos básicos de programação em Java.", "Profa. Ana Martins", 40, DifficultyLevel.BEGINNER);
        Course springBoot = new Course("Spring Boot Completo", "Crie aplicações REST modernas com Spring Boot e Hibernate.", "Prof. Carlos Silva", 60, DifficultyLevel.ADVANCED);
        Course pythonData = new Course("Python para Analise de Dados", "Trabalhe com Pandas, NumPy e visualização de dados.", "Dra. Júlia Fernandes", 50, DifficultyLevel.INTERMEDIATE);
        Course inactiveCourse = new Course("Historia dos Algoritmos", "Exploração de algoritmos clássicos que caíram em desuso.", "Prof. Roberto Antigo", 30, DifficultyLevel.BEGINNER);
        inactiveCourse.setStatus(CourseStatus.INACTIVE);

        DataStore.COURSES.put(javaBasics.getTitle(), javaBasics);
        DataStore.COURSES.put(springBoot.getTitle(), springBoot);
        DataStore.COURSES.put(pythonData.getTitle(), pythonData);
        DataStore.COURSES.put(inactiveCourse.getTitle(), inactiveCourse);

        Enrollment e1 = new Enrollment(alice, javaBasics);
        e1.setProgress(55);
        DataStore.ENROLLMENTS.add(e1);

        Enrollment e2 = new Enrollment(alice, pythonData);
        e2.setProgress(25);
        DataStore.ENROLLMENTS.add(e2);

        Enrollment e3 = new Enrollment(bob, javaBasics);
        e3.setProgress(85);
        DataStore.ENROLLMENTS.add(e3);

        Enrollment e4 = new Enrollment(bob, springBoot);
        e4.setProgress(100);
        DataStore.ENROLLMENTS.add(e4);
        
        Enrollment e5 = new Enrollment(bob, pythonData);
        e5.setProgress(15);
        DataStore.ENROLLMENTS.add(e5);

        Enrollment e6 = new Enrollment(charlie, javaBasics);
        e6.setProgress(10);
        DataStore.ENROLLMENTS.add(e6);

        SupportTicket ticket1 = new SupportTicket(alice, "Problema no Pagamento", "Meu boleto da mensalidade não foi gerado.");
        SupportTicket ticket2 = new SupportTicket(bob, "Erro no Vídeo", "A aula 5 do curso de Spring Boot não carrega corretamente.");

        DataStore.SUPPORT_TICKETS.add(ticket1);
        DataStore.SUPPORT_TICKETS.add(ticket2);
    }
}
