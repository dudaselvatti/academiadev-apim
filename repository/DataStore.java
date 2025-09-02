package repository;

import model.*;
import java.util.*;

public class DataStore {
    public static final Map<String, User> USERS = new HashMap<>();
    public static final Map<String, Course> COURSES = new HashMap<>();
    public static final List<Enrollment> ENROLLMENTS = new ArrayList<>();
    public static final Queue<SupportTicket> SUPPORT_TICKETS = new ArrayDeque<>();

    private DataStore() {}
}
