package model.domain;

import java.util.List;

public class BasicPlan implements SubscriptionPlan{
    
    private static final long MAX_COURSES = 3;
    
    @Override
    public boolean canEnroll(Student student, List<Enrollment> activeEnrollments) {
        
        long currentEnrollments = activeEnrollments.stream()
            .filter(enrollment -> enrollment.getStudent().equals(student))
            .count();
        
        return currentEnrollments < MAX_COURSES;
    }

    @Override
    public String getPlanName() {
        return "Basic Plan";
    }
    
}
