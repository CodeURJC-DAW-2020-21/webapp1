package es.codeurjc.friends_padel_tour.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DoubleOfPlayers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public DoubleOfPlayers(){}
    
    
}
