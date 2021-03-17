package es.codeurjc.friends_padel_tour.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PadelMatch {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private int division;
    private String time;
    private String date;
    private String city;
    private String province;
    private String facility;
    private int nPlayers;

    @ManyToOne
    private Player playerCreator;
    
    @ManyToOne
    private DoubleOfPlayers double1;
    @ManyToOne
    private DoubleOfPlayers double2;

    @ManyToOne
    private Tournament tournament;

    public PadelMatch(){}

    public PadelMatch(String province, String city, String facility, String date, String time,int division,Player creator) {
        this.province = province;
        this.city = city;
        this.facility = facility;
        this.date = date;
        this.time = time;
        this.division = division;
        this.playerCreator = creator;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProvince() {
        return province;
    }

    public int getnPlayers() {
        return nPlayers;
    }

    public void setnPlayers(int nPlayers) {
        this.nPlayers = nPlayers;
    }


    public void setProvince(String province) {
        this.province = province;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getLocation() {
        return city;
    }

    public void setLocation(String location) {
        this.city = location;
    }

    public int getDivision() {
        return division;
    }

    public void setDivision(int division) {
        this.division = division;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Player getCreator() {
        return playerCreator;
    }
}
