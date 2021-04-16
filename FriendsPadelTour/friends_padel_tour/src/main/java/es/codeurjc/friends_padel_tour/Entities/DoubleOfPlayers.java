package es.codeurjc.friends_padel_tour.Entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class DoubleOfPlayers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Player player1;
    @ManyToOne
    private Player player2;

    @ManyToMany
    private List<Tournament> tournaments;


    public DoubleOfPlayers(){}

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Player getPlayer1() {
        return player1;
    }
    
    public long getId(){
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }
    
    public List<Tournament> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<Tournament> tournaments) {
        this.tournaments = tournaments;
    }
    
}
