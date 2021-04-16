package es.codeurjc.friends_padel_tour.Entities;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Player{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String username;
    private String name;
    private String surname;
    private String location;
    private String email;
    private String password;
    private int division;
    private boolean hasImage;


    private int mathcesWon;
    private int matchesLost;
    private int mathesPlayed;

    @OneToMany(mappedBy = "player1")
    @JsonIgnore
    private List<DoubleOfPlayers> doubles1;

    @OneToMany(mappedBy = "player2")
    @JsonIgnore
    private List<DoubleOfPlayers> doubles2;

    @OneToMany(mappedBy = "playerCreator")
    @JsonIgnore
    private List<PadelMatch> createdMatches;

    @ManyToMany
    @JsonIgnore
    private List<PadelMatch> playedMatches;

    @ManyToMany
    @JsonIgnore
    private List<PadelMatch> pendingMatches;
    

    @Lob
    @JsonIgnore
    private Blob image;

    @OneToOne
    @JsonIgnore
    private User user;

    private int score;
    
    public Player(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<PadelMatch> getPendingMatches() {
        return pendingMatches;
    }

    public void setPendingMatches(List<PadelMatch> pendingMatches) {
        this.pendingMatches = pendingMatches;
    }

    public List<PadelMatch> getPlayedMatches() {
        return playedMatches;
    }

    public void setPlayedMatches(List<PadelMatch> playedMatches) {
        this.playedMatches = playedMatches;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public boolean isHasImage() {
        return hasImage;
    }

    public void setHasImage(boolean hasImage) {
        this.hasImage = hasImage;
    }

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

    public Player(String username, String name, String surname, String email, String password, String location, int division){
        this.username = username;
        this.name= name;
        this.surname = surname;
        this.email = email;
        this.password= password;
        this.location = location;
        this.division = division;
        this.matchesLost = this.mathcesWon = this.mathesPlayed = 0;
        this.hasImage = false;
        this.doubles1 = new ArrayList<>();
        this.doubles2 = new ArrayList<>();
        this.setScore(0);
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

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public List<PadelMatch> getCreatedMatches() {
        return createdMatches;
    }

    public void setCreatedMatches(List<PadelMatch> createdMatches) {
        this.createdMatches = createdMatches;
    }

    public List<DoubleOfPlayers> getDoubles1() {
        return doubles1;
    }

    public void setDoubles1(List<DoubleOfPlayers> doubles1) {
        this.doubles1 = doubles1;
    }

    public List<DoubleOfPlayers> getDoubles2() {
        return doubles2;
    }

    public void setDoubles2(List<DoubleOfPlayers> doubles2) {
        this.doubles2 = doubles2;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
