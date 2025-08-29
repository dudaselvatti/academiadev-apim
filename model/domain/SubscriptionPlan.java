package model.domain;

import java.util.List;

public interface SubscriptionPlan {

    boolean canEnroll(Student student, List<Enrollment> activeEnrollments);

    String getPlanName();
    
}
