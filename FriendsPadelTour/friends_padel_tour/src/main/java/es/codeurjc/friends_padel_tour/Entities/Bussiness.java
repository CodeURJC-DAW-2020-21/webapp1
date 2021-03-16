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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Bussiness{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String userName;
    private String bussinessName;
    private String userSurname;
    private String location;
    private String email;
    private String adress;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bussiness")
    private List<Tournament> tournaments;

    @Lob
    @JsonIgnore
    private Blob image;

    public Bussiness(){}

    public Bussiness(String name2, String userName2, String userSurname2, String location2, String email2,
            String adress2) {
                bussinessName = name2;
                userName = userName2;
                userSurname = userSurname2;
                email = email2;
                location = location2;
                adress = adress2;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
    public String getSurname() {
        return userSurname;
    }
    public void setSurname(String surname) {
        this.userSurname = surname;
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
    public String getUserSurname() {
        return userSurname;
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
    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }
    public void setId(long id) {
        this.id = id;
    }
    
}
