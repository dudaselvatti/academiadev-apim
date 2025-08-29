package dto;

import java.util.List;

public class StudentProfileDTO {
    
    private String name;
    private String email;
    private String subscriptionPlanName;
    private List<EnrollmentSummaryDTO> enrollments;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubscriptionPlanName() {
        return subscriptionPlanName;
    }

    public void setSubscriptionPlanName(String subscriptionPlanName) {
        this.subscriptionPlanName = subscriptionPlanName;
    }

    public List<EnrollmentSummaryDTO> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<EnrollmentSummaryDTO> enrollments) {
        this.enrollments = enrollments;
    }

}
