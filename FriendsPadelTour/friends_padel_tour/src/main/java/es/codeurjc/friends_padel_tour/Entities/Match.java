package es.codeurjc.friends_padel_tour.Entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private Date date;
    @ManyToOne
    private DoubleOfPlayers double1;
    @ManyToOne
    private DoubleOfPlayers double2;

    @ManyToOne
    private Tournament tournament;

    public Date getDate() {
        return date;
    }

    public DoubleOfPlayers getDouble2() {
        return double2;
    }

    public void setDouble2(DoubleOfPlayers double2) {
        this.double2 = double2;
    }

    public DoubleOfPlayers getDouble1() {
        return double1;
    }

    public void setDouble1(DoubleOfPlayers double1) {
        this.double1 = double1;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
