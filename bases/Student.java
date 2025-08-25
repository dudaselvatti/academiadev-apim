package bases;

import java.util.List;

public class Student extends User{

    private SubscriptionPlan subscriptionplan;
    private List<Enrollments> enrollments;

    public Student(String name, String email, SubscriptionPlan subscriptionplan) {
        super(name, email);
        this.subscriptionplan = subscriptionplan;
    }

    public SubscriptionPlan getSubscriptionplan() {
        return subscriptionplan;
    }


    
}
