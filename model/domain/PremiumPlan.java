package model.domain;

import java.util.List;

public class PremiumPlan implements SubscriptionPlan {
    
    @Override
    public String getPlanName() {
        return "Premium Plan";
    }

    @Override
    public boolean canEnroll(Student student, List<Enrollment> activeEnrollments) {
        return true;
    }
    
}
