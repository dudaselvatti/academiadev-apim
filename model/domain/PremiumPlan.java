package model.domain;

public class PremiumPlan implements SubscriptionPlan {
    
    @Override
    public String getPlanName() {
        return "Premium Plan";
    }

    @Override
    public boolean canEnroll(Student student, long totalEnrollments) {
        return true;
    }
    
}
