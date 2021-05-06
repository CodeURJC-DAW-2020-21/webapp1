package es.codeurjc.friends_padel_tour.Entities;

public class PlayerRequest {
    private Player player;
    private User user;
    
    public User getUser() {
        return user;
    }
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public void setUser(User user) {
        this.user = user;
    }
    
}
