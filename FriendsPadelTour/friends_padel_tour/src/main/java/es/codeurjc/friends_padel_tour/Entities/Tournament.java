package es.codeurjc.friends_padel_tour.Entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Bussiness bussiness;

    @OneToMany(mappedBy="tournament", cascade = CascadeType.ALL)
    private List<Match> matches;

    public Tournament() {}
    
    public List<Match> getMatches() {
        return matches;
    }
    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
}
