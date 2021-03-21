package es.codeurjc.friends_padel_tour.Entities;

import java.sql.Blob;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Bussiness{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String username;
    private String bussinessName;
    private String ownerName;
    private String ownerSurname;
    private String location;
    private String email;
    private String adress;
    private int createdTournaments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bussiness")
    private List<Tournament> tournaments;
    private String[][] schedule = {{"L", "M", "X", "J", "V", "S", "D"},{"8:00-14:00", "8:00-14:00", "8:00-14:00", "8:00-14:00", "8:00-14:00", "8:00-14:00", "8:00-14:00"},{"16:00-22:00", "16:00-22:00", "16:00-22:00", "16:00-22:00", "16:00-22:00", "16:00-22:00", "16:00-22:00"}};; 

    @Lob
    @JsonIgnore
    private Blob image;

    public Bussiness(){
        
    }

    public int getCreatedTournaments() {
        return createdTournaments;
    }

    public void setCreatedTournaments(int createdTournaments) {
        this.createdTournaments = createdTournaments;
    }

    public String[][] getSchedule() {
        return schedule;
    }

    public void setSchedule(String[][] schedule) {
        this.schedule = schedule;
    }

    @OneToOne(cascade = CascadeType.ALL)
    private User user;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Bussiness(String name2, String userName2, String userSurname2, String location2, String email2,
            String adress2, User user) {
                bussinessName = name2;
                setUsername(userName2);
                ownerSurname = userSurname2;
                email = email2;
                location = location2;
                adress = adress2;
                createdTournaments = 0;
                
                this.user = user;
    }


    public String getAdress() {
        return adress;
    }
    public String getBussinessName() {
        return bussinessName;
    }
    public void setBussinessName(String bussinessName) {
        this.bussinessName = bussinessName;
    }
    public String getOwnerSurname() {
        return ownerSurname;
    }
    public void setOwnerSurname(String surname) {
        this.ownerSurname = surname;
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
    public void setAdress(String adress) {
        this.adress = adress;
    }
    public long getId() {
        return id;
    }
    public Blob getImage() {
        return image;
    }
    public List<Tournament> getTournaments() {
        return tournaments;
    }
    public void setImage(Blob image) {
        this.image = image;
    }
    public void setTournaments(List<Tournament> tournaments) {
        this.tournaments = tournaments;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Bussiness(long id, String userName, String bussinessName, String userSurname, String location, String email,
            String adress, Blob image) {
        this.id = id;
        this.bussinessName = bussinessName;
        this.location = location;
        this.email = email;
        this.adress = adress;
        this.image = image;
    }
    
}
