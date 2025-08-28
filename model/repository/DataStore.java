package model.repository;

import model.domain.Course;
import model.domain.Enrollment;
import model.domain.SupportTicket;
import model.domain.User;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class DataStore {
    
    public static final Map<String, User> USERS = new HashMap<>();
    
    public static final Map<String, Course> COURSES = new HashMap<>();

    public static final List<Enrollment> ENROLLMENTS = new ArrayList<>();

    public static final Queue<SupportTicket> SUPPORT_TICKETS = new ArrayDeque<>();

    private DataStore() {
        //para nao criar novos objetos datastore
    }
}