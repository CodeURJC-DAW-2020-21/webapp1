package es.codeurjc.friends_padel_tour.Entities;

import java.sql.Blob;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Player{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String surname;
    private String location;
    private String email;
    private String password;
    private int division;

    private int mathcesWon;
    private int matchesLost;
    private int mathesPlayed;
    @OneToMany
    private List<DoubleOfPlayers> doubles;
    @OneToMany
    private List<PadelMatch> openMatches;
    

    @Lob
    @JsonIgnore
    private Blob image;
    
    public Player(){}

    public int getDivision() {
        return division;
    }

    public void setDivision(int division) {
        this.division = division;
    }

    public int getMathesPlayed() {
        return mathesPlayed;
    }

    public void setMathesPlayed(int mathesPlayed) {
        this.mathesPlayed = mathesPlayed;
    }

    public int getMatchesLost() {
        return matchesLost;
    }

    public void setMatchesLost(int matchesLost) {
        this.matchesLost = matchesLost;
    }

    public int getMathcesWon() {
        return mathcesWon;
    }

    public void setMathcesWon(int mathcesWon) {
        this.mathcesWon = mathcesWon;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Player(String name, String surname, String email, String password, String location, int division){
        this.name= name;
        this.surname = surname;
        this.email = email;
        this.password= password;
        this.location = location;
        this.division = division;
        this.matchesLost = this.mathcesWon = this.mathesPlayed = 0;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DoubleOfPlayers> getDoubles() {
        return doubles;
    }

    public void setDoubles(List<DoubleOfPlayers> doubles) {
        this.doubles = doubles;
    }

}
