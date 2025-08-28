package model.domain;

public class BasicPlan implements SubscriptionPlan{
    
    private static final long MAX_COURSES = 3;
    
    @Override
    public boolean canEnroll(Student student, long totalEnrollments) {
        return totalEnrollments < MAX_COURSES;
    }

    @Override
    public String getPlanName() {
        return "Basic Plan";
    }
    
}
