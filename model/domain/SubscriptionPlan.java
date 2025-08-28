package model.domain;

public interface SubscriptionPlan {

    boolean canEnroll(Student student, long totalEnrollments);

    String getPlanName();
    
}
