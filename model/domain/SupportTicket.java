package model.domain;

public class SupportTicket {
    
    private User user;
    private String description;
    private String title;

    public SupportTicket(User user, String description, String title) {
        this.user = user;
        this.description = description;
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    

}
