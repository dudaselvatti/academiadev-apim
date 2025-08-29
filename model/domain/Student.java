package model.domain;

import java.util.List;

public class Student extends User{

    private SubscriptionPlan subscriptionplan;
    private List<Enrollment> enrollments;

    public Student(String name, String email, SubscriptionPlan subscriptionplan) {
        super(name, email);
        this.subscriptionplan = subscriptionplan;
    }

    public SubscriptionPlan getSubscriptionplan() {
        return subscriptionplan;
    }
    
    public void setSubscriptionPlan(SubscriptionPlan subscriptionplan) {
        this.subscriptionplan = subscriptionplan;
    }

    public SubscriptionPlan getSubscriptionPlan() {
        return this.subscriptionplan;
    }
}
