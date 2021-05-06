package es.codeurjc.friends_padel_tour.Entities;

public class BussinessRequest {
    private Bussiness bussiness;
    private User user;
    
    public User getUser() {
        return user;
    }
    public Bussiness getBussiness() {
        return bussiness;
    }
    public void setBussiness(Bussiness bussiness) {
        this.bussiness = bussiness;
    }
    public void setUser(User user) {
        this.user = user;
    }
    
}
